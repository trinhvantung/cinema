package vn.trinhtung.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatRequestDTO {
	private Integer id;
	@NotNull(message = "Hàng không được để trống")
	private Integer rowIndex;
	@NotNull(message = "Cột không được để trống")
	private Integer columnIndex;
	@NotBlank(message = "Tên ghế không được để trống")
	private String name;
	@NotNull(message = "Loại ghế không được để trống")
	private Integer seatTypeId;
}
