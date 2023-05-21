package vn.trinhtung.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

	private LocalDate releaseDate;

	private List<CategoryResponseDTO> categories;

	public String getThumbnailUrl() {
		return "https://res.cloudinary.com/tung071201/image/upload/v1651760535/" + thumbnail;
	}
}
