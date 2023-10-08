package vn.trinhtung.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime createDate;
    private LocalDateTime expireDate;
    private String orderInfo;
    private BigDecimal amount;
    private Integer bookingId;
    private String userId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
