package vn.trinhtung.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import vn.trinhtung.dto.CategoryMovieCountResponseDTO;
import vn.trinhtung.dto.MovieRequestDTO;
import vn.trinhtung.dto.MovieResponseDTO;
import vn.trinhtung.grpc.MovieResponse;

public interface MovieService {
	void create(MultipartFile thumbnailFile, MovieRequestDTO request);

	void update(Integer movieId, MultipartFile thumbnailFile, MovieRequestDTO request);

	void delete(Integer movieId);

	MovieResponseDTO getById(Integer id);

	MovieResponse getMovieById(Integer id);

	Page<MovieResponseDTO> getAll(Integer page);

	Page<MovieResponseDTO> getAllByCategory(Integer categoryId, Integer page);
	
	List<MovieResponse> getMoviesByIds(List<Integer> movieIds);

    List<MovieResponseDTO> getAll();

	List<CategoryMovieCountResponseDTO> countByCategory();

	Long count();
}
