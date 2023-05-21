package vn.trinhtung.service;

import vn.trinhtung.dto.SeatRequestDTO;
import vn.trinhtung.dto.SeatResponseDTO;
import vn.trinhtung.grpc.SeatResponse;

import java.util.List;

public interface SeatService {

	List<SeatResponseDTO> getAllByHall(Integer hallId);

	List<SeatResponse> getSeatsByHall(Integer hallId);
	
	List<SeatResponse> getSeatsByIds(List<Integer> ids);

	void save(Integer hallId, List<SeatRequestDTO> seatRequestDTOs);
}
