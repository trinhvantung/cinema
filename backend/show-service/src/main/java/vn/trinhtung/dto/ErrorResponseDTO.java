package vn.trinhtung.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {
	private String error;
	private String message;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Object data;

	public ErrorResponseDTO(String error, String message) {
		this.error = error;
		this.message = message;
	}
}
