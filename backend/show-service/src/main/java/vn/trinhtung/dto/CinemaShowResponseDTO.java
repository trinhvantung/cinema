package vn.trinhtung.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CinemaShowResponseDTO {
    private CinemaResponseDTO cinema;
    private List<ShowResponseDTO> shows;
}
