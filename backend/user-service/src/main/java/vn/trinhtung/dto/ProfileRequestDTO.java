package vn.trinhtung.dto;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequestDTO {
	@Length(min = 5, max = 50, message = "Họ tên có độ dài từ 5 đến 50 ký tự")
	private String fullName;
}
