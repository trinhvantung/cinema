package vn.trinhtung.mapper;

import org.springframework.stereotype.Component;

import vn.trinhtung.dto.CategoryRequestDTO;
import vn.trinhtung.dto.CategoryResponseDTO;
import vn.trinhtung.entity.Category;
import vn.trinhtung.grpc.CategoryResponse;

@Component
public class CategoryMapper {
	public CategoryResponseDTO categoryToCategoryResponseDTO(Category category) {
		return CategoryResponseDTO.builder().id(category.getId()).name(category.getName())
				.displayOrder(category.getDisplayOrder()).build();
	}

	public Category categoryRequestDTOToCategory(CategoryRequestDTO category) {
		return Category.builder().name(category.getName()).displayOrder(category.getDisplayOrder())
				.build();
	}

	public CategoryResponse categoryToCategoryResponse(Category category) {
		return CategoryResponse.newBuilder()
				.setId(category.getId())
				.setDisplayOrder(category.getDisplayOrder())
				.setName(category.getName())
				.build();
	}
}
