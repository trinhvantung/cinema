package vn.trinhtung.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowPriceResponseDTO {
	private Integer showId;
	private Integer seatTypeId;
	private BigDecimal price;
}
