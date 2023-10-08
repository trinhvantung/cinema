package vn.trinhtung.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDTO {
    private Integer id;
    private String content;
    private Integer star;
    private UserResponseDTO user;
    private LocalDateTime createdDate;
}
