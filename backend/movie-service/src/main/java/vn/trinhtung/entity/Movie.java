package vn.trinhtung.entity;

import java.time.LocalDate;
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
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true)
	private String name;

	private Integer duration;

	@Column(columnDefinition = "text")
	private String description;

	private String thumbnail;
	
	private LocalDate releaseDate;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "movie_category", joinColumns = @JoinColumn(
					name = "movie_id"
			), inverseJoinColumns = @JoinColumn(name = "category_id")
	)
	private List<Category> categories;
}
