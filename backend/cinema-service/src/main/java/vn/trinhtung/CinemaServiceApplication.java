package vn.trinhtung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(proxyTargetClass = true, prePostEnabled = true)
@EnableEurekaClient
@SpringBootApplication
public class CinemaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaServiceApplication.class, args);
    }

}
