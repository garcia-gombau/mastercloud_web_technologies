package es.mastercloud.practice2.mapper;

import es.mastercloud.practice2.models.dao.Review;
import es.mastercloud.practice2.models.dto.BookReviewDTO;
import es.mastercloud.practice2.models.dto.InReviewDTO;
import es.mastercloud.practice2.models.dto.ReviewDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AppUserMapper.class})
public interface ReviewMapper {
    ReviewDTO toReviewDTO(Review review);
    BookReviewDTO toBookReviewDTO(Review review);
    Review toReviewFromInReviewDTO(InReviewDTO review);
}
