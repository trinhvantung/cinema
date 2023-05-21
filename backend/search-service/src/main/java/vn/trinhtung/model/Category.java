package vn.trinhtung.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Document(indexName = "category")
public class Category {
//    @Id
    private String id;

    private String name;

    private Integer displayOrder;

    private List<Movie> movies;
}
