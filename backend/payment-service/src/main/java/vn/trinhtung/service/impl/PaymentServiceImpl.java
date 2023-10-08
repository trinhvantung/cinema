package vn.trinhtung.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.trinhtung.dto.RevenueResponseDTO;
import vn.trinhtung.repository.PaymentRepository;
import vn.trinhtung.service.PaymentService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    public List<RevenueResponseDTO> sumAmountGroupByMonth(LocalDate start, LocalDate end, String groupBy) {
        final List<Map<String, Object>> sums = paymentRepository.sumAmountGroupByMonth(start.atStartOfDay(), end.atTime(LocalTime.MAX));

        YearMonth startMonth = YearMonth.from(start);
        YearMonth endMonth = YearMonth.from(end);

        while(!startMonth.isAfter(endMonth)) {
            int year = startMonth.getYear();
            String month = String.format("%02d", startMonth.getMonthValue());

            String date = year + "-" + month;
            if(!sums.stream().anyMatch(sum -> sum.get("time").equals(date))) {
                Map<String, Object> temp = new HashMap<>();
                temp.put("time", date);
                temp.put("amount", 0);
                sums.add(temp);
            }

            startMonth = startMonth.plusMonths(1);
        }

        List<RevenueResponseDTO> result = sums.stream().map(sum -> {
            System.out.println(sum.get("amount"));
            System.out.println(sum.get("time"));

            RevenueResponseDTO dto = new RevenueResponseDTO();
            dto.setAmount(BigDecimal.valueOf(Double.parseDouble(sum.get("amount").toString())));
            dto.setTime(sum.get("time").toString());
            return dto;
        }).sorted(Comparator.comparing(RevenueResponseDTO::getTime)).collect(Collectors.toList());
        return result;
    }
}
