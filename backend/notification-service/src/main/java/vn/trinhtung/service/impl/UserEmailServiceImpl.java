package vn.trinhtung.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.trinhtung.event.UserRegisteredEvent;
import vn.trinhtung.event.UserResetPasswordEvent;
import vn.trinhtung.service.EmailService;
import vn.trinhtung.service.UserEmailService;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserEmailServiceImpl implements UserEmailService {
    private final EmailService emailService;

    @Override
    public void handle(UserRegisteredEvent event) {
        final String template = "register";
        final String subject = "Kích hoạt tài khoản";
        final Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("username", event.getFullName());
        attributes.put("urlVerify", event.getUrlVerify());

        emailService.sendMessageHtml(event.getEmail(), subject, template, attributes);
    }

    @Override
    public void handle(UserResetPasswordEvent event) {
        final String template = "reset-password";
        final String subject = "Quên mật khẩu";
        final DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        final Map<String, Object> attributes = new HashMap<String, Object>();

        final String expire = event.getExpire().format(format);

        attributes.put("token", event.getToken());
        attributes.put("expire", expire);

        emailService.sendMessageHtml(event.getEmail(), subject, template, attributes);
    }

}
