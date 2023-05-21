package vn.trinhtung.mapper;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import vn.trinhtung.dto.CinemaResponseDTO;
import vn.trinhtung.dto.HallRequestDTO;
import vn.trinhtung.dto.HallResponseDTO;
import vn.trinhtung.entity.Cinema;
import vn.trinhtung.entity.Hall;
import vn.trinhtung.grpc.CinemaResponse;
import vn.trinhtung.grpc.HallResponse;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class HallMapper {
	private final CinemaMapper cinemaMapper;

	public HallResponseDTO hallToHallResponseDTO(Hall hall) {
		CinemaResponseDTO cinemaResponseDTO = null;
		if(Objects.nonNull(hall.getCinema())) {
			cinemaResponseDTO = cinemaMapper.cinemaToCinemaResponseDTO(hall.getCinema());
		}

		return HallResponseDTO.builder().id(hall.getId()).name(hall.getName())
				.cinema(cinemaResponseDTO).build();
	}

	public Hall hallRequestDTOToHall(HallRequestDTO request) {
		Cinema cinema = Cinema.builder().id(request.getCinemaId()).build();

		return Hall.builder().name(request.getName()).cinema(cinema).build();
	}

	public HallResponse hallToHallResponse(Hall hall) {
		CinemaResponse cinemaResponse = null;
		if(Objects.nonNull(hall.getCinema())) {
			cinemaResponse = cinemaMapper.cinemaToCinemaResponse(hall.getCinema());
		}

		return HallResponse.newBuilder()
				.setId(hall.getId())
				.setName(hall.getName())
				.setCinema(cinemaResponse)
				.build();
	}
}
