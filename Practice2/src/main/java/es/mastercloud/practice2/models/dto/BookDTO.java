package es.mastercloud.practice2.models.dto;

import java.util.List;

public record BookDTO (
        long id,
        String title,
        String synopsis,
        String author,
        String publisher,
        String publishYear,
        List<BookReviewDTO> reviews
){}
