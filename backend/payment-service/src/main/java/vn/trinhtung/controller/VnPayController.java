package vn.trinhtung.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.trinhtung.dto.VnPayIpnResponseDTO;
import vn.trinhtung.service.VnPayService;
import vn.trinhtung.utils.IpAddress;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/vnpay")
public class VnPayController {
    private final VnPayService vnPayService;

    @GetMapping("/payment/url")
    public String getPaymentUrl(HttpServletRequest request,
                                @RequestParam BigDecimal amount,
                                @RequestParam Integer bookingId,
                                @RequestParam String userId) {

        return vnPayService.getPaymentUrl(IpAddress.get(request), amount, bookingId, userId);
    }

    @GetMapping("/payment/url/ipn")
    public VnPayIpnResponseDTO ipnUrlHandle(@RequestParam Map<String, String> params) {

        return vnPayService.handleIpn(params);
    }
}
