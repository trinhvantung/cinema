package vn.trinhtung.mapper;

import org.springframework.stereotype.Component;
import vn.trinhtung.dto.ShowPriceResponseDTO;
import vn.trinhtung.grpc.ShowPriceResponse;

import java.math.BigDecimal;

@Component
public class ShowPriceMapper {

    public ShowPriceResponseDTO showPriceResponseToShowPriceResponseDTO(ShowPriceResponse showPrice) {

        return ShowPriceResponseDTO.builder()
                .price(BigDecimal.valueOf(Long.parseLong(showPrice.getPrice())))
                .seatTypeId(showPrice.getSeatTypeId())
                .showId(showPrice.getShowId())
                .build();
    }
}
