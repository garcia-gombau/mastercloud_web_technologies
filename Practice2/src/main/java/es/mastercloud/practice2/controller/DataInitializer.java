package es.mastercloud.practice2.controller;

import es.mastercloud.practice2.models.dao.AppUser;
import es.mastercloud.practice2.models.dao.Book;
import es.mastercloud.practice2.models.dao.Review;
import es.mastercloud.practice2.service.repository.AppUserRepository;
import es.mastercloud.practice2.service.repository.BookRepository;
import es.mastercloud.practice2.service.repository.ReviewRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataInitializer {
    @Autowired
    private ReviewRepository reviews;
    @Autowired
    private BookRepository books;
    @Autowired
    private AppUserRepository appUsers;
    @PostConstruct
    private void init(){
        AppUser user0 = appUsers.save(new AppUser("username0", "username0@ema.il"));
        AppUser user1 = appUsers.save(new AppUser("username1", "username1@ema.il"));
        Book book0 = books.save(new Book("The Stranger", "The protagonist, Mr. Meursault, will never speak out against his execution or show any feeling of injustice, regret or pity. Passivity and skepticism towards everything and everyone recover the behavior of the protagonist: an apathetic sense of existence and even death itself.", "Albert Camus", "Editions Gallimard", "1942", new ArrayList<>()));
        Book book1 = books.save(new Book("Enchiridion", "A practical philosophical guide instructing readers on how to live well from a primarily social perspective. In such a setting, The Enchiridion encourages one to enjoy the habits of moderation and modesty.", "Epictetus", "Secker & Warburg", "125", new ArrayList<>()));
        Book book2 = books.save(new Book("1984", "A man tries to keep hope in a totalitarian and repressive society. After falling in love with a young girl, they try to keep their love alive but hidden from the all-knowing Big Brother. This dark and virtual dictator seems to know what each one thinks in the corners of the mind.", "George Orwell", "Secker & Warburg", "1949", new ArrayList<>()));
        Review review0 = reviews.save(new Review(user0, book0, "Nice", 3));
        Review review1 = reviews.save(new Review(user1, book2, "Amazing", 5));
    }
}
