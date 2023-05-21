package vn.trinhtung.messaging.listener.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import vn.trinhtung.event.BookingCancelledRefundEvent;
import vn.trinhtung.event.RefundRequestEvent;
import vn.trinhtung.service.VnPayService;

@Component
@RequiredArgsConstructor
public class PaymentKafkaListener {
    private final VnPayService vnPayService;

    @KafkaListener(topics = "refund-payment", groupId = "group_1", containerFactory = "factory")
    public void consume(@Payload RefundRequestEvent event) {
        vnPayService.refund(event.getBookingId());
    }

    @KafkaListener(topics = "refund-for-cancelling-booking", groupId = "group_1", containerFactory = "factory")
    public void consume(@Payload BookingCancelledRefundEvent event) {
        vnPayService.refundForCancellingBooking(event.getBookingId());
    }
}
