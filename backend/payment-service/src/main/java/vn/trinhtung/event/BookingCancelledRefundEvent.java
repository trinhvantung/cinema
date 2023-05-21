package vn.trinhtung.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingCancelledRefundEvent implements Serializable {
    private Integer bookingId;

    private BigDecimal amount;
}
