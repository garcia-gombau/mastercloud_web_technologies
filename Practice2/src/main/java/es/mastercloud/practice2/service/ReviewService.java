package es.mastercloud.practice2.service;

import es.mastercloud.practice2.mapper.ReviewMapper;
import es.mastercloud.practice2.models.dao.Review;
import es.mastercloud.practice2.models.dto.InReviewDTO;
import es.mastercloud.practice2.models.dto.ReviewDTO;
import es.mastercloud.practice2.service.repository.AppUserRepository;
import es.mastercloud.practice2.service.repository.BookRepository;
import es.mastercloud.practice2.service.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviews;
    @Autowired
    private BookRepository books;
    @Autowired
    private AppUserRepository appUsers;

    @Autowired
    private ReviewMapper reviewMapper;

    public ReviewDTO save(InReviewDTO review){
        return reviewMapper.toReviewDTO(reviews.save(reviewMapper.toReviewFromInReviewDTO(review)));
    }

    public Page<ReviewDTO> findAll(Pageable page){
        return reviews.findAll(page).map(review -> reviewMapper.toReviewDTO(review));
    }

    public ReviewDTO findById(long id){
        return reviewMapper.toReviewDTO(reviews.findById(id).orElseThrow());
    }
    
    public Page<ReviewDTO> findByUserId(long id, Pageable page){
        return reviews.findByUserId(id, page).map(review -> reviewMapper.toReviewDTO(review));
    }

    public Page<ReviewDTO> findByBookId(long id, Pageable page){
        return reviews.findByBookId(id, page).map(review -> reviewMapper.toReviewDTO(review));
    }

    public ReviewDTO replace(Review updatedReview){
        appUsers.findById(updatedReview.getAppUser().getId()).orElseThrow();
        books.findById(updatedReview.getBook().getId()).orElseThrow();
        reviews.findById(updatedReview.getId()).orElseThrow();
        return reviewMapper.toReviewDTO(reviews.save(updatedReview));
    }

    public void deleteById(long id){
        reviews.deleteById(id);
    }

}
