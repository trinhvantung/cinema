package vn.trinhtung.mapper;

import org.springframework.stereotype.Component;
import vn.trinhtung.dto.SeatTypeResponseDTO;
import vn.trinhtung.grpc.SeatTypeResponse;

@Component
public class SeatTypeMapper {
    public SeatTypeResponseDTO seatTypeResponseToSeatTypeResponseDTO(SeatTypeResponse seatType) {
        return SeatTypeResponseDTO.builder()
                .id(seatType.getId())
                .color(seatType.getColor())
                .name(seatType.getName())
                .build();
    }
}
