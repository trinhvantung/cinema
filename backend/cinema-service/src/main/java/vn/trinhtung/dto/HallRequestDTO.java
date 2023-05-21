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
public class HallRequestDTO {
	@NotBlank(message = "Tên phòng không được để trống")
	private String name;
	@NotNull(message = "Rạp không được để trống")
	private Integer cinemaId;
}
