package vn.trinhtung.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CinemaResponseDTO {
    private Integer id;
    private String name;
    private String address;
    private CityResponseDTO city;
}
