package vn.trinhtung.mapper;

import org.springframework.stereotype.Component;
import vn.trinhtung.dto.CategoryResponseDTO;
import vn.trinhtung.grpc.CategoryResponse;

@Component
public class CategoryMapper {

	public CategoryResponseDTO categoryResponseToCategoryResponseDTO(CategoryResponse category) {
		return CategoryResponseDTO.builder()
				.id(category.getId())
				.displayOrder(category.getDisplayOrder())
				.name(category.getName())
				.build();
	}
}
