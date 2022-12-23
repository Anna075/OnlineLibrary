package com.demo.onlineLibraryAnaMariaDoroftei.utils;

import com.demo.onlineLibraryAnaMariaDoroftei.entities.Book;
import com.demo.onlineLibraryAnaMariaDoroftei.entities.BookCategory;
import com.demo.onlineLibraryAnaMariaDoroftei.entities.ReadBook;
import com.demo.onlineLibraryAnaMariaDoroftei.entities.User;
import com.demo.onlineLibraryAnaMariaDoroftei.repositories.BookCategoryRepository;
import com.demo.onlineLibraryAnaMariaDoroftei.repositories.BookRepository;
import com.demo.onlineLibraryAnaMariaDoroftei.repositories.ReadBookRepository;
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
    private final ReadBookRepository readBookRepository;

    private User admin;
    private User librarian;
    private User reader;

    private BookCategory fiction;
    private BookCategory nonfiction;
    private BookCategory drama;
    private BookCategory poetry;
    private BookCategory folktale;

    private Book nonfictionBookOne;
    private Book nonfictionBookTwo;
    private Book fictionBookOne;
    private Book fictionBookTwo;
    private Book dramaBookOne;
    private Book poetryBookOne;
    private Book folktaleBookOne;


    private ReadBook readBookOne;
    private ReadBook readBookTwo;
    private ReadBook readBookThree;

    @Override
    public void run(String... args) {
        loadUsers();
        loadBookCategories();
        loadBooks();
        loadReadBooks();
    }

    private void loadUsers() {
        admin = new User();
        admin.setEmail("admin@gt.com");
        admin.setPassword("pass");
        admin.setRoles(Collections.singleton(ADMIN));
        admin.setSurname("Doroftei");
        admin.setFirstname("Ana");
        admin.setPhoneNumber("(+40)712 345 678");
        admin.setJoinDate(new Date());
        admin = userRepository.save(admin);

        librarian = new User();
        librarian.setEmail("librarian@gt.com");
        librarian.setPassword("pass");
        librarian.setRoles(Collections.singleton(LIBRARIAN));
        librarian.setSurname("Popescu");
        librarian.setFirstname("Maria");
        librarian.setPhoneNumber("(+40)798 765 432");
        librarian.setJoinDate(new Date());
        librarian = userRepository.save(librarian);

        reader = new User();
        reader.setEmail("reader@gt.com");
        reader.setPassword("pass");
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
        drama.setName("Drama");
        drama = bookCategoryRepository.save(drama);

        poetry = new BookCategory();
        poetry.setName("Poetry");
        poetry = bookCategoryRepository.save(poetry);

        folktale = new BookCategory();
        folktale.setName("Folktale");
        folktale = bookCategoryRepository.save(folktale);
    }

    public void loadBooks() {
        nonfictionBookOne = new Book();
        nonfictionBookOne.setIsbn("9780345539434");
        nonfictionBookOne.setTitle("Cosmos");
        nonfictionBookOne.setAuthors("Carl Sagan");
        nonfictionBookOne.setBookCategory(nonfiction);
        nonfictionBookOne.setThumbnailURL("https://s13emagst.akamaized.net/products/641/640586/images/res_d99576bf68b0c3bdf5fbe916f274a157.jpg?width=450&height=450&hash=99A2C41BF06945CAFB991F50A3CF22FB");
        nonfictionBookOne.setDescription("Cosmos is one of the bestselling science books of all time. In clear-eyed prose, Sagan reveals a jewel-like blue world inhabited by a life form that is just beginning to discover its own identity and to venture into the vast ocean of space. Featuring a new Introduction by Sagan’s collaborator, Ann Druyan, full color illustrations, and a new Foreword by astrophysicist Neil deGrasse Tyson, Cosmos retraces the fourteen billion years of cosmic evolution that have transformed matter into consciousness, exploring such topics as the origin of life, the human brain, Egyptian hieroglyphics, spacecraft missions, the death of the Sun, the evolution of galaxies, and the forces and individuals who helped to shape modern science.");
        nonfictionBookOne.setPublisher("Ballantine Books");
        nonfictionBookOne.setLanguage("Engleza");
        nonfictionBookOne.setYearOfPublishing(2013);
        nonfictionBookOne.setPages(432);
        nonfictionBookOne = bookRepository.save(nonfictionBookOne);

        nonfictionBookTwo = new Book();
        nonfictionBookTwo.setIsbn("9781448190690");
        nonfictionBookTwo.setTitle("Sapiens. Scurta istorie a omenirii");
        nonfictionBookTwo.setAuthors("Yuval Noah Harari");
        nonfictionBookTwo.setBookCategory(nonfiction);
        nonfictionBookTwo.setThumbnailURL("https://s13emagst.akamaized.net/products/8341/8340701/images/res_f294ed48376589a8b0aa37c05beca909.jpg");
        nonfictionBookTwo.setDescription("Acum 100.000 de ani, pe pamint existau cel putin sase specii de oameni. Astazi exista una singura: noi, Homo sapiens. Ce s-a intimplat cu celelalte? Si cum am ajuns sa fim stapinii planetei? De la inceputurile speciei noastre si rolul pe care l-a jucat in ecosistemul global pina in modernitate, Sapiens imbina istoria si stiinta pentru a pune in discutie tot ce stim despre noi insine, ne arata cum ne-am unit ca sa construim orase, regate si imperii, cum am ajuns sa credem in zei, in legi si in carti, dar si cum am devenit sclavii birocratiei, ai consumerismului si ai cautarii fericirii.");
        nonfictionBookTwo.setPublisher("Polirom");
        nonfictionBookTwo.setLanguage("Romana");
        nonfictionBookTwo.setYearOfPublishing(2017);
        nonfictionBookTwo.setPages(384);
        nonfictionBookTwo = bookRepository.save(nonfictionBookTwo);

        fictionBookOne = new Book();
        fictionBookOne.setIsbn("9781448190693");
        fictionBookOne.setTitle("Hoţul de cărţi");
        fictionBookOne.setAuthors("Markus Zusak");
        fictionBookOne.setBookCategory(fiction);
        fictionBookOne.setThumbnailURL("https://mcdn.elefant.ro/mnresize/150/150/images/82/157982/hotul-de-carti_1_fullsize.jpg");
        fictionBookOne.setDescription("Este anul 1939. Germania nazista. Tara isi tine rasuflarea. Moartea nu a avut niciodata mai mult de lucru, si va deveni chiar mai ocupata. Liesel Meminger si fratele ei mai mic sunt dusi de catre mama lor sa locuiasca cu o familie sociala in afara orasului München. Tatal lui Liesel a fost dus departe sub soapta unui singur cuvant nefamiliar - Kommunist -, iar Liesel vede in ochii mamei sale teama unui destin similar. Pe parcursul calatoriei, Moartea ii face o vizita baietelului si o observa pe Liesel. Va fi prima dintre multe intalniri apropiate. Langa mormantul fratelui ei, viata lui Liesel se schimba atunci cand ea ridica un singur obiect, ascuns partial in zapada. Este Manualul Groparului, lasat acolo din greseala, si este prima ei carte furata.");
        fictionBookOne.setPublisher("RAO");
        fictionBookOne.setLanguage("Romana");
        fictionBookOne.setYearOfPublishing(2021);
        fictionBookOne.setPages(440);
        fictionBookOne = bookRepository.save(fictionBookOne);

        fictionBookTwo = new Book();
        fictionBookTwo.setIsbn("9780151002177");
        fictionBookTwo.setTitle("Ferma Animalelor");
        fictionBookTwo.setAuthors("George Orwell");
        fictionBookTwo.setBookCategory(fiction);
        fictionBookTwo.setThumbnailURL("https://cdn4.libris.ro/img/pozeprod/59/1010/BF/376683-KKAX.jpg");
        fictionBookTwo.setDescription("Ferma Animalelor a inspirat o celebra animatie difuzata in anul 1954, reluata in 1999 de un film in care vocile personajelor au fost dublate, printre altii, de Patrick Stewart, Julia Louis-Dreyfus si Peter Ustinov.");
        fictionBookTwo.setPublisher("Polirom");
        fictionBookTwo.setLanguage("Romana");
        fictionBookTwo.setYearOfPublishing(2021);
        fictionBookTwo.setPages(192);
        fictionBookTwo = bookRepository.save(fictionBookTwo);

        dramaBookOne = new Book();
        dramaBookOne.setIsbn("9789731047836");
        dramaBookOne.setTitle("Romeo și Julieta");
        dramaBookOne.setAuthors("William Shakespeare");
        dramaBookOne.setBookCategory(drama);
        dramaBookOne.setThumbnailURL("https://cdn.dc5.ro/img-prod/122410032-0.jpeg");
        dramaBookOne.setDescription("Romeo şi Julieta, cea mai minunată scriere despre iubirea petrecută între un bărbat şi o femeie pe care a produs-o cultura europeană, spune un singur lucru: în lumea omului nu există «sex gol». Aici sărutul nu e cioraniana «întâlnire între două salive», iar îmbrăţişarea dintre două fiinţe care se iubesc nu e acuplare. Felul în care Romeo şi Julieta se ating pentru prima oară (întâlnirea mâinilor) şi se sărută pentru prima oară pune în mişcare o asemenea suită de strategii spirituale (realizată printr-o formidabilă mobilizare a cuvintelor), încât cei doi reuşesc să-i dea sexului, din prima clipă a întâlnirii lor, dimensiunea sacrului. Sărutul slujeşte la ceva: la îndumnezeire.");
        dramaBookOne.setPublisher("Cartex");
        dramaBookOne.setLanguage("Romana");
        dramaBookOne.setYearOfPublishing(2018);
        dramaBookOne.setPages(174);
        dramaBookOne = bookRepository.save(dramaBookOne);

        poetryBookOne = new Book();
        poetryBookOne.setIsbn("9789731853260");
        poetryBookOne.setTitle("Poezii");
        poetryBookOne.setAuthors("Mihai Eminescu");
        poetryBookOne.setBookCategory(poetry);
        poetryBookOne.setThumbnailURL("https://cdn4.libris.ro/img/pozeprod/59/1000/29/1255748-YPNK.jpg");
        poetryBookOne.setDescription("Eminescu este un om al timpului modern, cultura lui individuala sta la nivelul culturei europene de astazi. Cu neobosita lui staruinta de a ceti, de a studia, de a cunoaste, el isi inzestra fara preget memoria cu operile insemnate din literatura antica si moderna. Cunoscator al filozofiei, in special a lui Platon, Kant si Schopenhauer, si nu mai putin al credintelor religioase, mai ales al celei crestine si buddaiste, admirator al Vedelor, pasionat pentru operele poetice din toate timpurile, posedand stiinta celor publicate pana astazi din istoria si limba romana, el afla in comoara ideilor astfel culese materialul concret de unde sa-si formeze inalta abstractiune care in poeziile lui ne deschide asa de des orizontul fara margini al gandirii omenesti.");
        poetryBookOne.setPublisher("EXIGENT");
        poetryBookOne.setLanguage("Romana");
        poetryBookOne.setYearOfPublishing(2021);
        poetryBookOne.setPages(336);
        poetryBookOne = bookRepository.save(poetryBookOne);

        folktaleBookOne = new Book();
        folktaleBookOne.setIsbn("9786068578088");
        folktaleBookOne.setTitle("Hänsel și Gretel");
        folktaleBookOne.setAuthors("Jacob Grimm, Wilhelm Grimm");
        folktaleBookOne.setBookCategory(folktale);
        folktaleBookOne.setThumbnailURL("https://cdn.dc5.ro/img-prod/415273-0-240.jpg");
        folktaleBookOne.setDescription("„Hansel si Gretel” este o poveste emotionanta semnata de Fratii Grimm, transpusa in nenumarate piese de teatru si spectacole pentru cei mici. Poate fi o amintire de neuitat pentru copiii care o descopera singuri, in timp ce invata sa citeasca.\n" +
                "Dupa ce au ramas singuri in padure, Hansel si Gretel au gasit drumul inapoi spre casa urmand darele de pietricele pe care le-au presarat in calea lor. Ramasi a doua oara singuri, nu au reusit sa se mai intoarca – pasarelele au mancat firimiturile pe care cei doi copii le presarasera pe carare de data aceasta. Afundandu-se in padure, Hansel si Gretel ajung la faimoasa casuta din turta dulce, cu geamuri de zahar si acoperis de cozonac. Restul e poveste...");
        folktaleBookOne.setPublisher("ALL");
        folktaleBookOne.setLanguage("Romana");
        folktaleBookOne.setYearOfPublishing(2014);
        folktaleBookOne.setPages(48);
        folktaleBookOne = bookRepository.save(folktaleBookOne);
    }

    public void loadReadBooks(){
        readBookOne = new ReadBook();
        readBookOne.setBook(fictionBookOne);
        readBookOne.setUser(reader);
        readBookOne = readBookRepository.save(readBookOne);

        readBookTwo = new ReadBook();
        readBookTwo.setBook(dramaBookOne);
        readBookTwo.setUser(reader);
        readBookTwo = readBookRepository.save(readBookTwo);

        readBookThree = new ReadBook();
        readBookThree.setBook(nonfictionBookOne);
        readBookThree.setUser(reader);
        readBookThree = readBookRepository.save(readBookThree);
    }
}