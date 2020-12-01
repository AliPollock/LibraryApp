package LibraryApp.testing;

import LibraryApp.Models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class which tests the functionality of the EBook class and the Library Item class it inherits from.
 */

class EBookTest {

    private Author author;
    private EBook ebook;
    private Computer computer;

    /**
     * Method which sets default values for Author and EBook objects
     */

    public void setDefaults() {
        ArrayList<LibraryItem> authorWorks = new ArrayList<>();
        ArrayList<String> Topics = new ArrayList<>();
        ArrayList<Computer> devices = new ArrayList<>();
        this.author.setAuthorWorks(authorWorks);
        this.ebook.setCurrentUsers(0);
        this.ebook.set_id(100001);
        this.ebook.setName("Bluej");
        this.ebook.setAuthor(author);
        this.ebook.setPublicationDate("2016");
        this.ebook.setPublisher("Pearson");
        this.ebook.setTopics(Topics);
        this.ebook.setBio("");
        this.ebook.setISBN("001");
        this.ebook.setTimesRead(0);
        this.ebook.setAccessExpiresHours(null);
        this.ebook.setDevices(devices);
    }

    /**
     * Method which is called before each test case.
     */

    @BeforeEach
    void setUp() {
        computer = new Computer();
        author = new Author("Michael Kolling");
        ebook = new EBook("Bluej", author, "2016", "Pearson", "12345");
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
        String expected = "EBook {_id=100001, name='Bluej', author=(Author: Michael Kolling), publicationDate='2016'" +
                            ", publisher='Pearson', topics=[], timesRead=0, bio='', iSBN='001', currentUsers=0, accessExpiresHours='null', devices=[]}";
        String result = ebook.toString();

        assertEquals(expected, result);
    }

    /**
     * Method which tests the get_id method.
     */

    @Test
    void get_id() {
        int expected = 100001;
        int result = ebook.get_id();

        assertEquals(expected,result);
    }

    /**
     * Method which tests the getName method.
     */

    @Test
    void getName() {
        String expected = "Bluej";
        String result = ebook.getName();

        assertEquals(expected, result);
    }

    /**
     * Method which tests the Author method.
     */

    @Test
    void getAuthor() {
        String expected = "Michael Kolling";
        String result = ebook.getAuthor().getName();

        assertEquals(expected, result);
    }

    /**
     * Method which tests the getPublicationDate method.
     */

    @Test
    void getPublicationDate() {
        String expected = "2016";
        String result = ebook.getPublicationDate();

        assertEquals(expected, result);
    }

    /**
     * Method which tests the getPublisher method.
     */

    @Test
    void getPublisher() {
        String expected = "Pearson";
        String result = ebook.getPublisher();

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

        ebook.setTopics(topicsTest);
        assertEquals(ebook.getTopics(), controlTest);
    }

    /**
     * Method which tests the getTopicsAsString method.
     */

    @Test
    void getTopicsAsString() {
        ArrayList<String> topicsTest = new ArrayList<>();
        topicsTest.add("test");
        topicsTest.add("second test");

        ebook.setTopics(topicsTest);
        assertEquals("test, second test", ebook.getTopicsAsString());
    }

    /**
     * Method which tests the getBio and setBio methods.
     */

    @Test
    void getBio() {
        ebook.setBio("This is a test bio");
        String expected = "This is a test bio";
        String result = ebook.getBio();

        assertEquals(expected, result);
    }

    /**
     * Method which tests the getISBN method.
     */

    @Test
    void getISBN() {
        String expected = "001";
        String result = ebook.getISBN();

        assertEquals(expected, result);
    }

    /**
     * Method which tests the setTimesRead and getTimesRead methods.
     */

    @Test
    void getTimesRead() {
        ebook.setTimesRead(5);
        int expected = 5;

        assertEquals(expected, ebook.getTimesRead());
    }

    /**
     * Method which tests the set_id method.
     */

    @Test
    void set_id() {
        ebook.set_id(1111);
        int expected = 1111;

        assertEquals(ebook.get_id(), expected);
    }

    /**
     * Method which tests the setName method.
     */

    @Test
    void setName() {
        ebook.setName("Ali");
        String expected = "Ali";

        assertEquals(expected, ebook.getName());
    }

    /**
     * Method which tests the setAuthor method.
     */

    @Test
    void setAuthor() {
        Author testAuthor = new Author("David Barnes");
        ebook.setAuthor(testAuthor);

        assertEquals(ebook.getAuthor().getName(), "David Barnes");
    }

    /**
     * Method which tests the setPublicationDate method.
     */

    @Test
    void setPublicationDate() {
        ebook.setPublicationDate("2017");

        assertEquals(ebook.getPublicationDate(), "2017");
    }

    /**
     * Method which tests the setPublisher method.
     */

    @Test
    void setPublisher() {
        ebook.setPublisher("Test publisher");

        assertEquals(ebook.getPublisher(), "Test publisher");
    }

    /**
     * Method which tests the setTimesRead method.
     */

    @Test
    void setTimesRead() {
        ebook.setTimesRead(5);

        assertEquals(ebook.getTimesRead(), 5);
    }

    /**
     * Method which tests the setISBN method.
     */

    @Test
    void setISBN() {
        ebook.setISBN("1111");

        assertEquals(ebook.getISBN(), "1111");
    }

    /**
     * Method which tests the setTopicsCSV method.
     */

    @Test
    void setTopicsCSV() {
        ebook.setTopicsCSV("First Topic");
        String result = ebook.getTopics().get(0);
        String expected = "First Topic";

        assertEquals(expected, result);
    }


    /**
     * Method which tests the incrementTimesRead method.
     */

    @Test
    void incrementTimesRead() {
        ebook.incrementTimesRead();

        assertEquals(1, ebook.getTimesRead());
    }

    /**
     * Method that tests the getCurrentUsers and addCurrentUsers methods.
     */

    @Test
    void getCurrentUsers() {
        ebook.addCurrentReader();
        int result = ebook.getCurrentUsers();
        assertEquals(1, result);
    }

    /**
     * Method that tests the static getMaximumConcurrentUsers method.
     */

    @Test
    void getMaximumConcurrentUsers() {
        assertEquals(10, EBook.getMaximumConcurrentUsers());
    }

    /**
     * Method that tests the setAccessExpiresHours and getAccessExpiresHours methods.
     */

    @Test
    void getAccessExpiresHours() {
        String expected = "9-12";
        ebook.setAccessExpiresHours("9-12");
        String result = ebook.getAccessExpiresHours();

        assertEquals(expected, result);
    }

    /**
     * Method that tests the setDevices and getDevices methods.
     */

    @Test
    void getDevices() {
        ArrayList<Computer> testComputers = new ArrayList<>();
        testComputers.add(computer);
        ebook.setDevices(testComputers);
        Computer result = ebook.getDevices().get(0);

        assertEquals(computer, result);
    }

    /**
     * Method that tests the setCurrentUsers and getCurrentUsers methods.
     */

    @Test
    void setCurrentUsers() {
        ebook.setCurrentUsers(5);
        int result = ebook.getCurrentUsers();
        assertEquals(5, result);
        ebook.removeCurrentReader();
        int secondResult = ebook.getCurrentUsers();
        assertEquals(4, secondResult);
    }

    /**
     * Method that tests the addDevice and removeDevice methods.
     */

    @Test
    void addDevice() {
        ebook.addDevice(computer);
        Computer result = ebook.getDevices().get(0);

        assertEquals(computer, result);
        ebook.removeDevice(computer);
        int secondResult = ebook.getDevices().size();

        assertEquals(0, secondResult);
    }


}