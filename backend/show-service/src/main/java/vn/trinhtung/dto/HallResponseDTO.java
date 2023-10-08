package vn.trinhtung.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HallResponseDTO {
	private Integer id;
	private String name;
	private CinemaResponseDTO cinema;
}
