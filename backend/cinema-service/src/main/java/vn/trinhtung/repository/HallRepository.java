package vn.trinhtung.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.trinhtung.entity.Hall;
import vn.trinhtung.entity.SeatType;

@Repository
public interface HallRepository extends JpaRepository<Hall, Integer> {
    Optional<Hall> findByNameAndCinemaId(String name, Integer cinemaId);

    @Query("SELECT DISTINCT seat.seatType FROM Hall hall JOIN hall.seats seat " +
            "WHERE hall.id = :hallId and seat.seatType.id <> 3")
    List<SeatType> findAllSeatTypesByHallId(Integer hallId);
}
