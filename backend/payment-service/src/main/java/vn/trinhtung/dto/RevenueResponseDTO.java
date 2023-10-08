package vn.trinhtung.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevenueResponseDTO {
    private BigDecimal amount;
    private String time;
}
