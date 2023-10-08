package vn.trinhtung.service.impl;

import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.trinhtung.dto.ReviewRequestDTO;
import vn.trinhtung.dto.ReviewResponseDTO;
import vn.trinhtung.dto.ReviewStarCountResponseDTO;
import vn.trinhtung.dto.UserResponseDTO;
import vn.trinhtung.entity.Review;
import vn.trinhtung.grpc.GetUsersByIdsRequest;
import vn.trinhtung.grpc.UserServiceGrpc;
import vn.trinhtung.mapper.UserMapper;
import vn.trinhtung.repository.ReviewRepository;
import vn.trinhtung.service.ReviewService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserMapper userMapper;

    @GrpcClient("userService")
    private UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

    @Override
    public Page<ReviewResponseDTO> getAllByMovieId(Integer movieId, Integer page) {


        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("id").descending());
        Page<Review> reviewPage = reviewRepository.findAllByMovieId(movieId, pageable);

        return getReviewResponses(reviewPage);
    }

    @Override
    public Page<ReviewResponseDTO> getAllByStarAndMovieId(Integer movieId, Integer star, Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("id").descending());
        Page<Review> reviewPage = null;

        if (star >= 1 && star <= 5) {
            reviewPage = reviewRepository.findAllByStarAndMovieId(movieId, star, pageable);

        } else {
            reviewPage = Page.empty();
        }


        return getReviewResponses(reviewPage);
    }

    private Page<ReviewResponseDTO> getReviewResponses(Page<Review> reviewPage) {
        List<String> userIds = reviewPage.getContent().stream().map(Review::getUserId).collect(Collectors.toList());

        List<UserResponseDTO> users = userServiceBlockingStub.getUsersByIds(GetUsersByIdsRequest
                .newBuilder().addAllIds(userIds).build()).getUsersList().stream()
                .map(userMapper::userResponseDTOToUserResponse).collect(Collectors.toList());

        return reviewPage.map(review -> {
            ReviewResponseDTO response = new ReviewResponseDTO();
            response.setId(review.getId());
            response.setContent(review.getContent());
            response.setCreatedDate(review.getCreatedDate());
            response.setStar(review.getStar());
            response.setUser(users.stream().filter(user -> user.getId().equals(review.getUserId())).findFirst().orElse(null));

            return response;
        });
    }

    @Override
    public ReviewResponseDTO checkExistsByMovieIdAndUserId(Integer movieId, String userId) {
        Optional<Review> optionalReview = reviewRepository.findByUserIdAndMovieId(userId, movieId);
        if(optionalReview.isPresent()) {
            Review savedReview = optionalReview.get();
            ReviewResponseDTO response = new ReviewResponseDTO();
            response.setId(savedReview.getId());
            response.setContent(savedReview.getContent());
            response.setCreatedDate(savedReview.getCreatedDate());
            response.setStar(savedReview.getStar());
            return response;
        }

        return null;
    }

    @Override
    public ReviewResponseDTO save(String userId, Integer movieId, ReviewRequestDTO reviewRequestDTO) {
        Review review = new Review();
        review.setContent(reviewRequestDTO.getContent());
        review.setStar(reviewRequestDTO.getStar());
        review.setMovieId(movieId);
        review.setUserId(userId);

        Review savedReview = reviewRepository.save(review);

        ReviewResponseDTO response = new ReviewResponseDTO();
        response.setId(savedReview.getId());
        response.setContent(savedReview.getContent());
        response.setCreatedDate(savedReview.getCreatedDate());
        response.setStar(savedReview.getStar());

        return response;
    }

    @Override
    public Map<String, Object> getReviewDetailByMovieId(Integer movieId) {
        Map<String, Object> result = new HashMap<>();
        List<ReviewStarCountResponseDTO> reviewStarCounts = reviewRepository.countTotalReviewStarByStar(movieId);
        long totalReview = 0l;
        float starResult = 0f;
        long totalStar = 0l;

        for (ReviewStarCountResponseDTO rsc : reviewStarCounts) {
            totalReview += rsc.getQuantity();
            totalStar += rsc.getStar() * rsc.getQuantity();
        }

        for (int i = 1; i <= 5; i++) {
            if (!reviewStarCounts.contains(new ReviewStarCountResponseDTO(i))) {
                reviewStarCounts.add(new ReviewStarCountResponseDTO(i, 0l));
            }
        }
        reviewStarCounts.sort((r1, r2) -> r2.getStar().compareTo(r1.getStar()));

        if (totalReview != 0) {
            starResult = totalStar / (1.0f * totalReview);
        }

        result.put("reviewStarCounts", reviewStarCounts);
        result.put("totalReview", totalReview);
        if (starResult == (int) starResult) {
            result.put("starResult", (int) starResult);
        } else {
            result.put("starResult", Float.valueOf(String.format("%.1f", starResult)));
        }

        return result;
    }
}
