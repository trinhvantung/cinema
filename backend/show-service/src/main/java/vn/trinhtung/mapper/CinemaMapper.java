package vn.trinhtung.mapper;

import org.springframework.stereotype.Component;
import vn.trinhtung.dto.CinemaResponseDTO;
import vn.trinhtung.grpc.CinemaResponse;

@Component
public class CinemaMapper {
    public CinemaResponseDTO cinemaResponseToCinemaResponseDTO(CinemaResponse cinema) {
        return CinemaResponseDTO.builder()
                .id(cinema.getId())
                .name(cinema.getName())
                .address(cinema.getAddress())
                .build();
    }
}
