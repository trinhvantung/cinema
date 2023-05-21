package vn.trinhtung.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.trinhtung.dto.MovieResponseDTO;
import vn.trinhtung.dto.MovieSearchResponseDTO;
import vn.trinhtung.service.MovieService;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/search")
    public Page<MovieSearchResponseDTO> searchMovie(@RequestParam String query, @RequestParam Integer page) {
        return movieService.search(query,page);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/save")
    public void save(@RequestBody MovieResponseDTO movie) {
        movieService.save(movie);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer id) {
        movieService.deleteById(id);
    }
}
