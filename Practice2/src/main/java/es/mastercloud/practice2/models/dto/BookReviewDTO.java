package es.mastercloud.practice2.models.dto;

public record BookReviewDTO(
   long id,
   IdAppUserDTO appUser,
   String comment,
   int rate
) {}
