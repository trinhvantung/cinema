package vn.trinhtung.messaging.listener.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import vn.trinhtung.event.UserRegisteredEvent;
import vn.trinhtung.event.UserResetPasswordEvent;
import vn.trinhtung.service.UserEmailService;

@Component
@RequiredArgsConstructor
public class UserEmailKafkaListener {
    private final UserEmailService userEmailService;

    @KafkaListener(topics = "user-registration", groupId = "group_1", containerFactory = "factory")
    public void consume(@Payload UserRegisteredEvent event) {
        userEmailService.handle(event);
    }

    @KafkaListener(topics = "user-reset-password", groupId = "group_1", containerFactory = "factory")
    public void consume(@Payload UserResetPasswordEvent event) {
        userEmailService.handle(event);
    }
}
