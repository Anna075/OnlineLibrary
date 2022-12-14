package com.demo.onlineLibraryAnaMariaDoroftei.utils;

import com.demo.onlineLibraryAnaMariaDoroftei.entities.Book;
import com.demo.onlineLibraryAnaMariaDoroftei.entities.BookCategory;
import com.demo.onlineLibraryAnaMariaDoroftei.entities.ReadBook;
import com.demo.onlineLibraryAnaMariaDoroftei.entities.User;
import com.demo.onlineLibraryAnaMariaDoroftei.repositories.BookCategoryRepository;
import com.demo.onlineLibraryAnaMariaDoroftei.repositories.BookRepository;
import com.demo.onlineLibraryAnaMariaDoroftei.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.demo.onlineLibraryAnaMariaDoroftei.enums.Roles.*;

@Component
@RequiredArgsConstructor
public class DbInit implements CommandLineRunner {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BookCategoryRepository bookCategoryRepository;

    private User admin;
    private User librarian;
    private User reader;

    private BookCategory fiction;
    private BookCategory nonfiction;
    private BookCategory drama;
    private BookCategory poetry;
    private BookCategory folktale;

    private Book cosmos;
    private ReadBook readBook;


    @Override
    public void run(String... args) {
        loadUsers();
        loadBookCategories();
        loadBooks();

    }

    private void loadUsers() {
        admin = new User();
        admin.setEmail("admin@gt.com");
        admin.setPassword("cGFzcw==");
        admin.setRoles(Collections.singleton(ADMIN));
        admin.setSurname("Doroftei");
        admin.setFirstname("Ana");
        admin.setPhoneNumber("(+40)712 345 678");
        admin.setJoinDate(new Date());
        admin = userRepository.save(admin);

        librarian = new User();
        librarian.setEmail("librarian@gt.com");
        librarian.setPassword("cGFzcw==");
        librarian.setRoles(Collections.singleton(LIBRARIAN));
        librarian.setSurname("Popescu");
        librarian.setFirstname("Maria");
        librarian.setPhoneNumber("(+40)798 765 432");
        librarian.setJoinDate(new Date());
        librarian = userRepository.save(librarian);

        reader = new User();
        reader.setEmail("reader@gt.com");
        reader.setPassword("cGFzcw==");
        reader.setRoles(Collections.singleton(READER));
        reader.setSurname("Teodorescu");
        reader.setFirstname("Andrei");
        reader.setPhoneNumber("(+40)765 432 109");
        reader.setJoinDate(new Date());
        reader = userRepository.save(reader);
    }

    public void loadBookCategories() {
        fiction = new BookCategory();
        fiction.setName("Fiction");
        fiction = bookCategoryRepository.save(fiction);

        nonfiction = new BookCategory();
        nonfiction.setName("Nonfiction");
        nonfiction = bookCategoryRepository.save(nonfiction);

        drama = new BookCategory();
        drama.setName("Romantic");
        drama = bookCategoryRepository.save(drama);

        poetry = new BookCategory();
        poetry.setName("Poetry");
        poetry = bookCategoryRepository.save(poetry);

        folktale = new BookCategory();
        folktale.setName("Folktale");
        folktale = bookCategoryRepository.save(folktale);
    }

    public void loadBooks() {
        cosmos = new Book();
        cosmos.setIsbn("9780345539434");
        cosmos.setTitle("Cosmos");
        cosmos.setAuthors("Carl Sagan");
        cosmos.setBookCategory(bookCategoryRepository.findByName("Nonfiction"));
        cosmos.setThumbnailURL("https://s13emagst.akamaized.net/products/641/640586/images/res_d99576bf68b0c3bdf5fbe916f274a157.jpg?width=450&height=450&hash=99A2C41BF06945CAFB991F50A3CF22FB");
        cosmos.setDescription("Cosmos is one of the bestselling science books of all time. In clear-eyed prose, Sagan reveals a jewel-like blue world inhabited by a life form that is just beginning to discover its own identity and to venture into the vast ocean of space. Featuring a new Introduction by Sagan’s collaborator, Ann Druyan, full color illustrations, and a new Foreword by astrophysicist Neil deGrasse Tyson, Cosmos retraces the fourteen billion years of cosmic evolution that have transformed matter into consciousness, exploring such topics as the origin of life, the human brain, Egyptian hieroglyphics, spacecraft missions, the death of the Sun, the evolution of galaxies, and the forces and individuals who helped to shape modern science.");
        cosmos.setPublisher("Ballantine Books");
        cosmos.setLanguage("English");
        cosmos.setYearOfPublishing(2013);
        cosmos.setPages(432);
        cosmos = bookRepository.save(cosmos);
    }

//    public void loadReadBooks(){
//
//    }


}