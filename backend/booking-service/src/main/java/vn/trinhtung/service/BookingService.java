package vn.trinhtung.service;

import org.springframework.data.domain.Page;
import vn.trinhtung.dto.*;
import vn.trinhtung.event.PaymentCompletedEvent;
import vn.trinhtung.event.RefundResultForCancellingEvent;
import vn.trinhtung.event.RefundedEvent;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BookingService {
	Page<BookingResponseDTO> getAll(Integer page);

	Page<BookingResponseDTO> getAllByUser(String userId, Integer page);

	BookingDetailResponseDTO getDetailByUser(String userId, Integer bookingId);

	BookingDetailResponseDTO getDetail(Integer bookingId);

	CreateBookingResponseDTO create(String userId, BookingRequestDTO booking);

	void delete(Integer id);

	void handlePaymentResult(PaymentCompletedEvent event);

	void handleRefundResult(RefundedEvent event);

	void handleRefundResultForCancelling(RefundResultForCancellingEvent event);

	void cancelBooking(String userId, Integer bookingId);

    List<BookingItemResponseDTO> getAllBookedSeatsByShow(Integer showId);

	List<TopRevenueByMoveResponseDTO> getTopRevenueByMovie(LocalDate start, LocalDate end, Integer limit);

	List<OrderStatisticResponseDTO> getOrderStatistic(LocalDate start, LocalDate end);

	Long count();

	Map<String, Object> generateTicketQrCode(Integer bookingId, String userId);

	BookingDetailResponseDTO activeTicket(String ticketCode);
}
