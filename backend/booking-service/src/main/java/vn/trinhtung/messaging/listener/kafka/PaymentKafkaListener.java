
package vn.trinhtung.messaging.listener.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import vn.trinhtung.event.PaymentCompletedEvent;
import vn.trinhtung.event.RefundResultForCancellingEvent;
import vn.trinhtung.event.RefundedEvent;
import vn.trinhtung.service.BookingService;

@Component
@RequiredArgsConstructor
public class PaymentKafkaListener {
    private final BookingService bookingService;

    @KafkaListener(topics = "payment-result", groupId = "group_1", containerFactory = "factory")
    public void consume(@Payload PaymentCompletedEvent event) {
        bookingService.handlePaymentResult(event);
    }


    @KafkaListener(topics = "refund-result", groupId = "group_1", containerFactory = "factory")
    public void consume(@Payload RefundedEvent event) {
        bookingService.handleRefundResult(event);
    }

    @KafkaListener(topics = "refund-for-cancelling-result", groupId = "group_1", containerFactory = "factory")
    public void consume(@Payload RefundResultForCancellingEvent event) {
        bookingService.handleRefundResultForCancelling(event);
    }

}
