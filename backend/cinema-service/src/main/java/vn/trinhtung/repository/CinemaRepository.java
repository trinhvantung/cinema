package vn.trinhtung.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.trinhtung.entity.Cinema;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Integer> {
	Optional<Cinema> findByName(String name);

	Page<Cinema> findAllByCityId(Integer cityId, Pageable pageable);
}
