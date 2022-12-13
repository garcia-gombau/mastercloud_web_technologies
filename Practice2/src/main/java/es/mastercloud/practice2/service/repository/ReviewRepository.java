package es.mastercloud.practice2.service.repository;

import es.mastercloud.practice2.models.dao.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r FROM Review r where r.appUser.id = ?1")
    public Page<Review> findByUserId(long id, Pageable page);

    @Query("select r FROM Review r where r.appUser.id = ?1")
    public List<Review> findByUserId(long id);

    @Query("select r FROM Review r where r.book.id = ?1")
    public Page<Review> findByBookId(long bookId, Pageable page);

    @Query("select r FROM Review r where r.book.id = ?1")
    public List<Review> findByBookId(long bookId);

}
