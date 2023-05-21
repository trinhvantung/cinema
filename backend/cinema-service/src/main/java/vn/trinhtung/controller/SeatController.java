package vn.trinhtung.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.trinhtung.dto.SeatRequestDTO;
import vn.trinhtung.service.SeatService;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RequestMapping("/seats")
@RestController
public class SeatController {
    private final SeatService seatService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/hall/{hallId}")
    public ResponseEntity<?> getAllByHall(@PathVariable Integer hallId) {
        return ResponseEntity.ok(seatService.getAllByHall(hallId));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/hall/{hallId}")
    public ResponseEntity<?> saveSeat(@PathVariable Integer hallId,
                                      @RequestBody List<SeatRequestDTO> seatRequestDTOs) {
        seatService.save(hallId, seatRequestDTOs);
        return ResponseEntity.ok().build();
    }
}
