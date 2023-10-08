package vn.trinhtung.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.trinhtung.dto.SeatResponseDTO;
import vn.trinhtung.dto.SeatTypeResponseDTO;
import vn.trinhtung.grpc.SeatResponse;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class SeatMapper {
    private final SeatTypeMapper seatTypeMapper;

    public SeatResponseDTO seatResponseToSeatResponseDTO(SeatResponse seat) {
        SeatTypeResponseDTO seatTypeResponseDTO = null;
        if(Objects.nonNull(seat.getSeatType())) {
            seatTypeResponseDTO = seatTypeMapper.seatTypeResponseToSeatTypeResponseDTO(seat.getSeatType());
        }

        return SeatResponseDTO.builder()
                .id(seat.getId())
                .columnIndex(seat.getColumnIndex())
                .rowIndex(seat.getRowIndex())
                .seatType(seatTypeResponseDTO)
                .name(seat.getName())
                .build();
    }
}
