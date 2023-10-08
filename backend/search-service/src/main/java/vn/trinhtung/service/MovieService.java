package vn.trinhtung.service;

import org.springframework.data.domain.Page;
import vn.trinhtung.dto.MovieResponseDTO;
import vn.trinhtung.dto.MovieSearchResponseDTO;

public interface MovieService {
    Page<MovieSearchResponseDTO> search(String query, Integer page);

    void save(MovieResponseDTO movie);

    void deleteById(Integer movieId);
}
