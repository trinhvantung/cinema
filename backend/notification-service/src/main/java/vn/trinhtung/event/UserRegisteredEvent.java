package vn.trinhtung.event;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisteredEvent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String fullName;
	private String urlVerify;
}
