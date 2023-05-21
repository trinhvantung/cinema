package vn.trinhtung.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import vn.trinhtung.dto.ShowPriceResponseDTO;
import vn.trinhtung.dto.ShowResponseDTO;
import vn.trinhtung.entity.Show;
import vn.trinhtung.grpc.ShowPriceResponse;
import vn.trinhtung.grpc.ShowResponse;
import vn.trinhtung.utils.TimestampUtil;

@RequiredArgsConstructor
@Component
public class ShowMapper {
	private final ShowPriceMapper showPriceMapper;

	public ShowResponseDTO showToShowResponseDTO(Show show) {
		List<ShowPriceResponseDTO> showPriceResponseDTOs = show.getShowPrices().stream()
				.map(showPriceMapper::showPriceToShowPriceResponseDTO).collect(Collectors.toList());

		return ShowResponseDTO.builder()
				.id(show.getId())
				.cinemaId(show.getCinemaId())
				.hallId(show.getHallId())
				.movieId(show.getMovieId()).end(show.getEnd())
				.start(show.getStart())
				.showPrices(showPriceResponseDTOs).build();
	}

	public ShowResponse showToShowResponse(Show show) {
		List<ShowPriceResponse> showPriceResponses = show.getShowPrices().stream()
				.map(showPriceMapper::showPriceToShowPriceResponse).collect(Collectors.toList());

		return ShowResponse.newBuilder()
				.setId(show.getId())
				.setCinemaId(show.getCinemaId())
				.setHallId(show.getHallId())
				.setStart(TimestampUtil.localDateTimeToTimestamp(show.getStart()))
				.setEnd(TimestampUtil.localDateTimeToTimestamp(show.getEnd()))
				.setMovieId(show.getMovieId())
				.addAllShowPrices(showPriceResponses)
				.build();
	}


	public ShowResponseDTO showResponseToShowResponseDTO(ShowResponse show) {
		List<ShowPriceResponseDTO> showPriceResponseDTOs = show.getShowPricesList().stream()
				.map(showPriceMapper::showPriceResponseToShowPriceResponseDTO).collect(Collectors.toList());

		return ShowResponseDTO.builder()
				.id(show.getId())
				.cinemaId(show.getCinemaId())
				.hallId(show.getHallId())
				.start(TimestampUtil.timestampToLocalDateTime(show.getStart()))
				.end(TimestampUtil.timestampToLocalDateTime(show.getEnd()))
				.movieId(show.getMovieId())
				.showPrices(showPriceResponseDTOs)
				.build();
	}
}
