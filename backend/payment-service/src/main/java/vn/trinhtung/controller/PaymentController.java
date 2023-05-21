package vn.trinhtung.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.trinhtung.dto.RevenueResponseDTO;
import vn.trinhtung.service.PaymentService;
import vn.trinhtung.utils.ExcelExporter;
import vn.trinhtung.utils.RevenueExcelExporter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

//    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/statistic")
    public List<RevenueResponseDTO> statistic(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {

        return paymentService.sumAmountGroupByMonth(start, end, "");
    }

    //    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/statistic/export")
    public void exportRevenueExcel(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                   @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                                   HttpServletResponse response) {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=revenue_" + start.toString() + "_" + end.toString() + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<RevenueResponseDTO> data = paymentService.sumAmountGroupByMonth(start, end, "");

        ExcelExporter exporter = new RevenueExcelExporter(data);
        try {
            exporter.export(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
