package vn.trinhtung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.trinhtung.entity.BookingItem;
import vn.trinhtung.entity.BookingStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingItemRepository extends JpaRepository<BookingItem, Integer> {
    @Query("SELECT i FROM BookingItem i WHERE i.id = :id AND i.booking.userId = :userId")
    Optional<BookingItem> findByIdAndUserId(Integer id, String userId);

    @Query("select count(bi) from BookingItem bi where bi.showId = :showId " +
            "and bi.seatId in :seatIds and bi.booking.status = :status")
    Integer countBySeatIdAndShowIdAndStatus(Integer showId, List<Integer> seatIds, BookingStatus status);

    @Query("select bi from BookingItem bi where bi.booking.showId = :showId and bi.booking.status = 'COMPLETED'")
    List<BookingItem> findAllBookedByShowId(Integer showId);
}
