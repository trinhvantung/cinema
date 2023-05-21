package vn.trinhtung.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryMovieCountResponseDTO {
    private Integer categoryId;
    private String categoryName;
    private Long movieCount;
}
