package vn.trinhtung.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.trinhtung.dto.CinemaRequestDTO;
import vn.trinhtung.dto.CinemaResponseDTO;
import vn.trinhtung.entity.Cinema;
import vn.trinhtung.entity.City;
import vn.trinhtung.exception.ApplicationException;
import vn.trinhtung.grpc.CinemaResponse;
import vn.trinhtung.mapper.CinemaMapper;
import vn.trinhtung.repository.CinemaRepository;
import vn.trinhtung.service.CinemaService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CinemaServiceImpl implements CinemaService {
    private final CinemaRepository cinemaRepository;
    private final CinemaMapper cinemaMapper;

    @Override
    public Page<CinemaResponseDTO> getAll(Integer page) {
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page - 1, 10, sort);

        return cinemaRepository.findAll(pageable).map(cinemaMapper::cinemaToCinemaResponseDTO);
    }

    @Override
    public List<CinemaResponseDTO> getAll() {
        return cinemaRepository.findAll().stream().map(cinemaMapper::cinemaToCinemaResponseDTO).collect(Collectors.toList());
    }

    @Override
    public Page<CinemaResponseDTO> getAllByCity(Integer cityId, Integer page) {
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page - 1, 10, sort);

        return cinemaRepository.findAllByCityId(cityId, pageable)
                .map(cinemaMapper::cinemaToCinemaResponseDTO);
    }

    @Override
    public CinemaResponseDTO getById(Integer id) {
        Cinema cinema = cinemaRepository.findById(id).orElseThrow(
                () -> new ApplicationException("cinema_not_found", "Cinema not found"));

        return cinemaMapper.cinemaToCinemaResponseDTO(cinema);
    }

    @Override
    public void createCinema(CinemaRequestDTO request) {
        if (cinemaRepository.findByName(request.getName()).isPresent()) {
            throw new ApplicationException("duplicate_cinema_name", "Duplicate cinema name");
        }

        Cinema cinema = cinemaMapper.cinemaRequestDTOToCinema(request);
        cinemaRepository.save(cinema);
    }

    @Override
    public void updateCinema(Integer id, CinemaRequestDTO request) {
        Cinema cinema = cinemaRepository.findById(id).orElseThrow(
                () -> new ApplicationException("cinema_not_found", "Cinema not found"));

        Optional<Cinema> cinemaByName = cinemaRepository.findByName(request.getName());
        if (cinemaByName.isPresent() && !cinemaByName.get().getId().equals(id)) {
            throw new ApplicationException("duplicate_cinema_name", "Duplicate cinema name");
        }

        cinema.setAddress(request.getAddress());
        cinema.setName(request.getName());
        cinema.setCity(City.builder().id(request.getCityId()).build());

        cinemaRepository.save(cinema);
    }

    @Override
    public void delete(Integer id) {
        Cinema cinema = cinemaRepository.findById(id).orElseThrow(
                () -> new ApplicationException("cinema_not_found", "Cinema not found"));
        cinemaRepository.delete(cinema);
    }

    @Override
    public List<CinemaResponse> getCinemasByIds(List<Integer> ids) {
        return cinemaRepository.findAllById(ids).stream().map(cinemaMapper::cinemaToCinemaResponse)
                .collect(Collectors.toList());
    }
}
