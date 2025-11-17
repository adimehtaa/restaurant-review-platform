package space.personalshowcase.restaurant_review_platform.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import space.personalshowcase.restaurant_review_platform.domain.RestaurantCreateUpdateRequest;
import space.personalshowcase.restaurant_review_platform.domain.dtos.GeoPointDto;
import space.personalshowcase.restaurant_review_platform.domain.dtos.RestaurantCreateUpdateRequestDto;
import space.personalshowcase.restaurant_review_platform.domain.dtos.RestaurantDto;
import space.personalshowcase.restaurant_review_platform.domain.dtos.RestaurantSummaryDto;
import space.personalshowcase.restaurant_review_platform.domain.entities.Restaurant;
import space.personalshowcase.restaurant_review_platform.domain.entities.Review;

import java.util.List;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {

    RestaurantCreateUpdateRequest toRestaurantCreateUpdateRequest(RestaurantCreateUpdateRequestDto dto);

    @Mapping(source = "reviews" , target = "totalReviews" , qualifiedByName = "populateTotalReviews")
    RestaurantDto toRestaurantDto(Restaurant restaurant);

    @Mapping(source = "reviews" , target = "totalReviews" , qualifiedByName = "populateTotalReviews")
    RestaurantSummaryDto toRestaurantSummaryDto(Restaurant restaurant);

    @Named("populateTotalReviews")
    default Integer populateTotalReviews(List<Review> reviews){
        return reviews.size();
    }

    @Mapping(target = "latitude",expression = "java(geoPoint.getLat())")
    @Mapping(target = "longitude",expression = "java(geoPoint.getLon())")
    GeoPointDto toGeoPointDto(GeoPoint geoPoint);
}
