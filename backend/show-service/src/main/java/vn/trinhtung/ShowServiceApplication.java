package vn.trinhtung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import vn.trinhtung.entity.Show;
import vn.trinhtung.repository.ShowRepository;

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
//        List<Show> shows = showRepository.findAll();
//        for(Show show : shows) {
//            show.setStart(show.getStart().plusDays(12));
//            show.setEnd(show.getEnd().plusDays(12));
//        }
//        showRepository.saveAll(shows);
    }
}
