package LibraryApp.testing;

import LibraryApp.Models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class which tests the functionality of the Library class.
 */

class LibraryTest {

    private Author author;
    private Author author2;
    private PhysicalBook book;
    private EBook ebook;
    private Library library;
    private Student student;
    private Admin admin;

    /**
     * Method which sets default values for Author and Book objects
     */

    public void setDefaults() {
        ArrayList<LibraryItem> catalogue = new ArrayList<>();
        ArrayList<EBook> eBookCatalogue = new ArrayList<>();
        ArrayList<PhysicalBook> physicalBookCatalogue = new ArrayList<>();
        ArrayList<Admin> adminList = new ArrayList<>();
        ArrayList<User> userList = new ArrayList<>();
        ArrayList<Author> authorList = new ArrayList<>();
        library.set_id(6789);
        library.setName("Strathclyde");
        library.setCatalogue(catalogue);
        library.setPhysicalCatalogue(physicalBookCatalogue);
        library.setEBookCatalogue(eBookCatalogue);
        library.setAdmins(adminList);
        library.setUsers(userList);
        library.setAuthors(authorList);
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
        library = new Library("Strathclyde", "8-11", "Glasgow");
        student = new Student("Ali", "password");
        admin = new Admin("teacher", "teach");

        setDefaults();
    }

    /**
     * Method which is called after each test case.
     */

    @AfterEach
    void tearDown() {
        System.out.println("After Each");
        setDefaults();
    }

    /**
     * Method which tests the toString method.
     */

    @Test
    void testToString() {
        String expected = "Library{_id=6789, name='Strathclyde', catalogue=[], eBookCatalogue=[], physicalCatalogue=[], admins=[], users=[], openHours='8-11', location='Glasgow', authors=[]}";
        String result = library.toString();

        assertEquals(expected, result);
    }

    /**
     * Method which tests the get_id method.
     */

    @Test
    void get_id() {
        int result = library.get_id();
        int expected = 6789;

        assertEquals(expected, result);
    }

    /**
     * Method which tests the getName method.
     */

    @Test
    void getName() {
        String expected = "Strathclyde";
        String result = library.getName();

        assertEquals(expected, result);
    }

    /**
     * Method which tests the getCataloge method.
     */

    @Test
    void getCatalogue() {
        ArrayList<LibraryItem> testCatalogue = new ArrayList<>();
        assertEquals(testCatalogue, library.getCatalogue());
    }

    /**
     * Method which tests the getPhysicalCatalogue method.
     */

    @Test
    void getPhysicalCatalogue() {
        ArrayList<PhysicalBook> testPhysicalBookCatalogue = new ArrayList<>();
        assertEquals(testPhysicalBookCatalogue, library.getCatalogue());
    }

    /**
     * Method which tests the getEBookCatalogue method.
     */

    @Test
    void getEBookCatalogue() {
        ArrayList<EBook> testEBookCatalogue = new ArrayList<>();
        assertEquals(testEBookCatalogue, library.getCatalogue());
    }

    /**
     * Method which tests the getAdmins method.
     */

    @Test
    void getAdmins() {
        ArrayList<Admin> testAdminList = new ArrayList<>();
        assertEquals(testAdminList, library.getAdmins());
    }

    /**
     * Method which tests the getUsers method.
     */

    @Test
    void getUsers() {
        ArrayList<User> testUserList = new ArrayList<>();
        assertEquals(testUserList, library.getUsers());
    }

    /**
     * Method which tests the getOpenHours method.
     */

    @Test
    void getOpenHours() {
        String expected = "8-11";
        String result = library.getOpenHours();

        assertEquals(expected,result);
    }

    /**
     * Method which tests the getLocation method.
     */

    @Test
    void getLocation() {
        String expected = "Glasgow";
        String result = library.getLocation();
        assertEquals(expected, result);
    }

    /**
     * Method which tests the getAuthors method.
     */

    @Test
    void getAuthors() {
        ArrayList<Author>testAuthorList = new ArrayList<>();
        assertEquals(testAuthorList, library.getAuthors());
    }

    /**
     * Method which tests the set_id and get_id methods.
     */

    @Test
    void set_id() {
        library.set_id(2468);
        assertEquals(2468, library.get_id());
    }

    /**
     * Method which tests the setName method.
     */

    @Test
    void setName() {
        library.setName("Andersonian");
        assertEquals("Andersonian", library.getName());
    }

    /**
     * Method which tests the setCatalogue method.
     */

    @Test
    void setCatalogue() {
        ArrayList<LibraryItem> testCatalogue = new ArrayList<>();
        testCatalogue.add(book);
        library.setCatalogue(testCatalogue);

        assertTrue(library.getCatalogue().contains(book));
    }

    /**
     * Method which tests the setEBookCatalogue method.
     */

    @Test
    void setEBookCatalogue() {
        ArrayList<EBook> testEBookCatalogue = new ArrayList<>();
        testEBookCatalogue.add(ebook);
        library.setEBookCatalogue(testEBookCatalogue);

        assertTrue(library.getEBookCatalogue().contains(ebook));
    }

    /**
     * Method which tests the setPhysicalCatalogue method.
     */

    @Test
    void setPhysicalCatalogue() {
        ArrayList<PhysicalBook> testPBookCatalogue = new ArrayList<>();
        testPBookCatalogue.add(book);
        library.setPhysicalCatalogue(testPBookCatalogue);

        assertTrue(library.getPhysicalCatalogue().contains(book));
    }

    /**
     * Method which tests the setAdmins method.
     */

    @Test
    void setAdmins() {
        ArrayList<Admin> testAdminList = new ArrayList<>();
        testAdminList.add(admin);
        library.setAdmins(testAdminList);

        assertTrue(library.getAdmins().contains(admin));
    }

    /**
     * Method which tests the setUsers method.
     */

    @Test
    void setUsers() {
        ArrayList<User> testUserList = new ArrayList<>();
        testUserList.add(student);
        testUserList.add(admin);
        library.setUsers(testUserList);

        assertTrue(library.getUsers().contains(student));
        assertTrue(library.getUsers().contains(admin));
    }

    /**
     * Method which tests the setOpenHours method.
     */

    @Test
    void setOpenHours() {
        library.setOpenHours("9-12");
        assertEquals("9-12", library.getOpenHours());
    }

    /**
     * Method which tests the setLocation method.
     */

    @Test
    void setLocation() {
        library.setLocation("City Centre");
        assertEquals("City Centre", library.getLocation());
    }

    /**
     * Method which tests the setAuthors method.
     */

    @Test
    void setAuthors() {
        ArrayList<Author> testAuthorList = new ArrayList<>();
        testAuthorList.add(author);
        library.setAuthors(testAuthorList);

        assertTrue(library.getAuthors().contains(author));
    }

    /**
     * Method which tests the searchForItem and addPhysicalBook methods.
     */

    @Test
    void searchForItem() {
        library.addPhysicalBook(book);
        LibraryItem result = library.searchForItem(book);

        assertEquals(book, result);
    }

    /**
     * Method which tests the booleanSearchForItem method.
     */

    @Test
    void booleanSearchForItem() {
        library.addPhysicalBook(book);
        boolean result = library.booleanSearchForItem(book);
        assertTrue(result);
    }

    /**
     * Method which tests the booleanSearchForItemName method.
     */

    @Test
    void booleanSearchForItemName() {
        library.addPhysicalBook(book);
        boolean result = library.booleanSearchForItemName("Bluej");
        assertTrue(result);
    }

    /**
     * Method which tests the booleanSearchForAuthor method.
     */

    @Test
    void booleanSearchForAuthor() {
        library.addPhysicalBook(book);
        boolean result = library.booleanSearchForAuthor("Michael Kolling");
        assertTrue(result);
    }

    /**
     * Method which tests the searchForAuthor method.
     */

    @Test
    void searchForAuthor() {
        library.addPhysicalBook(book);
        ArrayList<LibraryItem> result = library.searchForAuthor("Michael Kolling");
        assertTrue(result.contains(book));
    }

    /**
     * Method which tests the searchISBN and addEBook methods.
     */

    @Test
    void searchISBN() {
        library.addEBook(ebook);
        ArrayList<LibraryItem> result = library.searchISBN("1002");
        assertTrue(result.contains(ebook));
    }

    /**
     * Method which tests the booleanSearchForISBN method.
     */

    @Test
    void booleanSearchForISBN() {
        library.addPhysicalBook(book);
        boolean result = library.booleanSearchForISBN("12345");
        assertTrue(result);
    }

    /**
     * Method which tests the removeEbook method.
     */

    @Test
    void removeEbook() {
        library.addEBook(ebook);
        library.removeEbook(ebook);
        assertFalse(library.getEBookCatalogue().contains(ebook));
        assertFalse(library.getCatalogue().contains(ebook));
    }

    /**
     * Method which tests the removePhysicalBook method.
     */

    @Test
    void removePhysicalBook() {
        library.addPhysicalBook(book);
        library.removePhysicalBook(book);
        assertFalse(library.getPhysicalCatalogue().contains(book));
        assertFalse(library.getCatalogue().contains(book));
    }

    /**
     * Method which tests the removeResourceAtIndex, removePhysicalBook(int) and removeEBook(int) methods.
     */

    @Test
    void removeResourceAtIndex() { //testing removeResourceAtIndex and removePhysicalBook(int) and removeEbook(int)
        library.addPhysicalBook(book);
        library.addEBook(ebook);
        library.removeResourceAtIndex(1);
        library.removeResourceAtIndex(0);
        assertFalse(library.getCatalogue().contains(book));
        assertFalse(library.getPhysicalCatalogue().contains(book));
        assertFalse(library.getEBookCatalogue().contains(ebook));
    }

    /**
     * Method which tests the getDate method.
     */

    @Test
    void getDate() {
        String result = library.getDate();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String expected = formatter.format(date);

        assertEquals(expected, result);
    }

    /**
     * Method which tests the editBookTitle method.
     */

    @Test
    void editBookTitle() {
        library.addPhysicalBook(book);
        library.editBookTitle(book, "New Title");
        String result = library.searchForItem(book).getName();

        assertEquals("New Title", result);
    }

    /**
     * Method which tests the printAvailableBooks method.
     */

    @Test
    void printAvailableBooks() {
        library.addPhysicalBook(book);
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        library.printAvailableBooks();
        String expected = book.toString();

        assertEquals(expected, outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    /**
     * Method which tests the noItemsInCatalogue method.
     */

    @Test
    void noItemsInCatalogue() {
        library.addPhysicalBook(book);
        library.addEBook(ebook);

        assertEquals(2, library.noItemsInCatalogue());
    }

    /**
     * Method which tests the addUser, checkOutBook and returnBook methods.
     */

    @Test
    void checkOutBook() {
        library.addPhysicalBook(book);
        library.addUser(student);
        library.checkOutBook(student, book);
        assertTrue(book.isOnLoan());
        library.returnBook(book, false, "");
        assertFalse(book.isOnLoan());
    }

    /**
     * Method which tests the viewEBook and stopViewingEBook methods.
     */

    @Test
    void viewEBook() {
        library.addEBook(ebook);
        library.addUser(student);
        library.viewEBook(ebook, student);
        assertEquals(1, ebook.getCurrentUsers());
        assertTrue(student.getCurrentEBooks().contains(ebook));
        assertEquals(1, ebook.getTimesRead());
        library.stopViewingEBook(ebook, student);
        assertEquals(0, ebook.getCurrentUsers());
        assertFalse(student.getCurrentEBooks().contains(ebook));
    }

    /**
     * Method which tests the sendNotifications method.
     */

    @Test
    void sendNotifications() {
        library.addUser(student);
        library.addPhysicalBook(book);
        library.checkOutBook(student, book);
        library.sendNotifications("Test Notification");
        String result = student.getNotifications().get(0);

        assertEquals("Test Notification", result);
    }

    /**
     * Method which tests the printPhysicalBooks method.
     */

    @Test
    void printPhysicalBooks() {
        library.addPhysicalBook(book);
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        library.printPhysicalBooks();
        String expected = book.toString();

        assertEquals(expected, outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    /**
     * Method which tests the printEBooks method.
     */

    @Test
    void printEBooks() {
        library.addEBook(ebook);
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        library.printEBooks();
        String expected = ebook.toString();

        assertEquals(expected, outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }
}