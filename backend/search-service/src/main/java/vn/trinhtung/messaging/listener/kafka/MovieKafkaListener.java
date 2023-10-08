package vn.trinhtung.messaging.listener.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import vn.trinhtung.event.MovieCreatedEvent;
import vn.trinhtung.event.MovieDeletedEvent;
import vn.trinhtung.event.MovieUpdatedEvent;
import vn.trinhtung.service.MovieService;

@Component
@RequiredArgsConstructor
public class MovieKafkaListener {
    private final MovieService movieService;

    @KafkaListener(topics = "create-movie", groupId = "group_1", containerFactory = "factory")
    public void consume(@Payload MovieCreatedEvent event) {
        movieService.save(event.getMovie());
    }

    @KafkaListener(topics = "update-movie", groupId = "group_1", containerFactory = "factory")
    public void consume(@Payload MovieUpdatedEvent event) {
        movieService.save(event.getMovie());
    }

    @KafkaListener(topics = "delete-movie", groupId = "group_1", containerFactory = "factory")
    public void consume(@Payload MovieDeletedEvent event) {
        movieService.deleteById(event.getMovieId());
    }

}
