package vn.trinhtung.mapper;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import vn.trinhtung.dto.SeatRequestDTO;
import vn.trinhtung.dto.SeatResponseDTO;
import vn.trinhtung.dto.SeatTypeResponseDTO;
import vn.trinhtung.entity.Seat;
import vn.trinhtung.entity.SeatType;
import vn.trinhtung.grpc.SeatResponse;
import vn.trinhtung.grpc.SeatTypeResponse;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class SeatMapper {
	private final SeatTypeMapper seatTypeMapper;

	public SeatResponseDTO seatToSeatResponseDTO(Seat seat) {
		SeatTypeResponseDTO seatTypeResponseDTO = seatTypeMapper
				.seatTypeToSeatTypeResponseDTO(seat.getSeatType());

		return SeatResponseDTO.builder().id(seat.getId()).columnIndex(seat.getColumnIndex())
				.rowIndex(seat.getRowIndex()).name(seat.getName()).seatType(seatTypeResponseDTO)
				.build();
	}

	public Seat seatRequestDTOToSeat(SeatRequestDTO request) {
		SeatType seatType = SeatType.builder().id(request.getSeatTypeId()).build();

		return Seat.builder().id(request.getId()).columnIndex(request.getColumnIndex())
				.rowIndex(request.getRowIndex()).name(request.getName()).seatType(seatType).build();
	}

	public SeatResponse seatToSeatResponse(Seat seat) {
		SeatTypeResponse seatTypeResponse = null;

		if(Objects.nonNull(seat.getSeatType())) {
			seatTypeResponse = seatTypeMapper.seatTypeToSeatTypeResponse(seat.getSeatType());
		}

		return SeatResponse.newBuilder()
				.setId(seat.getId())
				.setColumnIndex(seat.getColumnIndex())
				.setRowIndex(seat.getRowIndex())
				.setName(seat.getName())
				.setSeatType(seatTypeResponse)
				.build();
	}
}
