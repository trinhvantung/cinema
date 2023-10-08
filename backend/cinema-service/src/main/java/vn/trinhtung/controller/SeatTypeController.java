package vn.trinhtung.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.trinhtung.dto.SeatTypeRequestDTO;
import vn.trinhtung.dto.SeatTypeResponseDTO;
import vn.trinhtung.service.SeatTypeService;

import javax.validation.Valid;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RequestMapping("/seat-types")
@RestController
public class SeatTypeController {
    private final SeatTypeService seatTypeService;

    @GetMapping
    public List<SeatTypeResponseDTO> getAll() {
        return seatTypeService.getAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{seatTypeId}")
    public SeatTypeResponseDTO getById(@PathVariable Integer seatTypeId) {
        return seatTypeService.getById(seatTypeId);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/hall/{hallId}")
    public List<SeatTypeResponseDTO> getAllByHall(@PathVariable Integer hallId) {
        return seatTypeService.getAllByHall(hallId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@Valid @RequestBody SeatTypeRequestDTO seatType) {
        seatTypeService.create(seatType);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{seatTypeId}")
    public void update(@Valid @RequestBody SeatTypeRequestDTO seatType,
                       @PathVariable Integer seatTypeId) {
        seatTypeService.update(seatTypeId, seatType);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{seatTypeId}")
    public void delete(@PathVariable Integer seatTypeId) {
        seatTypeService.delete(seatTypeId);
    }
}
