package vn.trinhtung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.trinhtung.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
