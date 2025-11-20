package space.personalshowcase.restaurant_review_platform.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import space.personalshowcase.restaurant_review_platform.domain.ReviewCreateUpdateRequest;
import space.personalshowcase.restaurant_review_platform.domain.entities.Review;
import space.personalshowcase.restaurant_review_platform.domain.entities.User;

import java.util.Optional;

public interface ReviewService {
    Review createReview(User author, String restaurantId, ReviewCreateUpdateRequest review);

    Page<Review> listReview(String restaurantId , Pageable pageable);
    Optional<Review> getReview(String restaurantId , String reviewId);
}
