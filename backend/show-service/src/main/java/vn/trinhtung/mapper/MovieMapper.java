package vn.trinhtung.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.trinhtung.dto.CategoryResponseDTO;
import vn.trinhtung.dto.MovieResponseDTO;
import vn.trinhtung.grpc.MovieResponse;
import vn.trinhtung.utils.TimestampUtil;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class MovieMapper {
    private final CategoryMapper categoryMapper;


    public MovieResponseDTO movieResponseToMovieResponseDTO(MovieResponse movie) {
        List<CategoryResponseDTO> categories = movie.getCategoryList().stream()
                .map(categoryMapper::categoryResponseToCategoryResponseDTO).collect(Collectors.toList());

        return MovieResponseDTO.builder()
                .id(movie.getId())
                .categories(categories)
                .duration(movie.getDuration())
                .description(movie.getDescription())
                .name(movie.getName())
                .releaseDate(TimestampUtil.timestampToLocalDate(movie.getReleaseDate()))
                .thumbnail(movie.getThumbnail())
                .build();
    }
}
