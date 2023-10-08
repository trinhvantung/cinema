package vn.trinhtung.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatTypeRequestDTO {
	@NotBlank(message = "Tên loại ghế không được để trống")
	private String name;
	@NotBlank(message = "Màu loại ghế không được để trống")
	private String color;
}
