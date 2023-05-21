package vn.trinhtung.mapper;

import org.springframework.stereotype.Component;

import vn.trinhtung.dto.BookingResponseDTO;
import vn.trinhtung.entity.Booking;

@Component
public class BookingMapper {
	public BookingResponseDTO bookingToBookingResponseDTO(Booking booking) {
		return BookingResponseDTO.builder().id(booking.getId()).totalPrice(booking.getTotalPrice())
				.movieId(booking.getMovieId())
				.createdAt(booking.getCreatedAt()).status(booking.getStatus()).build();
	}
}
