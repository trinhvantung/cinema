package vn.trinhtung.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefundResultForCancellingEvent implements Serializable {
    private Integer bookingId;
    private boolean isSuccess;
}
