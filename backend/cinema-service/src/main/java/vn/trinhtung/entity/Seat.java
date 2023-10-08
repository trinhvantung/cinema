package vn.trinhtung.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "name", "hall_id" }))
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer rowIndex;
	private Integer columnIndex;
	private String name;

	@ManyToOne
	@JoinColumn(name = "hall_id")
	private Hall hall;

	@ManyToOne
	@JoinColumn(name = "seat_type_id")
	private SeatType seatType;

}
