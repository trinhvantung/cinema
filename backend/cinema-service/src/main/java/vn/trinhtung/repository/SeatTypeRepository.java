package vn.trinhtung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.trinhtung.entity.SeatType;

import java.util.Optional;

@Repository
public interface SeatTypeRepository extends JpaRepository<SeatType, Integer> {
	Optional<SeatType> findByName(String name);
}
