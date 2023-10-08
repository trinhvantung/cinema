package vn.trinhtung.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import vn.trinhtung.dto.ReviewRequestDTO;
import vn.trinhtung.dto.ReviewResponseDTO;
import vn.trinhtung.service.ReviewService;

import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/movie/{movieId}")
    public Page<ReviewResponseDTO> getAllByMovieId(@PathVariable Integer movieId,
                                                   @RequestParam(defaultValue = "1") Integer page,
                                                   @RequestParam(required = false) Integer star) {
        if (Objects.isNull(star)) {
            return reviewService.getAllByMovieId(movieId, page);
        } else {
            return reviewService.getAllByStarAndMovieId(movieId, star, page);
        }
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/exists/movie/{movieId}")
    public ReviewResponseDTO checkExistsByMovieIdAndUserId(@PathVariable Integer movieId, @AuthenticationPrincipal Jwt jwt) {
        return reviewService.checkExistsByMovieIdAndUserId(movieId, jwt.getClaimAsString("sub"));
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/movie/{movieId}")
    public ReviewResponseDTO save(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer movieId,
                                  @RequestBody ReviewRequestDTO review) {
        return reviewService.save(jwt.getClaimAsString("sub"), movieId, review);
    }

    @GetMapping("/detail/movie/{movieId}")
    public Map<String, Object> getReviewDetailByMovieId(@PathVariable Integer movieId) {
        return reviewService.getReviewDetailByMovieId(movieId);
    }
}
