package vn.trinhtung;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import vn.trinhtung.entity.Movie;
import vn.trinhtung.event.MovieCreatedEvent;
import vn.trinhtung.mapper.MovieMapper;
import vn.trinhtung.repository.MovieRepository;

import java.util.List;

@RequiredArgsConstructor
@EnableGlobalMethodSecurity(proxyTargetClass = true, prePostEnabled = true)
@EnableEurekaClient
@SpringBootApplication
public class MovieServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MovieServiceApplication.class, args);
    }

    private final MovieMapper movieMapper;
    private final MovieRepository movieRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void run(String... args) throws Exception {
        List<Movie> movies = movieRepository.findAll();
        for (Movie movie : movies) {
            kafkaTemplate.send("create-movie",
                    new MovieCreatedEvent(movieMapper.movieToMovieResponseDTO(movie)));
        }
    }
}
