package vn.trinhtung.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.trinhtung.dto.CinemaRequestDTO;
import vn.trinhtung.dto.CinemaResponseDTO;
import vn.trinhtung.service.CinemaService;

import javax.validation.Valid;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RequestMapping("/cinemas")
@RestController
public class CinemaController {
    private final CinemaService cinemaService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(params = {"page"})
    public Page<CinemaResponseDTO> getAll(@RequestParam Integer page) {
        return cinemaService.getAll(page);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<CinemaResponseDTO> getAll() {
        return cinemaService.getAll();
    }

    @GetMapping("/city/{cityId}")
    public Page<CinemaResponseDTO> getAllByCity(@RequestParam Integer page, @PathVariable Integer cityId) {
        return cinemaService.getAllByCity(cityId, page);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{cinemaId}")
    public CinemaResponseDTO getById(@PathVariable Integer cinemaId) {
        return cinemaService.getById(cinemaId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CinemaRequestDTO cinema) {
        cinemaService.createCinema(cinema);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{cinemaId}")
    public ResponseEntity<?> update(@Valid @RequestBody CinemaRequestDTO cinema,
                                    @PathVariable Integer cinemaId) {
        cinemaService.updateCinema(cinemaId, cinema);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{cinemaId}")
    public ResponseEntity<?> delete(@PathVariable Integer cinemaId) {
        cinemaService.delete(cinemaId);
        return ResponseEntity.noContent().build();
    }
}
