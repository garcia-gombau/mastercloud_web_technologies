package es.mastercloud.practice2.models.dao;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;


@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    @Length(max = 1000)
    private String synopsis;
    private String author;
    private String publisher;
    private String publishYear;
    @OneToMany(mappedBy="book", cascade = CascadeType.REMOVE)
    private List<Review> reviews;

    public Book() {}

    public Book(long id) {
        this.id = id;
    }

    public Book(String title, String synopsis, String author, String publisher, String publishYear, List<Review> reviews) {
        this.title = title;
        this.synopsis = synopsis;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.reviews = reviews;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    public void addReviews(Review review) {
        this.reviews.add(review);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishYear='" + publishYear + '\'' +
                ", reviews=" + reviews +
                '}';
    }
}
