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
public class CityRequestDTO {
	@NotBlank(message = "Tên thành phố không được để trống")
	private String name;

	private Integer displayOrder;
}
