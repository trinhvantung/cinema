package vn.trinhtung.dto;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordRequestDTO {
	@Length(min = 5, message = "Mật khẩu phải có độ dài từ 5 ký tự")
	private String currentPassword;
	@Length(min = 5, message = "Mật khẩu phải có độ dài từ 5 ký tự")
	private String newPassword;
}
