package vn.trinhtung.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.trinhtung.entity.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
	List<Seat> findAllByHallId(Integer hallId);
}
