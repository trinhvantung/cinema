package vn.trinhtung.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.apache.commons.lang.RandomStringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.trinhtung.dto.*;
import vn.trinhtung.entity.Booking;
import vn.trinhtung.entity.BookingItem;
import vn.trinhtung.entity.BookingStatus;
import vn.trinhtung.event.*;
import vn.trinhtung.exception.ApplicationException;
import vn.trinhtung.feign.PaymentFeignClient;
import vn.trinhtung.grpc.*;
import vn.trinhtung.mapper.BookingMapper;
import vn.trinhtung.mapper.HallMapper;
import vn.trinhtung.mapper.SeatMapper;
import vn.trinhtung.mapper.ShowMapper;
import vn.trinhtung.repository.BookingItemRepository;
import vn.trinhtung.repository.BookingRepository;
import vn.trinhtung.service.BookingService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BookingItemRepository bookingItemRepository;
    private final BookingMapper bookingMapper;
    private final PaymentFeignClient paymentFeignClient;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final RedissonClient redissonClient;
    private final RedisTemplate<String, Object> redisTemplate;

    private final ShowMapper showMapper;
    private final SeatMapper seatMapper;
    private final HallMapper hallMapper;

    @GrpcClient("showService")
    private ShowServiceGrpc.ShowServiceBlockingStub showServiceBlockingStub;

    @GrpcClient("cinemaService")
    private HallServiceGrpc.HallServiceBlockingStub hallServiceBlockingStub;

    @GrpcClient("cinemaService")
    private SeatServiceGrpc.SeatServiceBlockingStub seatServiceBlockingStub;

    @GrpcClient("movieService")
    private MovieServiceGrpc.MovieServiceBlockingStub movieServiceBlockingStub;


    @Override
    public Page<BookingResponseDTO> getAll(Integer page) {
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        Page<Booking> bookingPage = bookingRepository.findAll(pageable);

        return map(bookingPage);
    }

    @Override
    public Page<BookingResponseDTO> getAllByUser(String userId, Integer page) {
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        Page<Booking> bookingPage = bookingRepository.findAllByUserId(userId, pageable);

        return map(bookingPage);
    }

    private Page<BookingResponseDTO> map(Page<Booking> bookingPage) {
        List<Integer> showIds = bookingPage.stream().map(Booking::getShowId).distinct().collect(Collectors.toList());


        List<ShowResponseDTO> shows = showServiceBlockingStub
                .getShowsByIds(GetShowsByIdsRequest.newBuilder().addAllIds(showIds).build())
                .getShowsList().stream().map(showMapper::showResponseToShowResponseDTO).collect(Collectors.toList());

        Page<BookingResponseDTO> response = bookingPage.map(booking -> {
            BookingResponseDTO temp = bookingMapper.bookingToBookingResponseDTO(booking);
            ShowResponseDTO show = shows.stream().filter(s -> s.getId().equals(booking.getShowId()))
                    .findFirst().orElse(null);
            temp.setShow(show);

            if (booking.getStatus().equals(BookingStatus.COMPLETED) &&
                    LocalDateTime.now().isBefore(show.getStart())) {
                temp.setCanCreateTicket(true);
            }
            temp.setFullName(booking.getFullName());
            temp.setPhoneNumber(booking.getPhoneNumber());
            temp.setEmail(booking.getEmail());
            return temp;
        });

        return response;
    }

    @Override
    public BookingDetailResponseDTO getDetailByUser(String userId, Integer bookingId) {
        Booking booking = bookingRepository.findByIdAndUserId(bookingId, userId).orElseThrow(
                () -> new ApplicationException("booking_not_found", "Booking not found"));
        BookingDetailResponseDTO result = map(booking);

        LocalDateTime now = LocalDateTime.now();

        if (booking.getStatus().equals(BookingStatus.COMPLETED) &&
                now.plusDays(2).isBefore(result.getShowResponse().getStart())) {

            result.setCanCancel(true);
        }
        return result;
    }

    @Override
    public BookingDetailResponseDTO getDetail(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(
                () -> new ApplicationException("booking_not_found", "Booking not found"));

        return map(booking);
    }

    private BookingDetailResponseDTO map(Booking booking) {

        ShowResponseDTO show = showMapper.showResponseToShowResponseDTO(showServiceBlockingStub
                .getShowById(GetShowByIdRequest.newBuilder().setId(booking.getShowId()).build())
                .getShow());


        HallResponseDTO hall = hallMapper.hallResponseToHallResponseDTO(hallServiceBlockingStub
                .getHallById(GetHallByIdRequest.newBuilder().setId(show.getHallId()).build()).getHall());

        List<Integer> seatIds = booking.getBookingItems().stream().map(BookingItem::getSeatId)
                .collect(Collectors.toList());


        List<SeatResponseDTO> seats = seatServiceBlockingStub.getSeatsByIds(GetSeatsByIdsRequest
                .newBuilder().addAllIds(seatIds).build()).getSeatsList()
                .stream().map(seatMapper::seatResponseToSeatResponseDTO).collect(Collectors.toList());

        List<BookingItemResponseDTO> bookingItems = booking.getBookingItems().stream().map(item -> {
            SeatResponseDTO seat = seats.stream().filter(s -> s.getId().equals(item.getSeatId()))
                    .findAny().orElse(null);
            BookingItemResponseDTO temp = new BookingItemResponseDTO(booking.getId(), seat,
                    item.getPrice());

            return temp;
        }).collect(Collectors.toList());

        BookingDetailResponseDTO response = new BookingDetailResponseDTO();
        response.setId(booking.getId());
        response.setCreatedAt(booking.getCreatedAt());
        response.setHall(hall);
        response.setFullName(booking.getFullName());
        response.setPhoneNumber(booking.getPhoneNumber());
        response.setEmail(booking.getEmail());
        response.setShowResponse(show);
        response.setStatus(booking.getStatus());
        response.setTotalPrice(booking.getTotalPrice());
        response.setBookingItems(bookingItems);

        return response;
    }

    @Override
    public CreateBookingResponseDTO create(String userId, BookingRequestDTO bookingRequestDto) {
        Booking booking = new Booking();
        booking.setShowId(bookingRequestDto.getShowId());
        booking.setUserId(userId);
        booking.setFullName(bookingRequestDto.getFullName());
        booking.setEmail(bookingRequestDto.getEmail());
        booking.setPhoneNumber(bookingRequestDto.getPhoneNumber());

        ShowResponseDTO show = showMapper.showResponseToShowResponseDTO(showServiceBlockingStub
                .getShowById(GetShowByIdRequest.newBuilder().setId(bookingRequestDto.getShowId()).build())
                .getShow());

        booking.setMovieId(show.getMovieId());

        if (Objects.isNull(show) || show.getStart().isBefore(LocalDateTime.now())) {
            throw new ApplicationException("show_not_found", "Show not found");
        }

        List<SeatResponseDTO> seatResponseDTOs = seatServiceBlockingStub.getSeatsByIds(GetSeatsByIdsRequest.newBuilder().addAllIds(bookingRequestDto.getBookingItems().stream()
                .map(BookingItemRequestDTO::getSeatId).collect(Collectors.toList())).build()).getSeatsList()
                .stream().map(seatMapper::seatResponseToSeatResponseDTO).collect(Collectors.toList());

        BigDecimal totalPrice = BigDecimal.ZERO;

        for (BookingItemRequestDTO bookingItemRequestDto : bookingRequestDto.getBookingItems()) {

            BookingItem bookingItem = new BookingItem();
            bookingItem.setBooking(booking);
            bookingItem.setSeatId(bookingItemRequestDto.getSeatId());

            SeatResponseDTO seat = seatResponseDTOs.stream()
                    .filter(seatResponseDTO -> seatResponseDTO.getId().equals(bookingItemRequestDto.getSeatId()))
                    .findFirst().orElse(null);

            BigDecimal price = show.getShowPrices().stream().
                    filter(showPriceResponse -> showPriceResponse.getSeatTypeId().equals(seat.getSeatType().getId()))
                    .findFirst().orElse(null)
                    .getPrice();

            bookingItem.setPrice(price);
            bookingItem.setShowId(bookingRequestDto.getShowId());

            totalPrice = totalPrice.add(price);

            booking.getBookingItems().add(bookingItem);
        }
        booking.setTotalPrice(totalPrice);
        booking.setStatus(BookingStatus.PENDING_PAYMENT);

        Booking savedBooking = bookingRepository.save(booking);
        String url = paymentFeignClient.getVnPayPaymentUrl(totalPrice, savedBooking.getId(), userId);

        return new CreateBookingResponseDTO(url);
    }

    @Override
    public void delete(Integer id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(
                () -> new ApplicationException("booking_not_found", "Booking not found"));
        bookingRepository.delete(booking);
    }

    @Transactional
    @Override
    // Xử lý khi thanh toán thành công
    public void handlePaymentResult(PaymentCompletedEvent event) {
        Optional<Booking> optionalBooking = bookingRepository.findBookingAndBookingItemById(event.getBookingId());

        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();

            List<Integer> seatIds = booking.getBookingItems().stream().map(BookingItem::getSeatId).collect(Collectors.toList());

            List<RLock> locks = new ArrayList<>();

            for (Integer seatId : seatIds) {
                locks.add(redissonClient.getLock(seatId + "-" + booking.getShowId()));
            }
            redissonClient.getMultiLock(locks.toArray(RLock[]::new)).lock();

            Integer count = bookingItemRepository.countBySeatIdAndShowIdAndStatus(booking.getShowId(), seatIds, BookingStatus.COMPLETED);

            // Duplicate
            if (count > 0) {
                booking.setStatus(BookingStatus.DUPLICATE);
                kafkaTemplate.send("refund-payment", new RefundRequestEvent(booking.getId(), booking.getTotalPrice()));
            } else {
                if (event.isSuccess()) {
                    booking.setStatus(BookingStatus.COMPLETED);
                } else {
                    booking.setStatus(BookingStatus.PAYMENT_FAIL);
                }
            }
            bookingRepository.save(booking);

            redissonClient.getMultiLock(locks.toArray(RLock[]::new)).unlock();
        }
    }

    @Override
    public void handleRefundResult(RefundedEvent event) {
        Optional<Booking> optionalBooking = bookingRepository.findById(event.getBookingId());

        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            if (event.isSuccess()) {
                booking.setStatus(BookingStatus.REFUND_SUCCESS);
            } else {
                booking.setStatus(BookingStatus.REFUND_FAIL);
            }
            bookingRepository.save(booking);
        }
    }

    @Override
    public void cancelBooking(String userId, Integer bookingId) {
        Booking booking = bookingRepository.findByIdAndUserId(bookingId, userId).orElseThrow(
                () -> new ApplicationException("booking_not_found", "Booking not found"));


        ShowResponseDTO showResponseDTO = showMapper.showResponseToShowResponseDTO(showServiceBlockingStub
                .getShowById(GetShowByIdRequest.newBuilder().setId(booking.getShowId()).build())
                .getShow());
        LocalDateTime now = LocalDateTime.now();

        if (booking.getStatus().equals(BookingStatus.CANCELED)) {
            throw new ApplicationException("booking_not_found", "Booking not found");
        }


        if (now.plusDays(2).isBefore(showResponseDTO.getStart())) {
            booking.setStatus(BookingStatus.CANCELLING);

            bookingRepository.save(booking);
            kafkaTemplate.send("refund-for-cancelling-booking", new BookingCancelledRefundEvent(bookingId, booking.getTotalPrice()));
        } else {
            throw new ApplicationException("error_cancel_booking", "Can not cancel booking");
        }

    }

    @Override
    public List<BookingItemResponseDTO> getAllBookedSeatsByShow(Integer showId) {
        List<BookingItem> bookingItems = bookingItemRepository.findAllBookedByShowId(showId);
        return bookingItems.stream().map(bi -> BookingItemResponseDTO.builder()
                .seat(SeatResponseDTO.builder().id(bi.getSeatId()).build())
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<TopRevenueByMoveResponseDTO> getTopRevenueByMovie(LocalDate start, LocalDate end, Integer limit) {
        List<Map<String, Object>> maps = bookingRepository.getTopRevenueByMovie(start.atStartOfDay(), end.atTime(LocalTime.MAX), limit);
        List<Integer> movieIds = new ArrayList<>();
        maps.forEach(revenue -> {
            movieIds.add(Integer.valueOf(revenue.get("movieId").toString()));
            System.out.println(revenue.keySet());
            System.out.println(revenue.values());
        });

        List<MovieResponse> movieResponses = movieServiceBlockingStub.getMoviesByIds(GetMoviesByIdsRequest.newBuilder()
                .addAllIds(movieIds).build()).getMoviesList();

        return maps.stream().map(revenue -> {
            Integer movieId = Integer.valueOf(revenue.get("movieId").toString());
            MovieResponse movie = movieResponses.stream().filter(movieResponse -> movieResponse.getId() == movieId).findFirst().get();

            TopRevenueByMoveResponseDTO dto = new TopRevenueByMoveResponseDTO();
            dto.setAmount(BigDecimal.valueOf(Double.parseDouble(revenue.get("amount").toString())));
            dto.setMovieName(movie.getName());

            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void handleRefundResultForCancelling(RefundResultForCancellingEvent event) {
        Optional<Booking> optionalBooking = bookingRepository.findById(event.getBookingId());

        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            if (event.isSuccess()) {
                booking.setStatus(BookingStatus.CANCELED);
            } else {
                booking.setStatus(BookingStatus.REFUND_CANCEL_BOOKING_FAIL);
            }
            bookingRepository.save(booking);
        }
    }

    @Override
    public Long count() {
        return bookingRepository.count();
    }

    @Override
    public List<OrderStatisticResponseDTO> getOrderStatistic(LocalDate start, LocalDate end) {
        final List<Map<String, Object>> maps = bookingRepository.getOrderStatistic(start.atStartOfDay(), end.atTime(LocalTime.MAX));
        YearMonth startMonth = YearMonth.from(start); // Tháng của start
        YearMonth endMonth = YearMonth.from(end); // Tháng của end

        // Lặp qua các tháng từ startMonth đến endMonth
        YearMonth currentMonth = startMonth;
        while (!currentMonth.isAfter(endMonth)) {
            int year = currentMonth.getYear();
            String month = String.format("%02d", currentMonth.getMonthValue());

            // Điều kiện xử lý cho từng tháng trong vòng lặp
            String date = year + "-" + month;
            if (!maps.stream().anyMatch(map -> map.get("time").equals(date))) {
                Map<String, Object> temp = new HashMap<>();
                temp.put("time", date);
                temp.put("count", 0);
                maps.add(temp);
            }

            // Tiếp tục với tháng tiếp theo
            currentMonth = currentMonth.plusMonths(1);
        }

        List<OrderStatisticResponseDTO> result = maps.stream().map(sum -> {
            OrderStatisticResponseDTO dto = new OrderStatisticResponseDTO();
            dto.setCount(Integer.valueOf(sum.get("count").toString()));
            dto.setTime(sum.get("time").toString());
            return dto;
        }).sorted(Comparator.comparing(OrderStatisticResponseDTO::getTime)).collect(Collectors.toList());
        return result;
    }

    @Override
    public Map<String, Object> generateTicketQrCode(Integer bookingId, String userId) {
        Map<String, Object> result = new HashMap<>();
        LocalDateTime expire = LocalDateTime.now().plusMinutes(5);

        Booking booking = bookingRepository.findByIdAndUserId(bookingId, userId).orElseThrow(
                () -> new ApplicationException("booking_not_found", "Booking not found"));


        ShowResponseDTO show = showMapper.showResponseToShowResponseDTO(showServiceBlockingStub
                .getShowById(GetShowByIdRequest.newBuilder().setId(booking.getShowId()).build())
                .getShow());

        if (show.getStart().isBefore(LocalDateTime.now())) {
            throw new ApplicationException("show_started", "Show started");
        }

        if (booking.getStatus().equals(BookingStatus.ACTIVATED_TICKET)) {
            throw new ApplicationException("ticked_activated", "Ticket activated");
        } else if (booking.getStatus().equals(BookingStatus.COMPLETED)) {

            String ticketCode = RandomStringUtils.random(64, true, true);
            String qrCodeContent = "http://localhost:5173/active-ticket/" + ticketCode;

            Map<String, Object> map = new HashMap<>();
            map.put("bookingId", bookingId);

            redisTemplate.opsForHash().putAll("qr-ticket-" + ticketCode, map);
            redisTemplate.expireAt("qr-ticket-" + ticketCode, expire.atZone(ZoneId.systemDefault()).toInstant());

            try {
                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeContent, BarcodeFormat.QR_CODE, 400, 400);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
                result.put("qr", byteArrayOutputStream.toByteArray());
                result.put("expire", expire);
            } catch (IOException | WriterException e) {

            }
            return result;
        } else {
            throw new ApplicationException("booking_status_invalid", "Booking status invalid");
        }
    }

    @Override
    public BookingDetailResponseDTO activeTicket(String ticketCode) {
        Map<Object, Object> map = redisTemplate.opsForHash().entries("qr-ticket-" + ticketCode);

        if (Objects.isNull(map) || Objects.isNull(map.get("bookingId"))) {
            throw new ApplicationException("booking_not_found", "Booking not found");
        }

        Booking booking = bookingRepository.findById((Integer) map.get("bookingId")).orElseThrow(
                () -> new ApplicationException("booking_not_found", "Booking not found"));

        ShowResponseDTO show = showMapper.showResponseToShowResponseDTO(showServiceBlockingStub
                .getShowById(GetShowByIdRequest.newBuilder().setId(booking.getShowId()).build())
                .getShow());

        if (show.getStart().isBefore(LocalDateTime.now())) {
            throw new ApplicationException("show_started", "Show started");
        }

        if (booking.getStatus().equals(BookingStatus.ACTIVATED_TICKET)) {
            throw new ApplicationException("ticked_activated", "Ticket activated");
        } else {
            booking.setStatus(BookingStatus.ACTIVATED_TICKET);
            bookingRepository.save(booking);
            redisTemplate.delete("qr-ticket-" + ticketCode);
        }

        return map(booking);
    }
}
