package vn.trinhtung.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.trinhtung.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>, JpaSpecificationExecutor<Booking> {
    Page<Booking> findAllByUserId(String userId, Pageable pageable);

    Optional<Booking> findByIdAndUserId(Integer id, String userId);

    @Query("select b from Booking b join  fetch b.bookingItems where b.id = :id")
    Optional<Booking> findBookingAndBookingItemById(Integer id);

    @Query("select sum(b.totalPrice) as amount, b.movieId as movieId from Booking b " +
            "where b.createdAt between ?1 and ?2 group by movieId order by amount desc")
    List<Map<String, Object>> getTopRevenueByMovie(LocalDateTime start, LocalDateTime end, Integer limit);


    @Query("select count(b) as count, function('date_format', b.createdAt, '%Y-%m') as time from Booking b " +
            "where b.createdAt between ?1 and ?2 group by time")
    List<Map<String, Object>> getOrderStatistic(LocalDateTime start, LocalDateTime end);
}
