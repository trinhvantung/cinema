package vn.trinhtung.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.trinhtung.dto.CityRequestDTO;
import vn.trinhtung.dto.CityResponseDTO;
import vn.trinhtung.entity.City;
import vn.trinhtung.exception.ApplicationException;
import vn.trinhtung.mapper.CityMapper;
import vn.trinhtung.repository.CityRepository;
import vn.trinhtung.service.CityService;

@RequiredArgsConstructor
@Service
public class CityServiceImpl implements CityService {
	private final CityMapper cityMapper;
	private final CityRepository cityRepository;

	@Override
	public List<CityResponseDTO> getAll() {
		Sort sort = Sort.by("displayOrder").ascending();
		return cityRepository.findAll(sort).stream().map(cityMapper::cityToCityResponseDTO).collect(Collectors.toList());
	}

	@Override
	public Page<CityResponseDTO> getAll(Integer page) {
		Sort sort = Sort.by("id").descending();
		Pageable pageable = PageRequest.of(page - 1, 10, sort);
		return cityRepository.findAll(pageable).map(cityMapper::cityToCityResponseDTO);
	}

	@Override
	public void createCity(CityRequestDTO request) {
		if (cityRepository.findByName(request.getName()).isPresent()) {
			throw new ApplicationException("duplicate_city_name", "Duplicate city name");
		}
		City city = cityMapper.cityRequestDTOToCity(request);
		cityRepository.save(city);
	}

	@Override
	public void updateCity(Integer cityId, CityRequestDTO request) {
		City city = cityRepository.findById(cityId)
				.orElseThrow(() -> new ApplicationException("city_not_found", "City not found"));

		Optional<City> cityByName = cityRepository.findByName(request.getName());

		if (cityByName.isPresent() && cityByName.get().getId() != cityId) {
			throw new ApplicationException("duplicate_city_name", "Duplicate city name");
		}

		city.setName(request.getName());
		city.setDisplayOrder(request.getDisplayOrder());

		cityRepository.save(city);
	}

	@Override
	public void deleteCity(Integer cityId) {
		City city = cityRepository.findById(cityId)
				.orElseThrow(() -> new ApplicationException("city_not_found", "City not found"));
		cityRepository.delete(city);
	}

	@Override
	public CityResponseDTO getById(Integer id) {
		City city = cityRepository.findById(id)
				.orElseThrow(() -> new ApplicationException("city_not_found", "City not found"));
		return cityMapper.cityToCityResponseDTO(city);
	}

}
