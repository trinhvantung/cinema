package vn.trinhtung.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.trinhtung.dto.CategoryResponseDTO;
import vn.trinhtung.dto.MovieRequestDTO;
import vn.trinhtung.dto.MovieResponseDTO;
import vn.trinhtung.entity.Category;
import vn.trinhtung.entity.Movie;
import vn.trinhtung.grpc.CategoryResponse;
import vn.trinhtung.grpc.MovieResponse;
import vn.trinhtung.utils.TimestampUtil;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class MovieMapper {
	private final CategoryMapper categoryMapper;

	public MovieResponseDTO movieToMovieResponseDTO(Movie movie) {
		List<CategoryResponseDTO> categories = movie.getCategories().stream()
				.map(categoryMapper::categoryToCategoryResponseDTO).collect(Collectors.toList());

		return MovieResponseDTO.builder().id(movie.getId()).duration(movie.getDuration())
				.description(movie.getDescription()).name(movie.getName())
				.thumbnail(movie.getThumbnail()).releaseDate(movie.getReleaseDate())
				.categories(categories).build();
	}

	public Movie movieRequestDTOToMovie(MovieRequestDTO movie) {
		List<Category> categories = movie.getCategories().stream()
				.map(categoryId -> Category.builder().id(categoryId).build()).collect(Collectors.toList());

		return Movie.builder().duration(movie.getDuration()).description(movie.getDescription())
				.name(movie.getName())
				.releaseDate(movie.getReleaseDate()).categories(categories).build();
	}

	public MovieResponse movieToMovieResponse(Movie movie) {
		List<CategoryResponse> categories = movie.getCategories().stream()
				.map(categoryMapper::categoryToCategoryResponse).collect(Collectors.toList());

		return MovieResponse.newBuilder()
				.setId(movie.getId())
				.addAllCategory(categories)
				.setDuration(movie.getDuration())
				.setDescription(movie.getDescription())
				.setName(movie.getName())
				.setReleaseDate(TimestampUtil.localDateToTimestamp(movie.getReleaseDate()))
				.setThumbnail(movie.getThumbnail())
				.build();
	}
}
