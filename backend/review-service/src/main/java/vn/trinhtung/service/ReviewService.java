package vn.trinhtung.service;

import org.springframework.data.domain.Page;
import vn.trinhtung.dto.ReviewRequestDTO;
import vn.trinhtung.dto.ReviewResponseDTO;

import java.util.Map;

public interface ReviewService {
    Page<ReviewResponseDTO> getAllByMovieId(Integer movieId, Integer page);

    Page<ReviewResponseDTO> getAllByStarAndMovieId(Integer movieId, Integer star, Integer page);

    ReviewResponseDTO checkExistsByMovieIdAndUserId(Integer movieId, String userId);

    ReviewResponseDTO save(String userId, Integer movieId, ReviewRequestDTO review);

    Map<String, Object> getReviewDetailByMovieId(Integer movieId);
}
