package vn.trinhtung;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import vn.trinhtung.model.Movie;
import vn.trinhtung.repository.MovieElasticSearchRepository;
import vn.trinhtung.repository.MovieRepository;

import java.util.List;
import java.util.stream.Collectors;

@EnableEurekaClient
//@RequiredArgsConstructor
@SpringBootApplication
public class SearchServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SearchServiceApplication.class, args);
	}


//	private final MovieRepository movieRepository;
//	private final MovieElasticSearchRepository movieElasticSearchRepository;

	@Override
	public void run(String... args) throws Exception {
		/*List<Movie> movies = movieRepository.findAll().stream().map(m -> Movie.builder()
				.id(m.getId().toString())
				.description(m.getDescription())
				.duration(m.getDuration())
				.thumbnail(m.getThumbnail())
				.name(m.getName())
				.build()).collect(Collectors.toList());



		movieElasticSearchRepository.saveAll(movies);*/
	}
}
