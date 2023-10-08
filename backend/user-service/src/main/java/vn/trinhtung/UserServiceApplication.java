package vn.trinhtung;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

//@EnableMethodSecurity(prePostEnabled = true)
@EnableGlobalMethodSecurity(proxyTargetClass = true, prePostEnabled = true)
@EnableEurekaClient
@RequiredArgsConstructor
@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
