package vn.trinhtung.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`show`")
public class Show {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private LocalDateTime start;

	@Column(nullable = false)
	private LocalDateTime end;

	@Column(nullable = false)
	private Integer movieId;

	@Column(nullable = false)
	private Integer hallId;

	@Column(nullable = false)
	private Integer cinemaId;

	@OneToMany(mappedBy = "show", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<ShowPrice> showPrices;
}
