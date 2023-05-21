package vn.trinhtung.dto;

import java.util.List;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    @Length(min = 5, max = 50, message = "Họ tên có độ dài từ 5 đến 50 ký tự")
    private String fullName;

    private String password;

    @Email(message = "Email không đúng định dạng")
    private String email;
    private List<RoleRequestDTO> roles;
    private boolean nonLock;
}
