package es.mastercloud.practice2.service.repository;

import es.mastercloud.practice2.models.dao.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
