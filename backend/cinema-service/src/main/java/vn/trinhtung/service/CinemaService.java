package vn.trinhtung.service;

import org.springframework.data.domain.Page;

import vn.trinhtung.dto.CinemaRequestDTO;
import vn.trinhtung.dto.CinemaResponseDTO;
import vn.trinhtung.grpc.CinemaResponse;

import java.util.List;

public interface CinemaService {
	Page<CinemaResponseDTO> getAll(Integer page);

	List<CinemaResponseDTO> getAll();

	Page<CinemaResponseDTO> getAllByCity(Integer cityId, Integer page);

	CinemaResponseDTO getById(Integer id);

	void createCinema(CinemaRequestDTO request);

	void updateCinema(Integer id, CinemaRequestDTO request);

	void delete(Integer id);

    List<CinemaResponse> getCinemasByIds(List<Integer> ids);
}
