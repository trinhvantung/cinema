package vn.trinhtung.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowRequestDTO {
    @NotNull(message = "Thời gian bắt đầu không được để trống")
    private LocalDateTime start;

    @NotNull(message = "Thời gian kết thúc không được để trống")
    private LocalDateTime end;

    @NotNull(message = "Phim không được để trống")
    private Integer movieId;

    @NotNull(message = "Phòng chiếu không được để trống")
    private Integer hallId;

    @Valid
    @NotEmpty(message = "Giá tiền không được để trống")
    private List<ShowPriceRequestDTO> showPrices;
}
