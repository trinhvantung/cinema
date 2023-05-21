package vn.trinhtung.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.trinhtung.dto.HallRequestDTO;
import vn.trinhtung.dto.HallResponseDTO;
import vn.trinhtung.service.HallService;

import javax.validation.Valid;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RequestMapping("/halls")
@RestController
public class HallController {
    private final HallService hallService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(params = {"page", "sort"})
    public Page<HallResponseDTO> getAll(@RequestParam Integer page, @RequestParam String sort) {
        return hallService.getAll(page, sort);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<HallResponseDTO> getAll() {
        return hallService.getAll();
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SCOPE_server')")
    @GetMapping("/{hallId}")
    public HallResponseDTO getById(@PathVariable Integer hallId) {
        return hallService.getById(hallId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public HallResponseDTO create(@Valid @RequestBody HallRequestDTO hall) {
        return hallService.create(hall);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{hallId}")
    public ResponseEntity<?> update(@Valid @RequestBody HallRequestDTO hall, @PathVariable Integer hallId) {
        hallService.update(hallId, hall);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{hallId}")
    public ResponseEntity<?> delete(@PathVariable Integer hallId) {
        hallService.delete(hallId);
        return ResponseEntity.noContent().build();
    }

}
