package vn.trinhtung.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cinema {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 50, unique = true)
	private String name;
	private Float latitude;
	private Float longitude;
	@Column(unique = true)
	private String address;

	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;

	@OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL)
	private List<Hall> halls;
}
