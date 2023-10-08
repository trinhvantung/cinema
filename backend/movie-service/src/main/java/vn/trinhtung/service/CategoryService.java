package vn.trinhtung.service;

import java.util.List;

import org.springframework.data.domain.Page;

import vn.trinhtung.dto.CategoryRequestDTO;
import vn.trinhtung.dto.CategoryResponseDTO;

public interface CategoryService {
	List<CategoryResponseDTO> getAll();

	Page<CategoryResponseDTO> getAll(Integer page);

	CategoryResponseDTO getById(Integer id);

	void create(CategoryRequestDTO request);

	void update(Integer categoryId, CategoryRequestDTO request);

	void delete(Integer id);

	Long count();
}
