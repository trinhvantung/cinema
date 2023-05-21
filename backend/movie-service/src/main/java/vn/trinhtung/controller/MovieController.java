package vn.trinhtung.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import vn.trinhtung.dto.CategoryMovieCountResponseDTO;
import vn.trinhtung.dto.MovieRequestDTO;
import vn.trinhtung.dto.MovieResponseDTO;
import vn.trinhtung.service.MovieService;

@Validated
@RequiredArgsConstructor
@RequestMapping("/movies")
@RestController
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/{movieId}")
    public MovieResponseDTO getById(@PathVariable Integer movieId) {
        return movieService.getById(movieId);
    }

    @GetMapping(params = {"page"})
    public Page<MovieResponseDTO> getAll(@RequestParam Integer page) {
        return movieService.getAll(page);
    }

    @GetMapping
    public List<MovieResponseDTO> getAll() {
        return movieService.getAll();
    }


    @GetMapping(value = "/category/{categoryId}", params = {"page"})
    public Page<MovieResponseDTO> getAllByCategory(@PathVariable Integer categoryId, @RequestParam Integer page) {
        return movieService.getAllByCategory(categoryId, page);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestPart MultipartFile thumbnailFile,
                                    @RequestPart MovieRequestDTO movie) {
        movieService.create(thumbnailFile, movie);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{movieId}")
    public ResponseEntity<?> update(@RequestPart(required = false) MultipartFile thumbnailFile,
                                    @PathVariable Integer movieId, @RequestPart MovieRequestDTO movie) {
        movieService.update(movieId, thumbnailFile, movie);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{movieId}")
    public ResponseEntity<?> delete(@PathVariable Integer movieId) {
        movieService.delete(movieId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/count/category")
    public List<CategoryMovieCountResponseDTO> countMovieByCategory() {
        return movieService.countByCategory();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/count")
    public Long count() {
        return movieService.count();
    }

}
