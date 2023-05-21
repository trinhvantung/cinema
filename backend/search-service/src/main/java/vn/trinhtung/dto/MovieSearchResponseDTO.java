package vn.trinhtung.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieSearchResponseDTO {
    private String id;

    private String name;

    private Integer duration;

    private String description;

    private String thumbnail;

//    private LocalDate releaseDate;

    public String getThumbnailUrl() {
        return "https://res.cloudinary.com/tung071201/image/upload/v1651760535/" + thumbnail;
    }
}
