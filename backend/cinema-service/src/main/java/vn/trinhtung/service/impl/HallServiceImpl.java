package vn.trinhtung.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.trinhtung.dto.HallRequestDTO;
import vn.trinhtung.dto.HallResponseDTO;
import vn.trinhtung.entity.Cinema;
import vn.trinhtung.entity.Hall;
import vn.trinhtung.exception.ApplicationException;
import vn.trinhtung.grpc.HallResponse;
import vn.trinhtung.mapper.HallMapper;
import vn.trinhtung.mapper.SeatMapper;
import vn.trinhtung.repository.CinemaRepository;
import vn.trinhtung.repository.HallRepository;
import vn.trinhtung.service.HallService;

@RequiredArgsConstructor
@Service
public class HallServiceImpl implements HallService {
    private final HallRepository hallRepository;
    private final HallMapper hallMapper;
    private final SeatMapper seatMapper;
    private final CinemaRepository cinemaRepository;


    @Override
    public Page<HallResponseDTO> getAll(Integer page, String sortString) {
        String[] sorts = sortString.split("-");
        Sort sort;
        if (sorts[0].equalsIgnoreCase("cinema")) {
            if (sorts[1].equalsIgnoreCase("desc")) {
                sort = Sort.by("cinema.name").descending();
            } else {
                sort = Sort.by("cinema.name").ascending();
            }
        } else if (sorts[0].equalsIgnoreCase("name")) {
            if (sorts[1].equalsIgnoreCase("desc")) {
                sort = Sort.by("name").descending();
            } else {
                sort = Sort.by("name").ascending();
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
        return hallRepository.findAll(pageable).map(hallMapper::hallToHallResponseDTO);
    }

    @Override
    public HallResponseDTO getById(Integer id) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("hall_not_found", "Hall not found"));
        return hallMapper.hallToHallResponseDTO(hall);
    }

    @Override
    public HallResponse getHallById(Integer id) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("hall_not_found", "Hall not found"));
        return hallMapper.hallToHallResponse(hall);
    }

    @Override
    public HallResponseDTO create(HallRequestDTO request) {
        if (hallRepository.findByNameAndCinemaId(request.getName(), request.getCinemaId())
                .isPresent()) {
            throw new ApplicationException("duplicate_hall_name", "Duplicate hall name");
        }

        if (cinemaRepository.findById(request.getCinemaId()).isEmpty()) {
            throw new ApplicationException("cinema_not_found", "Cinema not found");
        }

        Hall hall = hallMapper.hallRequestDTOToHall(request);

        return hallMapper.hallToHallResponseDTO(hallRepository.save(hall));
    }

    @Override
    public void update(Integer id, HallRequestDTO request) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("hall_not_found", "Hall not found"));

        if (cinemaRepository.findById(request.getCinemaId()).isEmpty()) {
            throw new ApplicationException("cinema_not_found", "Cinema not found");
        }

        Optional<Hall> hallByName = hallRepository.findByNameAndCinemaId(request.getName(),
                request.getCinemaId());
        if (hallByName.isPresent() && hallByName.get().getId() != id) {
            throw new ApplicationException("duplicate_hall_name", "Duplicate hall name");
        }

        Cinema cinema = Cinema.builder().id(request.getCinemaId()).build();
        hall.setCinema(cinema);
        hall.setName(request.getName());

        hallRepository.save(hall);
    }

    @Override
    public void delete(Integer id) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("hall_not_found", "Hall not found"));
        hallRepository.delete(hall);
    }

    @Override
    public List<HallResponseDTO> getAll() {
        return hallRepository.findAll().stream().map(hallMapper::hallToHallResponseDTO).collect(Collectors.toList());
    }

    @Override
    public List<HallResponse> getHallsByIds(List<Integer> ids) {
        return hallRepository.findAllById(ids).stream().map(hallMapper::hallToHallResponse).collect(Collectors.toList());
    }
}
