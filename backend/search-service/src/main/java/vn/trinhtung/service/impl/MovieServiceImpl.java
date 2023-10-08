package vn.trinhtung.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.trinhtung.dto.MovieResponseDTO;
import vn.trinhtung.dto.MovieSearchResponseDTO;
import vn.trinhtung.model.Movie;
import vn.trinhtung.repository.MovieElasticSearchRepository;
import vn.trinhtung.service.MovieService;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieElasticSearchRepository movieElasticSearchRepository;

    @Override
    public Page<MovieSearchResponseDTO> search(String query, Integer page) {
        Pageable pageable = PageRequest.of(page -1, 10);


        return movieElasticSearchRepository.search(query, pageable).map(movie -> MovieSearchResponseDTO.builder()
                .id(movie.getId()).duration(movie.getDuration())
                .description(movie.getDescription())
                .name(movie.getName())
                .thumbnail(movie.getThumbnail())
                .build());
    }

    @Override
    public void save(MovieResponseDTO movie) {
        movieElasticSearchRepository.save(Movie.builder()
                .name(movie.getName())
                .thumbnail(movie.getThumbnail())
                .duration(movie.getDuration())
                .description(movie.getDescription())
                .id(movie.getId().toString())
                .build());
    }

    @Override
    public void deleteById(Integer movieId) {
        movieElasticSearchRepository.deleteById(movieId.toString());
    }
}
