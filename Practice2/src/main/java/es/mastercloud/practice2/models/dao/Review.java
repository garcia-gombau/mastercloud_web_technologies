package es.mastercloud.practice2.models.dao;

import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private AppUser appUser;
    @ManyToOne
    private Book book;
    private String comment;
    private int rate;

    public Review() {
    }

    public Review(AppUser appUser, Book book, String comment, int rate) {
        this.appUser = appUser;
        this.book = book;
        this.comment = comment;
        this.rate = rate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", appUser=" + appUser +
                ", book=" + book +
                ", comment='" + comment + '\'' +
                ", rate=" + rate +
                '}';
    }
}
