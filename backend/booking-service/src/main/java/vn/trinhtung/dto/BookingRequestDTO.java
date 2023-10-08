package vn.trinhtung.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDTO {
	@NotNull
	private Integer showId;

	@NotBlank
	private String fullName;

	@Email
	private String email;

	@NotBlank
	private String phoneNumber;

	@NotEmpty
	private List<@Valid BookingItemRequestDTO> bookingItems;
}
