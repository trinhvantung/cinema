package vn.trinhtung.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowResponseDTO {
	private Integer id;
	private LocalDateTime start;
	private LocalDateTime end;
	private Integer movieId;
	private Integer hallId;
	private Integer cinemaId;
	private List<ShowPriceResponseDTO> showPrices;
	private HallResponseDTO hall;

	@JsonInclude(value = Include.NON_NULL)
	private MovieResponseDTO movie;
}
