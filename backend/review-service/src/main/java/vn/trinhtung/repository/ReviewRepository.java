package vn.trinhtung.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.trinhtung.dto.ReviewStarCountResponseDTO;
import vn.trinhtung.entity.Review;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Page<Review> findAllByMovieId(Integer movieId, Pageable pageable);

    @Query("select r from Review r where r.movieId=:movieId and r.star=:star")
    Page<Review> findAllByStarAndMovieId(Integer movieId, Integer star, Pageable pageable);

    @Query("SELECT new vn.trinhtung.dto.ReviewStarCountResponseDTO(r.star, COUNT(r.star)) " +
            "FROM Review AS r WHERE r.movieId=:movieId GROUP BY r.star")
    List<ReviewStarCountResponseDTO> countTotalReviewStarByStar(Integer movieId);

    Optional<Review> findByUserIdAndMovieId(String userId, Integer movieId);
}
