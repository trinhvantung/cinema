package vn.trinhtung.mapper;

import org.springframework.stereotype.Component;

import vn.trinhtung.dto.ShowPriceResponseDTO;
import vn.trinhtung.entity.ShowPrice;
import vn.trinhtung.grpc.ShowPriceResponse;

import java.math.BigDecimal;

@Component
public class ShowPriceMapper {

	public ShowPriceResponseDTO showPriceToShowPriceResponseDTO(ShowPrice showPrice) {

		return ShowPriceResponseDTO.builder()
				.price(showPrice.getPrice())
				.seatTypeId(showPrice.getShowPriceId().getSeatTypeId())
				.showId(showPrice.getShowPriceId().getShowId())
				.build();
	}

	public ShowPriceResponse showPriceToShowPriceResponse(ShowPrice showPrice) {
		return ShowPriceResponse.newBuilder()
				.setPrice(showPrice.getPrice().stripTrailingZeros().toPlainString())
				.setShowId(showPrice.getShowPriceId().getShowId())
				.setSeatTypeId(showPrice.getShowPriceId().getSeatTypeId())
				.build();
	}

	public ShowPriceResponseDTO showPriceResponseToShowPriceResponseDTO(ShowPriceResponse showPrice) {

		return ShowPriceResponseDTO.builder()
				.price(BigDecimal.valueOf(Long.parseLong(showPrice.getPrice())))
				.seatTypeId(showPrice.getSeatTypeId())
				.showId(showPrice.getShowId())
				.build();
	}
}
