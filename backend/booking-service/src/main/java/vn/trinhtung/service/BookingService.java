package vn.trinhtung.service;

import org.springframework.data.domain.Page;

import vn.trinhtung.dto.*;
import vn.trinhtung.event.PaymentCompletedEvent;
import vn.trinhtung.event.RefundResultForCancellingEvent;
import vn.trinhtung.event.RefundedEvent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {
	Page<BookingResponseDTO> getAll(Integer page);

	Page<BookingResponseDTO> getAllByUser(Integer userId, Integer page);

	BookingDetailResponseDTO getDetailByUser(Integer userId, Integer bookingId);

	BookingDetailResponseDTO getDetail(Integer bookingId);

	CreateBookingResponseDTO create(Integer userId, BookingRequestDTO booking);

	void delete(Integer id);

	void handlePaymentResult(PaymentCompletedEvent event);

	void handleRefundResult(RefundedEvent event);

	void handleRefundResultForCancelling(RefundResultForCancellingEvent event);

	void cancelBooking(Integer userId, Integer bookingId);

    List<BookingItemResponseDTO> getAllBookedSeatsByShow(Integer showId);

	List<TopRevenueByMoveResponseDTO> getTopRevenueByMovie(LocalDate start, LocalDate end, Integer limit);

	List<OrderStatisticResponseDTO> getOrderStatistic(LocalDate start, LocalDate end);

	Long count();
}
