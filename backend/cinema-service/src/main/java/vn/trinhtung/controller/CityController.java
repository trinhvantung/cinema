package vn.trinhtung.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import vn.trinhtung.dto.CityRequestDTO;
import vn.trinhtung.dto.CityResponseDTO;
import vn.trinhtung.service.CityService;

@Validated
@RequiredArgsConstructor
@RequestMapping("/cities")
@RestController
public class CityController {
    private final CityService cityService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{cityId}")
    public CityResponseDTO getById(@PathVariable Integer cityId) {
        return cityService.getById(cityId);
    }

    @GetMapping
    public List<CityResponseDTO> getAll() {
        return cityService.getAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(params = {"page"})
    public Page<CityResponseDTO> getAll(@RequestParam Integer page) {
        return cityService.getAll(page);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CityRequestDTO city) {
        cityService.createCity(city);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{cityId}")
    public ResponseEntity<?> update(@Valid @RequestBody CityRequestDTO city,
                                    @PathVariable Integer cityId) {
        cityService.updateCity(cityId, city);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{cityId}")
    public ResponseEntity<?> delete(@PathVariable Integer cityId) {
        cityService.deleteCity(cityId);
        return ResponseEntity.noContent().build();
    }
}
