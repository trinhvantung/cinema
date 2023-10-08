package vn.trinhtung.mapper;

import org.springframework.stereotype.Component;

import vn.trinhtung.dto.CityRequestDTO;
import vn.trinhtung.dto.CityResponseDTO;
import vn.trinhtung.entity.City;

@Component
public class CityMapper {
	public CityResponseDTO cityToCityResponseDTO(City city) {
		return CityResponseDTO.builder().id(city.getId()).name(city.getName())
				.displayOrder(city.getDisplayOrder()).build();
	}

	public City cityRequestDTOToCity(CityRequestDTO request) {
		return City.builder().name(request.getName()).displayOrder(request.getDisplayOrder())
				.build();
	}


}
