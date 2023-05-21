package vn.trinhtung.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieShowResponseDTO {
	private MovieResponseDTO movie;
	private List<ShowResponseDTO> shows;
}
