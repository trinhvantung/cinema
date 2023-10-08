package vn.trinhtung.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "payment-service", path = "/api/payment-service", contextId = "payment")
public interface PaymentFeignClient {
    @GetMapping("/vnpay/payment/url")
    String getVnPayPaymentUrl(@RequestParam BigDecimal amount, @RequestParam Integer bookingId,
                              @RequestParam String userId);
}
