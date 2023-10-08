package vn.trinhtung.service;

import java.util.List;

import org.springframework.data.domain.Page;

import vn.trinhtung.dto.CityRequestDTO;
import vn.trinhtung.dto.CityResponseDTO;

public interface CityService {
	CityResponseDTO getById(Integer id);
	
	List<CityResponseDTO> getAll();
	
	Page<CityResponseDTO> getAll(Integer page);

	void createCity(CityRequestDTO request);

	void updateCity(Integer cityId, CityRequestDTO request);

	void deleteCity(Integer cityId);
}
