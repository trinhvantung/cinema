package vn.trinhtung.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.trinhtung.dto.MovieResponseDTO;
import vn.trinhtung.dto.ShowPriceResponseDTO;
import vn.trinhtung.dto.ShowResponseDTO;
import vn.trinhtung.grpc.ShowResponse;
import vn.trinhtung.utils.TimestampUtil;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ShowMapper {
    private final ShowPriceMapper showPriceMapper;
    private final MovieMapper movieMapper;


    public ShowResponseDTO showResponseToShowResponseDTO(ShowResponse show) {
        List<ShowPriceResponseDTO> showPriceResponseDTOs = show.getShowPricesList().stream()
                .map(showPriceMapper::showPriceResponseToShowPriceResponseDTO).collect(Collectors.toList());

        MovieResponseDTO movieResponseDTO = null;

        if (Objects.nonNull(show.getMovie())) {
            movieResponseDTO = movieMapper.movieResponseToMovieResponseDTO(show.getMovie());
        }

        return ShowResponseDTO.builder()
                .id(show.getId())
                .cinemaId(show.getCinemaId())
                .hallId(show.getHallId())
                .start(TimestampUtil.timestampToLocalDateTime(show.getStart()))
                .end(TimestampUtil.timestampToLocalDateTime(show.getEnd()))
                .movieId(show.getMovieId())
                .showPrices(showPriceResponseDTOs)
                .movie(movieResponseDTO)
                .build();
    }
}
