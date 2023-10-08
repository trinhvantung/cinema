package vn.trinhtung.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.trinhtung.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
	Optional<Movie> findByName(String name);

	@Query("SELECT DISTINCT m FROM Movie m JOIN m.categories c WHERE c.id = :categoryId")
	Page<Movie> findAllByCategoryId(Integer categoryId, Pageable pageable);

	@Query("select count(m) as count, c.name as categoryName, c.id as categoryId from Movie m join m.categories as c " +
			"group by categoryId")
	List<Map<String, Object>> countByCategory();
}
