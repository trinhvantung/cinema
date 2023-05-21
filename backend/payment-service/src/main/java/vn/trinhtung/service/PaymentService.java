package vn.trinhtung.service;

import vn.trinhtung.dto.RevenueResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface PaymentService {

    List<RevenueResponseDTO> sumAmountGroupByMonth(LocalDate start, LocalDate end, String groupBy);
}
