package vn.trinhtung.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowPriceRequestDTO {
	@NotNull(message = "Loại ghế không được để trống")
	private Integer seatTypeId;

	@NotNull(message = "Giá không được để trống")
	private BigDecimal price;
}
