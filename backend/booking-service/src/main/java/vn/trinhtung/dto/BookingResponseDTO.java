package vn.trinhtung.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.trinhtung.entity.BookingStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDTO {
    private Integer id;

    private ShowResponseDTO show;

    private BigDecimal totalPrice;

    private String userId;

    private String fullName;

    private String email;

    private String phoneNumber;

    private Integer movieId;

    private BookingStatus status;

    private LocalDateTime createdAt;

    private boolean canCreateTicket;
}
