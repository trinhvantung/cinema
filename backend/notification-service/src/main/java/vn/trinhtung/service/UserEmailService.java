package vn.trinhtung.service;

import vn.trinhtung.event.UserRegisteredEvent;
import vn.trinhtung.event.UserResetPasswordEvent;

public interface UserEmailService {
	void handle(UserRegisteredEvent event);
	
	void handle(UserResetPasswordEvent event);
}
