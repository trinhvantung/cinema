package vn.trinhtung.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowDetailResponseDTO {
	private Integer id;
	private LocalDateTime start;
	private LocalDateTime end;
	private MovieResponseDTO movie;
	private HallResponseDTO hall;
	private List<SeatResponseDTO> seats;
	private List<ShowPriceResponseDTO> showPrices;
}
