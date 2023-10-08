package vn.trinhtung.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import vn.trinhtung.dto.*;
import vn.trinhtung.service.BookingService;
import vn.trinhtung.util.ExcelExporter;
import vn.trinhtung.util.OrderStatisticExcelExporter;
import vn.trinhtung.util.TopRevenueByMovieExcelExporter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(params = {"page"})
    public Page<BookingResponseDTO> getAll(@RequestParam Integer page) {

        return bookingService.getAll(page);
    }

    @GetMapping("/seats/booked/show/{showId}")
    public List<BookingItemResponseDTO> getAllBookedSeatsByShow(@PathVariable Integer showId) {
        return bookingService.getAllBookedSeatsByShow(showId);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/current-user")
    public Page<BookingResponseDTO> getAllByCurrentUser(@RequestParam Integer page,
                                                        @AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getClaimAsString("sub");

        return bookingService.getAllByUser(userId, page);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{bookingId}/current-user")
    public BookingDetailResponseDTO getDetailByCurrentUser(
            @PathVariable Integer bookingId,
            @AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getClaimAsString("sub");

        return bookingService.getDetailByUser(userId, bookingId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{bookingId}")
    public BookingDetailResponseDTO getDetail(@PathVariable Integer bookingId) {
        return bookingService.getDetail(bookingId);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/cancelling/{bookingId}")
    public void cancel(@PathVariable Integer bookingId, @AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getClaimAsString("sub");
        bookingService.cancelBooking(userId, bookingId);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public CreateBookingResponseDTO create(@RequestBody BookingRequestDTO booking, @AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getClaimAsString("sub");

        return bookingService.create(userId, booking);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{bookingId}")
    public void delete(@PathVariable Integer bookingId) {
        bookingService.delete(bookingId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/top/revenue/movie")
    public List<TopRevenueByMoveResponseDTO> getTopRevenueByMovie(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                                                                  @RequestParam(defaultValue = "5") Integer limit) {

        return bookingService.getTopRevenueByMovie(start, end, limit);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/top/revenue/movie/export")
    public void exportTopRevenueByMovie(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                                        @RequestParam(defaultValue = "5") Integer limit,
                                        HttpServletResponse response) {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=revenue_by_movie_" + start.toString() + "_" + end.toString() + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<TopRevenueByMoveResponseDTO> data = bookingService.getTopRevenueByMovie(start, end, limit);

        ExcelExporter exporter = new TopRevenueByMovieExcelExporter(data);
        try {
            exporter.export(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/statistic/order")
    public List<OrderStatisticResponseDTO> getOrderStatistic(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {

        return bookingService.getOrderStatistic(start, end);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/statistic/order/export")
    public void exportOrderStatistic(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                                     HttpServletResponse response) {

        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=order_" + start.toString() + "_" + end.toString() + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<OrderStatisticResponseDTO> data = bookingService.getOrderStatistic(start, end);

        ExcelExporter exporter = new OrderStatisticExcelExporter(data);
        try {
            exporter.export(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @GetMapping("/ticket/qr/{bookingId}")
    public ResponseEntity<?> generateTicketQrCode(@PathVariable Integer bookingId,
                                                  @AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getClaimAsString("sub");

        Map<String, Object> result = bookingService.generateTicketQrCode(bookingId, userId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Qr-Expire", ((LocalDateTime) result.get("expire")).toString());


        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.IMAGE_PNG)
                .body(result.get("qr"));
    }

    @PutMapping("/ticket/active/{ticketCode}")
    public BookingDetailResponseDTO activeTicket(@PathVariable String ticketCode) {
        return bookingService.activeTicket(ticketCode);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/count")
    public Long count() {
        return bookingService.count();
    }
}
