package vn.trinhtung.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequestDTO {
    @NotBlank(message = "Tên phim không được để trống")
    private String name;

    @Min(value = 1, message = "Độ dài không hợp lệ")
    @NotNull(message = "Tên phim không được để trống")
    private Integer duration;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    @NotNull(message = "Ngày phát hành không được để trống")
    private LocalDate releaseDate;

    @NotEmpty(message = "Danh mục không được để trống")
    private List<Integer> categories;
}
