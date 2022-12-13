package es.mastercloud.practice2.controller;

import es.mastercloud.practice2.models.dao.Review;
import es.mastercloud.practice2.models.dto.ReviewDTO;
import es.mastercloud.practice2.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping("/")
    public Page<ReviewDTO> readReviews(Pageable page){
        return reviewService.findAll(page);
    }

    @GetMapping("/{id}")
    public ReviewDTO readReview(@PathVariable long id){
        return reviewService.findById(id);
    }

    @PutMapping("/{id}")
    public ReviewDTO updateReview(@PathVariable long id, @RequestBody Review newReview){
        newReview.setId(id);
        return reviewService.replace(newReview);
    }

    @DeleteMapping("/{id}")
    public ReviewDTO deleteReview(@PathVariable long id){
        ReviewDTO review = reviewService.findById(id);
        reviewService.deleteById(id);
        return review;
    }

}
