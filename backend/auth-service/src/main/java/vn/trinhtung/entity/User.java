package vn.trinhtung.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String fullName;
	private String email;
	private String password;
	private String phoneNumber;
	private boolean enable;
	private boolean nonLock;
	private String activationCode;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_roles", joinColumns = @JoinColumn(
					name = "user_id"
			), inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private List<Role> roles;

}