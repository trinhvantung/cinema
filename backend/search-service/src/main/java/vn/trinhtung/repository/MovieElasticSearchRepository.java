package vn.trinhtung.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import vn.trinhtung.model.Movie;

@Repository
public interface MovieElasticSearchRepository extends ElasticsearchRepository<Movie, String> {
    @Query("{\"multi_match\":{\"query\":\"?0\",\"fields\":[\"name\",\"description\"]}}")
    Page<Movie> search(String query, Pageable pageable);
}
