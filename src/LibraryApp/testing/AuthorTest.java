package LibraryApp.testing;

import LibraryApp.Models.Author;
import LibraryApp.Models.EBook;
import LibraryApp.Models.LibraryItem;
import LibraryApp.Models.PhysicalBook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class which tests the functionality of the Author class.
 */

class AuthorTest {

    private Author author;
    private Author author2;
    private PhysicalBook book;
    private EBook ebook;

    /**
     * Method which sets default values for Author and Book objects
     */

    public void setDefaults() {
        ArrayList<LibraryItem> authorWorks = new ArrayList<>();
        author.set_id(1001);
        author.setName("Michael Kolling");
        author.setAuthorWorks(authorWorks);
    }

    /**
     * Method which is called before each test case.
     */

    @BeforeEach
    void setUp() {
        author = new Author("Michael Kolling");
        author2 = new Author("Wallace Wang");
        book = new PhysicalBook("Bluej", author, "2016", "Pearson", "12345");
        ebook = new EBook("Programming for Dummies", author2, "2006", "John Wiley", "1002");

        setDefaults();
    }

    /**
     * Method which is called after each test case.
     */

    @AfterEach
    void tearDown() {
        setDefaults();
    }

    /**
     * Method which tests the toString method.
     */

    @Test
    void testToString() {
        String expected = "(Author: Michael Kolling)";
        String results = author.toString();

        assertEquals(expected, results);
    }

    /**
     * Method which tests the get_id method.
     */

    @Test
    void get_id() {
        int expected = 1001;
        int results = author.get_id();

        assertEquals(expected, results);
    }

    /**
     * Method which tests the getName method.
     */

    @Test
    void getName() {
        String expected = "Michael Kolling";
        String results = author.getName();

        assertEquals(expected, results);
    }

    /**
     * Method which tests the set_id method.
     */

    @Test
    void set_id() {
        author.set_id(1111);
        assertEquals(1111, author.get_id());
    }

    /**
     * Method which tests the setName method.
     */

    @Test
    void setName() {
        author.setName("New Name");
        assertEquals("New Name", author.getName());
    }

    /**
     * Method which tests the setAuthorWorks and the getAuthorWorks method.
     */

    @Test
    void setAuthorWorks() {
        ArrayList<LibraryItem> works = new ArrayList<>();
        works.add(book);
        author.setAuthorWorks(works);

        LibraryItem testBook = author.getAuthorWorks().get(0);
        assertEquals(testBook, book);
    }

    /**
     * Method which tests the addWork method.
     */

    @Test
    void addWork() {
        author.addWork(book);
        LibraryItem result = author.getAuthorWorks().get(0);

        assertEquals(book, result);
    }
}