package vn.trinhtung.service.impl;

import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.trinhtung.dto.*;
import vn.trinhtung.entity.Show;
import vn.trinhtung.entity.ShowPrice;
import vn.trinhtung.entity.ShowPriceId;
import vn.trinhtung.exception.ApplicationException;
import vn.trinhtung.grpc.*;
import vn.trinhtung.mapper.*;
import vn.trinhtung.repository.ShowRepository;
import vn.trinhtung.repository.spcification.ShowSpecification;
import vn.trinhtung.service.ShowService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ShowServiceImpl implements ShowService {
    private final ShowRepository showRepository;
    private final ShowMapper showMapper;
    private final MovieMapper movieMapper;
    private final HallMapper hallMapper;
    private final CinemaMapper cinemaMapper;
    private final SeatMapper seatMapper;
    private final SeatTypeMapper seatTypeMapper;

    private final ShowPriceMapper showPriceMapper;


    @GrpcClient("movieService")
    private MovieServiceGrpc.MovieServiceBlockingStub movieServiceBlockingStub;

    @GrpcClient("cinemaService")
    private HallServiceGrpc.HallServiceBlockingStub hallServiceBlockingStub;

    @GrpcClient("cinemaService")
    private SeatServiceGrpc.SeatServiceBlockingStub seatServiceBlockingStub;

    @GrpcClient("cinemaService")
    private SeatTypeServiceGrpc.SeatTypeServiceBlockingStub seatTypeServiceBlockingStub;

    @GrpcClient("cinemaService")
    private CinemaServiceGrpc.CinemaServiceBlockingStub cinemaServiceBlockingStub;


    @Override
    public Page<ShowResponseDTO> getAll(Integer page) {
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page - 1, 10, sort);

        Page<ShowResponseDTO> response = showRepository.findAll(pageable)
                .map(showMapper::showToShowResponseDTO);

        List<Integer> movieIds = response.stream().map(ShowResponseDTO::getMovieId).distinct()
                .collect(Collectors.toList());


        List<MovieResponseDTO> movieResponseDTOs = movieServiceBlockingStub.getMoviesByIds(GetMoviesByIdsRequest
                .newBuilder().addAllIds(movieIds).build()).getMoviesList().stream()
                .map(movieMapper::movieResponseToMovieResponseDTO).collect(Collectors.toList());

        List<Integer> hallIds = response.stream().map(ShowResponseDTO::getHallId).collect(Collectors.toList());


        List<HallResponseDTO> hallResponseDTOs = hallServiceBlockingStub.getHallsByIds(GetHallsByIdsRequest
                .newBuilder().addAllIds(hallIds).build()).getHallsList().stream()
                .map(hallMapper::hallResponseToHallResponseDTO).collect(Collectors.toList());

        // Add movie
        response.forEach(showResponse -> {
            MovieResponseDTO movieResponseDTO = movieResponseDTOs.stream()
                    .filter(m -> m.getId().equals(showResponse.getMovieId())).findFirst()
                    .orElse(null);
            showResponse.setMovie(movieResponseDTO);
            showResponse.setHall(hallResponseDTOs.stream().filter(hallResponseDTO -> hallResponseDTO.getId()
                    .equals(showResponse.getHallId())).findFirst().orElse(null));
        });
        return response;
    }

    @Override
    public List<ShowResponse> getShowsByIds(List<Integer> ids) {

        List<ShowResponse> response = showRepository.findAllById(ids).stream()
                .map(showMapper::showToShowResponse).collect(Collectors.toList());

        List<Integer> movieIds = response.stream().map(ShowResponse::getMovieId).distinct()
                .collect(Collectors.toList());

        List<MovieResponse> movieResponses = movieServiceBlockingStub
                .getMoviesByIds(GetMoviesByIdsRequest.newBuilder().addAllIds(movieIds).build()).getMoviesList();


        response = response.stream().map(showResponse -> {
            MovieResponse movieResponse = movieResponses.stream()
                    .filter(m -> m.getId() == showResponse.getMovieId()).findFirst()
                    .orElse(null);

            return showResponse.toBuilder().setMovie(movieResponse).build();
        }).collect(Collectors.toList());

        return response;
    }

    @Override
    public Page<MovieShowResponseDTO> getAllByCinema(Integer page, Integer cinemaId,
                                                     String dateString) {
        Integer[] dateArr = Arrays.stream(dateString.split("-")).map(Integer::valueOf)
                .toArray(Integer[]::new);
        LocalDate date = LocalDate.of(dateArr[0], dateArr[1], dateArr[2]);

        LocalDateTime dateMin = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime dateMax = LocalDateTime.of(date, LocalTime.MAX);

        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page - 1, 10, sort);

        Page<Integer> moviePage = showRepository.findAllMovieByCinemaAndStart(dateMin, dateMax,
                cinemaId, pageable);

        List<Integer> movieIds = moviePage.getContent();

        List<Show> shows = showRepository.findAllShowByMoviesAndCinemaAndStart(dateMin, dateMax,
                cinemaId, movieIds);


        List<MovieResponseDTO> movies = movieServiceBlockingStub.getMoviesByIds(GetMoviesByIdsRequest
                .newBuilder().addAllIds(movieIds).build()).getMoviesList().stream()
                .map(movieMapper::movieResponseToMovieResponseDTO).collect(Collectors.toList());

        LocalDateTime now = LocalDateTime.now();
        return moviePage.map(movieId -> {
            MovieResponseDTO movie = movies.stream().filter(m -> m.getId().equals(movieId)).findFirst()
                    .orElse(null);
            List<ShowResponseDTO> showResponseDTOs = shows.stream()
                    .filter(show -> show.getMovieId().equals(movieId) && now.isBefore(show.getStart()))
                    .map(showMapper::showToShowResponseDTO)
                    .sorted(Comparator.comparing(ShowResponseDTO::getStart))
                    .collect(Collectors.toList());
            return MovieShowResponseDTO.builder().movie(movie).shows(showResponseDTOs).build();
        });
    }

    @Override
    public Page<CinemaShowResponseDTO> getAllByMovie(Integer page, Integer movieId, String dateString) {
        Integer[] dateArr = Arrays.stream(dateString.split("-")).map(Integer::valueOf)
                .toArray(Integer[]::new);
        LocalDate date = LocalDate.of(dateArr[0], dateArr[1], dateArr[2]);

        LocalDateTime dateMin = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime dateMax = LocalDateTime.of(date, LocalTime.MAX);

        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        Page<Integer> cinemaPage = showRepository.findAllCinemaByMovieAndStart(dateMin, dateMax,
                movieId, pageable);

        System.out.println(cinemaPage.toString());
        System.out.println(cinemaPage.getContent().toString());
        System.out.println(cinemaPage.getContent().size());

        List<Integer> cinemaIds = cinemaPage.getContent();

        List<Show> shows = showRepository.findAllShowByMovieAndCinemasAndStart(dateMin, dateMax,
                movieId, cinemaIds);

        List<CinemaResponseDTO> cinemas = cinemaServiceBlockingStub.getCinemasByIds(GetCinemasByIdsRequest
                .newBuilder().addAllIds(cinemaIds).build()).getCinemasList().stream()
                .map(cinemaMapper::cinemaResponseToCinemaResponseDTO).collect(Collectors.toList());


        LocalDateTime now = LocalDateTime.now();
        return cinemaPage.map(cinemaId -> {
            CinemaResponseDTO cinema = cinemas.stream().filter(c -> c.getId().equals(cinemaId)).findFirst()
                    .orElse(null);
            List<ShowResponseDTO> showResponseDTOs = shows.stream()
                    .filter(show -> show.getCinemaId().equals(cinemaId) && now.isBefore(show.getStart()))
                    .map(showMapper::showToShowResponseDTO)
                    .sorted(Comparator.comparing(ShowResponseDTO::getStart))
                    .collect(Collectors.toList());
            return CinemaShowResponseDTO.builder().cinema(cinema).shows(showResponseDTOs).build();
        });
    }

    @Override
    public void create(ShowRequestDTO request) {
        Optional<Show> showOptional = showRepository.findByHallIdAndStart(request.getHallId(), request.getStart());
        if (showOptional.isPresent()) {
            throw new ApplicationException("show_duplicate_time", "Show duplicate time");
        }


        HallResponseDTO hallResponseDTO = hallMapper.hallResponseToHallResponseDTO(hallServiceBlockingStub
                .getHallById(GetHallByIdRequest.newBuilder().setId(request.getHallId()).build()).getHall());


        List<SeatTypeResponseDTO> seatTypeResponseDTOs = seatTypeServiceBlockingStub.getSeatTypesByHall(GetSeaTypesByHallRequest
                .newBuilder().setHallId(request.getHallId()).build()).getSeatTypesList().stream()
                .map(seatTypeMapper::seatTypeResponseToSeatTypeResponseDTO).collect(Collectors.toList());


        Show show = new Show();
        show.setCinemaId(hallResponseDTO.getCinema().getId());
        show.setHallId(request.getHallId());
        show.setMovieId(request.getMovieId());
        show.setStart(request.getStart());
        show.setEnd(request.getEnd());

        Integer[] seatTypeResponseIdArr = seatTypeResponseDTOs.stream().map(SeatTypeResponseDTO::getId)
                .toArray(Integer[]::new);
        Integer[] seatTypeRequestIdArr = request.getShowPrices().stream().map(ShowPriceRequestDTO::getSeatTypeId)
                .toArray(Integer[]::new);

        List<Integer> seatTypeResponseIds = new ArrayList<>(Arrays.asList(seatTypeResponseIdArr));
        List<Integer> seatTypeRequestIds = new ArrayList<>(Arrays.asList(seatTypeRequestIdArr));

        // Các phần tử khác nhau trong cả 2 mảng
        seatTypeResponseIds.retainAll(seatTypeRequestIds);
        System.out.println("Các phần tử giống nhau trong cả 2 mảng: " + seatTypeResponseIds);

        // Các phần tử khác nhau trong mảng thứ nhất
        seatTypeResponseIds = new ArrayList<>(Arrays.asList(seatTypeResponseIdArr));
        seatTypeResponseIds.removeAll(seatTypeRequestIds);
        System.out.println("Các phần tử khác nhau trong mảng thứ nhất: " + seatTypeResponseIds);

        if (seatTypeResponseIds.size() > 0) {
            throw new ApplicationException("seat_type_not_enough", "Seat type not enough", seatTypeResponseIds);
        }

        // Các phần tử khác nhau trong mảng thứ hai
        seatTypeResponseIds = new ArrayList<>(Arrays.asList(seatTypeResponseIdArr));
        seatTypeRequestIds.removeAll(seatTypeResponseIds);
        System.out.println("Các phần tử khác nhau trong mảng thứ hai: " + seatTypeRequestIds);
        if (seatTypeRequestIds.size() > 0) {
            throw new ApplicationException("seat_type_not_found", "Seat type not found", seatTypeRequestIds);
        }


        List<ShowPrice> showPrices = new ArrayList<>();
        request.getShowPrices().forEach(showPrice -> {
            ShowPriceId id = new ShowPriceId(null, showPrice.getSeatTypeId());
            ShowPrice sp = new ShowPrice(id, showPrice.getPrice(), show);
            showPrices.add(sp);
        });

        show.setShowPrices(showPrices);

        showRepository.save(show);
    }

    @Override
    public void update(Integer showId, ShowRequestDTO request) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ApplicationException("show_not_found", "Show not found"));

        Optional<Show> showOptional = showRepository.findByHallIdAndStart(request.getHallId(), request.getStart());
        if (showOptional.isPresent() && !showOptional.get().getId().equals(showId)) {
            throw new ApplicationException("show_duplicate_time", "Show duplicate time");
        }

        HallResponseDTO hallResponseDTO = hallMapper.hallResponseToHallResponseDTO(hallServiceBlockingStub
                .getHallById(GetHallByIdRequest.newBuilder().setId(request.getHallId()).build()).getHall());

        List<SeatTypeResponseDTO> seatTypeResponseDTOs = seatTypeServiceBlockingStub.getSeatTypesByHall(GetSeaTypesByHallRequest
                .newBuilder().setHallId(request.getHallId()).build()).getSeatTypesList().stream()
                .map(seatTypeMapper::seatTypeResponseToSeatTypeResponseDTO).collect(Collectors.toList());


        show.setCinemaId(hallResponseDTO.getCinema().getId());
        show.setHallId(request.getHallId());
        show.setMovieId(request.getMovieId());
        show.setStart(request.getStart());
        show.setEnd(request.getEnd());

        List<ShowPrice> showPrices = show.getShowPrices();
        showPrices.clear();

        request.getShowPrices().forEach(showPrice -> {
            if (seatTypeResponseDTOs.stream().noneMatch(seatTypeResponseDTO -> seatTypeResponseDTO.getId().equals(showPrice.getSeatTypeId()))) {
                throw new ApplicationException("seat_type_not_found", "Seat type not found", showPrice.getSeatTypeId());
            }
            ShowPriceId id = new ShowPriceId(show.getId(), showPrice.getSeatTypeId());
            ShowPrice sp = new ShowPrice(id, showPrice.getPrice(), show);
            showPrices.add(sp);
        });

        show.setShowPrices(showPrices);

        showRepository.save(show);
    }

    @Override
    public void delete(Integer showId) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ApplicationException("show_not_found", "Show not found"));
        showRepository.delete(show);
    }

    @Override
    public Page<ShowResponseDTO> getAll(Integer page, ShowFilterDTO showFilter, String sortString) {
        String[] sorts = sortString.split("-");
        Sort sort;
        if (sorts[0].equalsIgnoreCase("start")) {
            if (sorts[1].equalsIgnoreCase("desc")) {
                sort = Sort.by("start").descending();
            } else {
                sort = Sort.by("start").ascending();
            }
        } else if (sorts[0].equalsIgnoreCase("id")) {
            if (sorts[1].equalsIgnoreCase("desc")) {
                sort = Sort.by("id").descending();
            } else {
                sort = Sort.by("id").ascending();
            }
        } else {
            sort = Sort.by("id").descending();
        }
        Pageable pageable = PageRequest.of(page - 1, 10, sort);


        Page<ShowResponseDTO> response = showRepository.findAll(ShowSpecification.filterShowSpecification(showFilter), pageable)
                .map(showMapper::showToShowResponseDTO);

        List<Integer> movieIds = response.stream().map(ShowResponseDTO::getMovieId).distinct()
                .collect(Collectors.toList());


        List<MovieResponseDTO> movieResponseDTOs = movieServiceBlockingStub.getMoviesByIds(GetMoviesByIdsRequest
                .newBuilder().addAllIds(movieIds).build()).getMoviesList().stream()
                .map(movieMapper::movieResponseToMovieResponseDTO).collect(Collectors.toList());

        List<Integer> hallIds = response.stream().map(ShowResponseDTO::getHallId).collect(Collectors.toList());


        List<HallResponseDTO> hallResponseDTOs = hallServiceBlockingStub.getHallsByIds(GetHallsByIdsRequest
                .newBuilder().addAllIds(hallIds).build()).getHallsList().stream()
                .map(hallMapper::hallResponseToHallResponseDTO).collect(Collectors.toList());

        // Add movie
        response.forEach(showResponse -> {
            MovieResponseDTO movieResponseDTO = movieResponseDTOs.stream()
                    .filter(m -> m.getId().equals(showResponse.getMovieId())).findFirst()
                    .orElse(null);
            showResponse.setMovie(movieResponseDTO);
            showResponse.setHall(hallResponseDTOs.stream().filter(hallResponseDTO -> hallResponseDTO.getId()
                    .equals(showResponse.getHallId())).findFirst().orElse(null));
        });
        return response;
    }

    @Override
    public Long count() {
        return showRepository.count();
    }

    @Override
    public ShowResponseDTO getById(Integer showId) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ApplicationException("show_not_found", "Show not found"));
        return showMapper.showToShowResponseDTO(show);
    }

    @Override
    public ShowResponse getShowById(Integer showId) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ApplicationException("show_not_found", "Show not found"));
        return showMapper.showToShowResponse(show);
    }

    @Override
    public ShowDetailResponseDTO getDetail(Integer showId) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ApplicationException("show_not_found", "Show not found"));

        if (show.getStart().isBefore(LocalDateTime.now())) {
            throw new ApplicationException("show_not_found", "Show not found");
        }

        HallResponseDTO hall = hallMapper.hallResponseToHallResponseDTO(hallServiceBlockingStub
                .getHallById(GetHallByIdRequest.newBuilder().setId(show.getHallId()).build()).getHall());

        List<SeatResponseDTO> seats = seatServiceBlockingStub.getSeatsByHall(GetSeatsByHallRequest
                .newBuilder().setHallId(show.getHallId()).build()).getSeatsList().stream()
                .map(seatMapper::seatResponseToSeatResponseDTO).collect(Collectors.toList());


        MovieResponseDTO movie = movieMapper.movieResponseToMovieResponseDTO(movieServiceBlockingStub.getMovieById(GetMovieByIdRequest
                .newBuilder().setId(show.getMovieId()).build()).getMovie());

        List<ShowPriceResponseDTO> showPrices = show.getShowPrices().stream()
                .map(showPriceMapper::showPriceToShowPriceResponseDTO).collect(Collectors.toList());

        ShowDetailResponseDTO response = new ShowDetailResponseDTO();
        response.setId(show.getId());
        response.setEnd(show.getEnd());
        response.setStart(show.getStart());
        response.setHall(hall);
        response.setMovie(movie);
        response.setSeats(seats);
        response.setShowPrices(showPrices);

        return response;
    }

}
