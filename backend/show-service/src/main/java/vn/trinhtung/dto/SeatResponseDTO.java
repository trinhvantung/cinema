package vn.trinhtung.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatResponseDTO {
	private Integer id;
	private Integer rowIndex;
	private Integer columnIndex;
	private String name;
	private SeatTypeResponseDTO seatType;
}
