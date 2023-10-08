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
public class ShowPriceResponseDTO {
	private Integer showId;
	private Integer seatTypeId;
	private BigDecimal price;
}
