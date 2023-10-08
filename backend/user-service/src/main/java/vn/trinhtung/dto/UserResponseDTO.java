package vn.trinhtung.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserResponseDTO {
	private Integer id;
	private String fullName;
	private String email;
	private boolean nonLock;
	private boolean enable;
}
