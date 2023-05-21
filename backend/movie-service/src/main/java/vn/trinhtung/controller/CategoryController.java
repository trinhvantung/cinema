package vn.trinhtung.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import vn.trinhtung.dto.CategoryRequestDTO;
import vn.trinhtung.dto.CategoryResponseDTO;
import vn.trinhtung.service.CategoryService;

@Validated
@RequiredArgsConstructor
@RequestMapping("/categories")
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponseDTO> getAll() {
        return categoryService.getAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(params = {"page"})
    public Page<CategoryResponseDTO> getAll(@RequestParam Integer page) {
        return categoryService.getAll(page);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{categoryId}")
    public CategoryResponseDTO getById(@PathVariable Integer categoryId) {
        return categoryService.getById(categoryId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CategoryRequestDTO category) {
        categoryService.create(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{categoryId}")
    public ResponseEntity<?> update(@PathVariable Integer categoryId,
                                    @RequestBody CategoryRequestDTO category) {
        categoryService.update(categoryId, category);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> delete(@PathVariable Integer categoryId) {
        categoryService.delete(categoryId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/count")
    public Long count() {
        return categoryService.count();
    }
}
