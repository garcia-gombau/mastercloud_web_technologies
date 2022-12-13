package es.mastercloud.practice2.controller;

import es.mastercloud.practice2.models.dao.Book;
import es.mastercloud.practice2.models.dao.Review;
import es.mastercloud.practice2.models.dto.*;
import es.mastercloud.practice2.service.BookService;
import es.mastercloud.practice2.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    ReviewService reviewService;

    @Operation(summary = "Creates a book")
    @ApiResponses(value = {@ApiResponse(
                    responseCode = "200",
                    description = "Returns the created book",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation= Book.class)
                    )})
    })
    @PostMapping("/")
    public ResponseEntity<BookDTO> createBook(@RequestBody InBookDTO book){
        BookDTO bookDTO = bookService.save(book);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(bookDTO.id()).toUri();
        return ResponseEntity.created(location).body(bookDTO);
    }

    @Operation(summary = "Returns the all books")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Returns the all books",
            content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation= Book.class)
            )})
    })
    @GetMapping("/")
    public Page<BookDTO> readBooks(Pageable page){
        return bookService.findAll(page);
    }

    @Operation(summary = "Returns the all books")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Returns the all books",
            content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation= Book.class)
            )})
    })
    @GetMapping("/titles")
    public Page<BasicBookDTO> readBasicBooks(Pageable page){
        return bookService.findBasicAll(page);
    }

    @Operation(summary = "Asks for a book")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Returns the desired book",
            content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation= Book.class)
            )}), @ApiResponse(
            responseCode = "404",
            description = "The book was not found",
            content = @Content
            )
    })
    @GetMapping("/{id}")
    public BookDTO readBookById(@PathVariable long id){
        return bookService.findById(id).orElseThrow();
    }

    @Operation(summary = "Asks for a book update")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Returns the updated book",
            content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation= Book.class)
            )}), @ApiResponse(
            responseCode = "404",
            description = "The book was not found",
            content = @Content
            )
    })
    @PutMapping("/{id}")
    public BookDTO updateBook(@RequestBody Book newBook, @PathVariable long id){
        newBook.setId(id);
        return bookService.replace(newBook);
    }

    @Operation(summary = "Asks for a book deletion")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Returns the deleted book",
            content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation= Book.class)
            )}), @ApiResponse(
            responseCode = "404",
            description = "The book was not found",
            content = @Content
            )
    })
    @DeleteMapping("/{id}")
    public BookDTO deleteBook(@PathVariable long id){
        BookDTO book = bookService.findById(id).orElseThrow();
        bookService.deleteById(id);
        return book;
    }

    @Operation(summary = "Creates a book review")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Returns the book's review",
            content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation= Review.class)
            )}), @ApiResponse(
            responseCode = "404",
            description = "The book was not found",
            content = @Content
    )
    })
    @PostMapping("/{id}/reviews")
    public ResponseEntity<ReviewDTO> createReview(@RequestBody InReviewDTO review, @PathVariable long id){
        ReviewDTO reviewDTO = reviewService.save(review);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(reviewDTO.id()).toUri();
        return ResponseEntity.created(location).body(reviewDTO);
    }

    @Operation(summary = "Asks for a book reviews")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Returns the book's reviews",
            content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation= Review.class)
            )}), @ApiResponse(
            responseCode = "404",
            description = "The book was not found",
            content = @Content
            )
    })
    @GetMapping("/{id}/reviews")
    public Page<ReviewDTO> readReviews(@PathVariable long id, Pageable page){
        return reviewService.findByBookId(id, page);
    }

}
