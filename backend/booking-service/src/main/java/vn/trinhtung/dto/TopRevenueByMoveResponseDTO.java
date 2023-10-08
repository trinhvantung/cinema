package vn.trinhtung.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopRevenueByMoveResponseDTO {
    private String movieName;
    private BigDecimal amount;
}
