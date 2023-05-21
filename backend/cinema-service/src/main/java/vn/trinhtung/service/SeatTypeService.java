package vn.trinhtung.service;

import java.util.List;

import vn.trinhtung.dto.SeatTypeRequestDTO;
import vn.trinhtung.dto.SeatTypeResponseDTO;
import vn.trinhtung.grpc.SeatTypeResponse;

public interface SeatTypeService {
	List<SeatTypeResponseDTO> getAll();

	SeatTypeResponseDTO getById(Integer id);

	void create(SeatTypeRequestDTO request);

	void update(Integer seatTypeId, SeatTypeRequestDTO request);

	void delete(Integer seatTypeId);

	List<SeatTypeResponseDTO> getAllByHall(Integer hallId);

    List<SeatTypeResponse> getSeatTypesByHall(Integer hallId);
}
