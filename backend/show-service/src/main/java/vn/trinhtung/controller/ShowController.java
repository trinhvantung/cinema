package vn.trinhtung.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.trinhtung.dto.*;
import vn.trinhtung.service.ShowService;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RequiredArgsConstructor
@RestController
@RequestMapping("/shows")
public class ShowController {
    private final ShowService showService;


    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SCOPE_server')")
    @GetMapping("/{showId}")
    public ShowResponseDTO getById(@PathVariable Integer showId) {

        return showService.getById(showId);
    }

    @GetMapping("/detail/{showId}")
    public ShowDetailResponseDTO getDetailShow(@PathVariable Integer showId) {
        return showService.getDetail(showId);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
//            (params = {"page"})
    public Page<ShowResponseDTO> getAll(@RequestParam Integer page,
                                        ShowFilterDTO showFilter, @RequestParam String sort) {
        return showService.getAll(page, showFilter, sort);
    }

    @GetMapping(value = "/cinema/{cinemaId}", params = {"page", "date"})
    public Page<MovieShowResponseDTO> getAllShowByCinema(@PathVariable Integer cinemaId,
                                                         @RequestParam Integer page,
                                                         @RequestParam @Valid @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$") String date) {

        return showService.getAllByCinema(page, cinemaId, date);
    }

    @GetMapping(value = "/movie/{movieId}", params = {"page", "date"})
    public Page<CinemaShowResponseDTO> getAllShowByMovie(@PathVariable Integer movieId,
                                                         @RequestParam Integer page,
                                                         @RequestParam @Valid @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$") String date) {

        return showService.getAllByMovie(page, movieId, date);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody @Valid ShowRequestDTO show) {
        showService.create(show);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{showId}")
    public void update(@PathVariable Integer showId,
                       @RequestBody @Valid ShowRequestDTO show) {
        showService.update(showId, show);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{showId}")
    public void delete(@PathVariable Integer showId) {
        showService.delete(showId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/count")
    public Long count() {
        return showService.count();
    }
}
