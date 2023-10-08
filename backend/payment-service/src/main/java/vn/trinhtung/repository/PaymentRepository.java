package vn.trinhtung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.trinhtung.entity.Payment;
import vn.trinhtung.entity.PaymentStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Optional<Payment> findByBookingIdAndPaymentStatus(Integer bookingId, PaymentStatus paymentStatus);

    @Query("SELECT SUM(p.amount) as amount, FUNCTION('date_format', p.createDate, '%Y-%m') as time FROM Payment p " +
            "where p.createDate between ?1 and ?2 group by time")
    List<Map<String, Object>> sumAmountGroupByMonth(LocalDateTime start, LocalDateTime end);

//    @Query("select sum(p.amount) as amount,  from Payment p")
//    List<Map<String, Object>> sumAmountGroupByWeek(LocalDateTime start, LocalDateTime end);
}
