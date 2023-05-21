package vn.trinhtung.mapper;

import org.springframework.stereotype.Component;

import vn.trinhtung.dto.SeatTypeRequestDTO;
import vn.trinhtung.dto.SeatTypeResponseDTO;
import vn.trinhtung.entity.SeatType;
import vn.trinhtung.grpc.SeatTypeResponse;

@Component
public class SeatTypeMapper {
	public SeatTypeResponseDTO seatTypeToSeatTypeResponseDTO(SeatType seatType) {

		return SeatTypeResponseDTO.builder().id(seatType.getId()).color(seatType.getColor())
				.name(seatType.getName()).build();
	}

	public SeatType seatTypeRequestDTOToSeatType(SeatTypeRequestDTO request) {
		return SeatType.builder().color(request.getColor()).name(request.getName()).build();
	}

	public SeatTypeResponse seatTypeToSeatTypeResponse(SeatType seatType) {
		return SeatTypeResponse.newBuilder()
				.setId(seatType.getId())
				.setColor(seatType.getColor())
				.setName(seatType.getName())
				.build();
	}
}
