package vn.trinhtung.entity;

import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShowPrice {
	@EmbeddedId
	private ShowPriceId showPriceId;
	private BigDecimal price;

	@ManyToOne
	@MapsId("showId")
	private Show show;

}
