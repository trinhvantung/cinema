package vn.trinhtung.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingItemResponseDTO {
	private Integer id;

	private SeatResponseDTO seat;

	private BigDecimal price;
}
