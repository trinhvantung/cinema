package vn.trinhtung.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer showId;

	private BigDecimal totalPrice;

	private String userId;

	private String fullName;

	private String email;

	private String phoneNumber;

	private Integer movieId;

	@Enumerated(EnumType.STRING)
	private BookingStatus status;

	@CreatedDate
	private LocalDateTime createdAt;

	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
	private List<BookingItem> bookingItems = new ArrayList<>();
}
