package vn.trinhtung.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.trinhtung.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
	Optional<City> findByName(String name);
}
