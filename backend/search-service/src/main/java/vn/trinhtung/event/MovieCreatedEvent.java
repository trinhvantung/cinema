package vn.trinhtung.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.trinhtung.dto.MovieResponseDTO;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieCreatedEvent {
    private MovieResponseDTO movie;
}
