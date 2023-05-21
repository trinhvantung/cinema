package vn.trinhtung.mapper;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import vn.trinhtung.dto.CinemaRequestDTO;
import vn.trinhtung.dto.CinemaResponseDTO;
import vn.trinhtung.dto.CityResponseDTO;
import vn.trinhtung.entity.Cinema;
import vn.trinhtung.entity.City;
import vn.trinhtung.grpc.CinemaResponse;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class CinemaMapper {
    private final CityMapper cityMapper;

    public CinemaResponseDTO cinemaToCinemaResponseDTO(Cinema cinema) {
        CityResponseDTO cityResponseDTO = null;
        if(Objects.nonNull(cinema.getCity())) {
            cityResponseDTO = cityMapper.cityToCityResponseDTO(cinema.getCity());
        }

        return CinemaResponseDTO.builder().id(cinema.getId()).name(cinema.getName())
                .address(cinema.getAddress()).city(cityResponseDTO)
                .build();
    }

    public Cinema cinemaRequestDTOToCinema(CinemaRequestDTO request) {
        City city = City.builder().id(request.getCityId()).build();
        return Cinema.builder()
                .name(request.getName()).address(request.getAddress()).city(city).build();
    }


    public CinemaResponse cinemaToCinemaResponse(Cinema cinema) {
        return CinemaResponse.newBuilder()
                .setAddress(cinema.getAddress())
                .setId(cinema.getId())
                .setName(cinema.getName())
                .build();
    }
}
