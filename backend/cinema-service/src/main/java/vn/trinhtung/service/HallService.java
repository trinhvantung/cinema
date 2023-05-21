package vn.trinhtung.service;

import java.util.List;

import org.springframework.data.domain.Page;

import vn.trinhtung.dto.HallRequestDTO;
import vn.trinhtung.dto.HallResponseDTO;
import vn.trinhtung.grpc.HallResponse;

public interface HallService {
	Page<HallResponseDTO> getAll(Integer page, String sort);
	
	HallResponseDTO getById(Integer id);

	HallResponse getHallById(Integer id);

	HallResponseDTO create(HallRequestDTO request);
	
	void update(Integer id, HallRequestDTO request);
	
	void delete(Integer id);

    List<HallResponseDTO> getAll();

	List<HallResponse> getHallsByIds(List<Integer> ids);
}
