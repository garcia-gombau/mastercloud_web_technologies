package es.mastercloud.practice2.models.dto;

public record ReviewDTO(
        long id,
        IdAppUserDTO appUser,
        IdBookDTO book,
        String comment,
        int rate
){}
