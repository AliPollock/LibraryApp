package LibraryApp.testing;

import LibraryApp.Models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class which tests the functionality of the Physical book class and the Library Item class it inherits from.
 */

class PhysicalBookTest {

    private Author author;
    private PhysicalBook book;

    /**
     * Method which sets default values for Author and Book objects
     */

    public void setDefaults() {
        ArrayList<LibraryItem> authorWorks = new ArrayList<>();
        ArrayList<String> Topics = new ArrayList<>();
        this.author.setAuthorWorks(authorWorks);
        this.book.removeCurrentUser();
        this.book.setOverDueCharge(0.0);
        this.book.setOverdue(false);
        this.book.setOnLoan(false);
        this.book.setDueDate(null);
        this.book.removeDamages();
        this.book.set_id(100001);
        this.book.setName("Bluej");
        this.book.setAuthor(author);
        this.book.setPublicationDate("2016");
        this.book.setPublisher("Pearson");
        this.book.setTopics(Topics);
        this.book.setBio("");
        this.book.setISBN("001");
        this.book.setTimesRead(0);
    }

    /**
     * Method which is called before each test case.
     */

    @BeforeEach
    void setUp() {
        author = new Author("Michael Kolling");
        book = new PhysicalBook("Bluej", author, "2016", "Pearson", "12345");
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
        String expected = "PhysicalBook{" +
                "_id=100001, name='Bluej', author=Michael Kolling, publicationDate='2016', publisher='Pearson'," +
                " topics=[], timesRead=0, bio='', iSBN='001', isOnLoan=false, isOverdue=false, overDueCharge=0.0, dueDate='null', damages=''";
        String result = book.toString();

        assertEquals(expected, result);
    }

    /**
     * Method which tests the get_id method.
     */

    @Test
    void get_id() {
        int expected = 100001;
        int result = book.get_id();

        assertEquals(expected,result);
    }

    /**
     * Method which tests the getName method.
     */

    @Test
    void getName() {
        String expected = "Bluej";
        String result = book.getName();

        assertEquals(expected, result);
    }

    /**
     * Method which tests the Author method.
     */

    @Test
    void getAuthor() {
        String expected = "Michael Kolling";
        String result = book.getAuthor().getName();

        assertEquals(expected, result);
    }

    /**
     * Method which tests the getPublicationDate method.
     */

    @Test
    void getPublicationDate() {
       String expected = "2016";
       String result = book.getPublicationDate();

        assertEquals(expected, result);
    }

    /**
     * Method which tests the getPublisher method.
     */

    @Test
    void getPublisher() {
        String expected = "Pearson";
        String result = book.getPublisher();

        assertEquals(expected, result);
    }

    /**
     * Method which tests the getTopics and setTopics methods.
     */

    @Test
    void getTopics() {
        ArrayList<String> controlTest = new ArrayList<>();
        controlTest.add("test");
        controlTest.add("second test");

        ArrayList<String> topicsTest = new ArrayList<>();
        topicsTest.add("test");
        topicsTest.add("second test");

        book.setTopics(topicsTest);
        assertEquals(book.getTopics(), controlTest);
    }

    /**
     * Method which tests the getTopicsAsString method.
     */

    @Test
    void getTopicsAsString() {
        ArrayList<String> topicsTest = new ArrayList<>();
        topicsTest.add("test");
        topicsTest.add("second test");

        book.setTopics(topicsTest);
        assertEquals("test, second test", book.getTopicsAsString());
    }

    /**
     * Method which tests the getBio and setBio methods.
     */

    @Test
    void getBio() {
        book.setBio("This is a test bio");
        String expected = "This is a test bio";
        String result = book.getBio();

        assertEquals(expected, result);
    }

    /**
     * Method which tests the getISBN method.
     */

    @Test
    void getISBN() {
        String expected = "001";
        String result = book.getISBN();

        assertEquals(expected, result);
    }

    /**
     * Method which tests the setTimesRead and the getTimesRead methods.
     */

    @Test
    void getTimesRead() {
        book.setTimesRead(5);
        int expected = 5;

        assertEquals(expected, book.getTimesRead());
    }

    /**
     * Method which tests the set_id method.
     */

    @Test
    void set_id() {
        book.set_id(1111);
        int expected = 1111;

        assertEquals(book.get_id(), expected);
    }

    /**
     * Method which tests the setName method.
     */

    @Test
    void setName() {
        book.setName("Ali");
        String expected = "Ali";

        assertEquals(expected, book.getName());
    }

    /**
     * Method which tests the setAuthor method.
     */

    @Test
    void setAuthor() {
        Author testAuthor = new Author("David Barnes");
        book.setAuthor(testAuthor);

        assertEquals(book.getAuthor().getName(), "David Barnes");
    }

    /**
     * Method which tests the setPublicationDate method.
     */

    @Test
    void setPublicationDate() {
        book.setPublicationDate("2017");

        assertEquals(book.getPublicationDate(), "2017");
    }

    /**
     * Method which tests the setPublisher method.
     */

    @Test
    void setPublisher() {
        book.setPublisher("Test publisher");

        assertEquals(book.getPublisher(), "Test publisher");
    }

    /**
     * Method which tests the setTimesRead method.
     */

    @Test
    void setTimesRead() {
        book.setTimesRead(5);

        assertEquals(book.getTimesRead(), 5);
    }

    /**
     * Method which tests the setISBN method.
     */

    @Test
    void setISBN() {
        book.setISBN("1111");

        assertEquals(book.getISBN(), "1111");
    }

    /**
     * Method which tests the setTopicsCSV method.
     */

    @Test
    void setTopicsCSV() {
        book.setTopicsCSV("First Topic");
        String result = book.getTopics().get(0);
        String expected = "First Topic";

        assertEquals(expected, result);
    }

    /**
     * Method which tests the incrementTimesRead method.
     */

    @Test
    void incrementTimesRead() {
        book.incrementTimesRead();

        assertEquals(1, book.getTimesRead());
    }

    /**
     * Method which tests the isOverdue and setOverdue methods.
     */


    @Test
    void isOverdue() {
        book.setOverdue(true);

        assertTrue(book.isOverdue());
    }

    /**
     * Method which tests the getOverDueCharge and setOverDueCharge methods.
     */

    @Test
    void getOverDueCharge() {
        book.setOverDueCharge(10.0);
        assertEquals(10.0, book.getOverDueCharge());
    }

    /**
     * Method which tests the getDueDate and setDueDate methods.
     */

    @Test
    void getDueDate() {
        book.setDueDate("04/12/2020");
        assertEquals("04/12/2020", book.getDueDate());
    }

    /**
     * Method which tests the getCurrentUser and setCurrentUser method.
     */

    @Test
    void getCurrentUser() {
        Student user = new Student("testUsername", "password");
        book.setCurrentUser(user);
        String expected = "testUsername";

        assertEquals(expected, book.getCurrentUser().getName());
    }

    /**
     * Method which tests the isOnLoan and changeLoanStatus methods.
     */

    @Test
    void isOnLoan() {
        book.changeLoanStatus();

        assertTrue(book.isOnLoan());
    }

    /**
     * Method which tests the getDamages and setDamages methods.
     */

    @Test
    void getDamages() {
        book.setDamages("test damage");
        String expected = "test damage";

        assertEquals(expected, book.getDamages());
    }

    /**
     * Method which tests the setOnLoan method.
     */

    @Test
    void setOnLoan() {
        book.setOnLoan(true);

        assertTrue(book.isOnLoan());
    }

    /**
     * Method which tests the changeOverdueStatus method.
     */


    @Test
    void changeOverdueStatus() {
        book.changeOverdueStatus();

        assertTrue(book.isOverdue());
    }

    /**
     * Method which tests the  removeCurrentUser method.
     */

    @Test
    void removeCurrentUser() {
        Student user = new Student("testUsername", "password");
        book.setCurrentUser(user);
        book.removeCurrentUser();

        assertNull(book.getCurrentUser());
    }

    /**
     * Method which tests the method removeDamages method.
     */

    @Test
    void removeDamages() {
        book.setDamages("test damage");
        book.removeDamages();

        assertEquals("", book.getDamages());

    }
}