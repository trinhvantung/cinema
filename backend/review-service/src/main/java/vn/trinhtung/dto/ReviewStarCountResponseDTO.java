package vn.trinhtung.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewStarCountResponseDTO {
    private Integer star;
    private Long quantity;

    public ReviewStarCountResponseDTO(Integer star) {
        super();
        this.star = star;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewStarCountResponseDTO that = (ReviewStarCountResponseDTO) o;
        return Objects.equals(star, that.star);
    }

    @Override
    public int hashCode() {
        return Objects.hash(star);
    }
}
