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
import vn.trinhtung.dto.CategoryRequestDTO;
import vn.trinhtung.dto.CategoryResponseDTO;
import vn.trinhtung.entity.Category;
import vn.trinhtung.exception.ApplicationException;
import vn.trinhtung.mapper.CategoryMapper;
import vn.trinhtung.repository.CategoryRepository;
import vn.trinhtung.service.CategoryService;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
	private final CategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper;

	@Override
	public List<CategoryResponseDTO> getAll() {
		Sort sort = Sort.by("displayOrder").ascending();
		return categoryRepository.findAll(sort).stream()
				.map(categoryMapper::categoryToCategoryResponseDTO).collect(Collectors.toList());
	}

	@Override
	public Page<CategoryResponseDTO> getAll(Integer page) {
		Sort sort = Sort.by("id").descending();
		Pageable pageable = PageRequest.of(page - 1, 2, sort);
		return categoryRepository.findAll(pageable).map(categoryMapper::categoryToCategoryResponseDTO);
	}

	@Override
	public CategoryResponseDTO getById(Integer id) {
		Category category = categoryRepository.findById(id).orElseThrow(
				() -> new ApplicationException("category_not_found", "Category not found"));
		return categoryMapper.categoryToCategoryResponseDTO(category);
	}

	@Override
	public void create(CategoryRequestDTO request) {
		if (categoryRepository.findByName(request.getName()).isPresent()) {
			throw new ApplicationException("duplicate_category_name", "Duplicate category name");
		}

		Category category = categoryMapper.categoryRequestDTOToCategory(request);

		categoryRepository.save(category);
	}

	@Override
	public void update(Integer categoryId, CategoryRequestDTO request) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(
				() -> new ApplicationException("category_not_found", "Category not found"));
		Optional<Category> categoryByName = categoryRepository.findByName(request.getName());

		if (categoryByName.isPresent() && categoryByName.get().getId() != categoryId) {
			throw new ApplicationException("duplicate_category_name", "Duplicate category name");
		}

		category.setName(request.getName());
		category.setDisplayOrder(request.getDisplayOrder());

		categoryRepository.save(category);
	}

	@Override
	public void delete(Integer id) {
		Category category = categoryRepository.findById(id).orElseThrow(
				() -> new ApplicationException("category_not_found", "Category not found"));
		categoryRepository.delete(category);
	}

	@Override
	public Long count() {
		return categoryRepository.count();
	}

}
