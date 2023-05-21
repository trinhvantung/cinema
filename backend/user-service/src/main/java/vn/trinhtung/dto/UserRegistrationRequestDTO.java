package vn.trinhtung.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequestDTO {
	@Length(min = 5, max = 50, message = "Họ tên có độ dài từ 5 đến 50 ký tự")
	private String fullName;

	@Email(message = "Email không đúng định dạng")
	private String email;

	@Length(min = 5, message = "Mật khẩu phải có độ dài từ 5 ký tự")
	private String password;
}
