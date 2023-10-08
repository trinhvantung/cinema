package vn.trinhtung.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ShowPriceId implements Serializable {
	private static final long serialVersionUID = -668393972978547360L;

	private Integer showId;
	private Integer seatTypeId;
}