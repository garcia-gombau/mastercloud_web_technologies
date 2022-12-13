package es.mastercloud.practice2.service;

import es.mastercloud.practice2.mapper.BookMapper;
import es.mastercloud.practice2.models.dao.Book;
import es.mastercloud.practice2.models.dto.BasicBookDTO;
import es.mastercloud.practice2.models.dto.BookDTO;
import es.mastercloud.practice2.models.dto.InBookDTO;
import es.mastercloud.practice2.service.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository books;
    @Autowired
    private BookMapper bookMapper;

    public BookDTO save(InBookDTO book){
        return bookMapper.toBookDTO(books.save(bookMapper.toBookFromInBookDTO(book)));
    }

    public Page<BookDTO> findAll(Pageable page){
        return books.findAll(page).map(book -> bookMapper.toBookDTO(book));
    }

    public Optional<BookDTO> findById(long id){
        return books.findById(id).map(book -> bookMapper.toBookDTO(book));
    }

    public BookDTO replace(Book updatedBook){
        books.findById(updatedBook.getId()).orElseThrow();
        return bookMapper.toBookDTO(books.save(updatedBook));
    }

    public void deleteById(long id){
        books.deleteById(id);
    }

    public Page<BasicBookDTO> findBasicAll(Pageable page) {
        return books.findAll(page).map(book -> bookMapper.toBasicDTO(book));
    }
}
