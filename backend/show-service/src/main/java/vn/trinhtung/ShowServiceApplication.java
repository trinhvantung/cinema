package vn.trinhtung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import vn.trinhtung.entity.Show;
import vn.trinhtung.repository.ShowRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


@EnableGlobalMethodSecurity(proxyTargetClass = true, prePostEnabled = true)
@EnableEurekaClient
@SpringBootApplication
public class ShowServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ShowServiceApplication.class, args);
    }


    @Autowired
    private ShowRepository showRepository;

    @Override
    public void run(String... args) throws Exception {
        Show temp = showRepository.findById(40).get();
        List<Show> shows = showRepository.findAll();

        LocalDate now = LocalDate.now();

        long distance = temp.getStart().toLocalDate().until(now, ChronoUnit.DAYS);
        System.out.println("Distance: " + distance);

        for(Show show : shows) {
            show.setStart(show.getStart().plusDays(distance));
            show.setEnd(show.getEnd().plusDays(distance));
        }
        showRepository.saveAll(shows);
    }
}
