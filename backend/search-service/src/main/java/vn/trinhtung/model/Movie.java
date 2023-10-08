package vn.trinhtung.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "movie")
public class Movie {
    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Integer)
    private Integer duration;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Text)
    private String thumbnail;

}
