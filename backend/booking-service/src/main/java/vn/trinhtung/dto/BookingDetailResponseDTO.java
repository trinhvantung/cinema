package vn.trinhtung.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.trinhtung.entity.BookingStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetailResponseDTO {
    private Integer id;

    private BigDecimal totalPrice;

    private String fullName;

    private String email;

    private String phoneNumber;

    private BookingStatus status;

    private LocalDateTime createdAt;

    private HallResponseDTO hall;

    private ShowResponseDTO showResponse;

    private List<BookingItemResponseDTO> bookingItems;

    private boolean canCancel;
}
