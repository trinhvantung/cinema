package vn.trinhtung.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ResetPasswordToken {
	@Id
	private String resetPasswordToken;
	private LocalDateTime resetPasswordExpire;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
