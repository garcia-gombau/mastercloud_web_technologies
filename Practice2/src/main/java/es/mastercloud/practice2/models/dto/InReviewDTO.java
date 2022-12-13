package es.mastercloud.practice2.models.dto;

public record InReviewDTO(
        IdAppUserDTO appUser,
        IdBookDTO book,
        String comment,
        int rate
) {}
