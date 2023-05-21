package vn.trinhtung.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordRequestDTO {
    @Email(message = "Email không đúng định dạng")
    private String email;
    @NotBlank(message = "Mã xác nhận không được để trống")
    private String token;
    @NotBlank(message = "Mật khẩu không được để trống")
    private String newPassword;
}
