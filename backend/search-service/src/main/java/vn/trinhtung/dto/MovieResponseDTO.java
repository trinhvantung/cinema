package vn.trinhtung.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponseDTO {
	private Integer id;

	private String name;

	private Integer duration;

	private String description;

	private String thumbnail;

//	private LocalDate releaseDate;

}
