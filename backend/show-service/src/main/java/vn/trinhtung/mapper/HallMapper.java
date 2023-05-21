package vn.trinhtung.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.trinhtung.dto.CinemaResponseDTO;
import vn.trinhtung.dto.HallResponseDTO;
import vn.trinhtung.grpc.HallResponse;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class HallMapper {
    private final CinemaMapper cinemaMapper;

    public HallResponseDTO hallResponseToHallResponseDTO(HallResponse hall) {
        CinemaResponseDTO cinemaResponseDTO = null;
        if(Objects.nonNull(hall.getCinema())) {
            cinemaResponseDTO = cinemaMapper.cinemaResponseToCinemaResponseDTO(hall.getCinema());
        }

        return HallResponseDTO.builder()
                .id(hall.getId())
                .name(hall.getName())
                .cinema(cinemaResponseDTO)
                .build();
    }
}
