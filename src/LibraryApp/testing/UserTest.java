package LibraryApp.testing;

import LibraryApp.Models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class which tests the functionality of the Physical book class and the User class it inherits from.
 */

class UserTest {

    private Student student;
    private PhysicalBook book;
    private EBook ebook;
    private Author author;

    /**
     * Method which sets default values for Author and Book objects
     */

    public void setDefaults() {
        ArrayList<LibraryItem> bookHistory = new ArrayList<>();
        ArrayList<PhysicalBook> booksOnLoan = new ArrayList<>();
        ArrayList<EBook> currentEBooks = new ArrayList<>();
        ArrayList<String> notifications = new ArrayList<>();
        student.clearNotifications();
        student.set_userId(1001);
        student.setPassword("password");
        student.setName("testUsername");
        student.setLibraryFees(0.0);
        student.setAdmin(false);
        student.setBookHistory(bookHistory);
        student.setBooksOnLoan(booksOnLoan);
        student.setCurrentEBooks(currentEBooks);
        student.setLoanPeriod(42);
        student.setMaxBooksAllowed(5);
        student.setMember(true);
        student.setStaff(false);
        book.set_id(1002);
        ebook.set_id(1003);

    }

    /**
     * Method which is called before each test case.
     */

    @BeforeEach
    void setUp() {
        author = new Author("Michael Kolling");
        student = new Student("testUsername", "password");
        book = new PhysicalBook("Bluej", author, "2016", "Pearson", "12345");
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
        String expected = "User {_userId=1001, name='testUsername', isAdmin=false, isMember=true, isStaff=false, libraryFees=0.0, booksOnLoan=[], currentEBooks=[], bookHistory=[], loanPeriod=42, maxBooksAllowed=5, password='password', notifications=[]}";
        String result = student.toString();

        assertEquals(expected, result);
    }

    /**
     * Method which tests the get_userId and set_userId methods.
     */

    @Test
    void get_userId() {
        int expected = 1002;
        student.set_userId(1002);
        int result = student.get_userId();

        assertEquals(expected, result);
    }


    /**
     * Method which tests the setName and getName methods.
     */

    @Test
    void getName() {
        String expected = "Ali";
        student.setName("Ali");
        String result = student.getName();

        assertEquals(expected, result);
    }


    /**
     * Method which tests isAdmin and setAdmin methods.
     */

    @Test
    void isAdmin() {
        student.setAdmin(true);
        boolean result = student.isAdmin();
        assertTrue(result);
    }


    /**
     * Method which tests the isMember and setMember methods.
     */

    @Test
    void isMember() {
        student.setMember(true);
        boolean result = student.isMember();
        assertTrue(result);
    }


    /**
     * Method which tests the isStaff and setStaff methods.
     */

    @Test
    void isStaff() {
        student.setStaff(true);
        boolean result = student.isStaff();
        assertTrue(result);
    }

    /**
     * Method which tests the setLibraryFees and getLibraryFees method.
     */

    @Test
    void getLibraryFees() {
        student.setLibraryFees(20.0);
        double expected = 20.0;
        double result = student.getLibraryFees();

        assertEquals(expected, result);
    }

    /**
     * Method which tests the getCurrentEBooks and setCurrentEBooks methods.
     */

    @Test
    void getCurrentEBooks() { //set and get currentEbook
        ArrayList<EBook> testEBooks = new ArrayList<>();
        testEBooks.add(ebook);
        student.setCurrentEBooks(testEBooks);
        EBook eBookResult = student.getCurrentEBooks().get(0);

        assertEquals(ebook, eBookResult);
    }

    /**
     * Method which tests the getBookHistory and setBookHistory methods.
     */

    @Test
    void getBookHistory() {//set and get bookHistory
        ArrayList<LibraryItem> testBookHistory = new ArrayList<>();
        testBookHistory.add(ebook);
        testBookHistory.add(book);
        student.setBookHistory(testBookHistory);

        PhysicalBook result = book;
        EBook secondResult = ebook;
        student.returnBook(ebook);
        assertEquals(book, student.getBookHistory().get(1));
        assertEquals(ebook, student.getBookHistory().get(0));
    }

    /**
     * Method which tests the setLoanPeriod and getLoanPeriod methods.
     */

    @Test
    void getLoanPeriod() {
        student.setLoanPeriod(50);
        int result = student.getLoanPeriod();

        assertEquals(50, result);
    }

    /**
     * Method which tests the setMaxBooksAllowed and getMaxBooksAllowed methods.
     */

    @Test
    void getMaxBooksAllowed() {
        student.setMaxBooksAllowed(10);
        int result = student.getMaxBooksAllowed();

        assertEquals(10, result);
    }

    /**
     * Method which tests the setPassword and getPassword methods.
     */

    @Test
    void getPassword() {
        String expected = "testPassword";
        student.setPassword("testPassword");
        String result = student.getPassword();

        assertEquals(expected, result);
    }

    /**
     * Method which tests the getBooksOnLoan and setBooksOnLoan methods.
     */

    @Test
    void getBooksOnLoan() {
        ArrayList<PhysicalBook> testBooksOnLoan = new ArrayList<>();
        testBooksOnLoan.add(book);
        student.setBooksOnLoan(testBooksOnLoan);
        PhysicalBook result = student.getBooksOnLoan().get(0);

        assertEquals(book, result);
    }

    /**
     * Method which tests the setNotifications and getNotifications methods.
     */

    @Test
    void getNotifications() { //set and get notifications
        String expected = "First Notification";
        student.setNotifications("First Notification");
        String result = student.getNotifications().get(0);

        assertEquals(expected, result);
    }

    /**
     * Method which tests the printNotifications method.
     */

    @Test
    void printNotifications() {
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        student.setNotifications("First Notification");
        student.printNotifications();
        String expected = student.getNotifications().get(0);

        assertEquals(expected, outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    /**
     * Method which tests the addBook(PhysicalBook) and returnBook(PhysicalBook) methods.
     */

    @Test
    void addPBook() {
        student.addBook(book);
        PhysicalBook result = student.getBooksOnLoan().get(0);
        assertEquals(book, result);

        student.returnBook(book);
        int secondResult = student.getBooksOnLoan().size();
        assertEquals(0, secondResult);
    }


    /**
     * Method which tests the addBook(EBook) and returnBook(EBook)method.
     */

    @Test
    void addEBook() {
        student.addBook(ebook);
        EBook result = student.getCurrentEBooks().get(0);
        assertEquals(ebook, result);

        student.returnBook(ebook);
        int secondResult = student.getBooksOnLoan().size();
        assertEquals(0, secondResult);
    }

    /**
     * Method which test the printCurrentBooks method.
     */

    @Test
    void printCurrentBooks() {
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        student.addBook(book);
        student.addBook(ebook);
        student.printCurrentBooks();
        String expected = "PhysicalBook{_id=1002, name='Bluej', author=Michael Kolling, publicationDate='2016', publisher='Pearson', " +
                "topics=[], timesRead=0, bio='', iSBN='12345', isOnLoan=false, isOverdue=false, overDueCharge=0.0, dueDate='null', damages=''," +
                " EBook {_id=1003, name='Bluej', author=(Author: Michael Kolling), publicationDate='2016', publisher='Pearson', topics=[]," +
                " timesRead=0, bio='', iSBN='12345', currentUsers=0, accessExpiresHours='null', devices=[]}";


        assertEquals(expected, outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    /**
     * Method which test the numCurrentBooks method.
     */

    @Test
    void numCurrentBooks() {
        int expected = 1;
        student.addBook(book);
        int result = student.numCurrentBooks();

        assertEquals(expected, result);
    }

    /**
     * Method which test the clearNotifications method.
     */

    @Test
    void clearNotifications(){

        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        student.setNotifications("First Notification");
        student.clearNotifications();
        student.printNotifications();
        String expected = "";

        assertEquals(expected, outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }
}