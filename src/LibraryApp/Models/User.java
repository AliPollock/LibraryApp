package LibraryApp.Models;
import java.util.ArrayList;

/**
 * Abstract class that generally represents a user of the Library. Stores the information and carries out the operations that
 * would be stored or implicit in a user of the Library.
 */

public class User {
    protected int _userId;
    private String name;
    protected boolean isAdmin;
    protected boolean isMember;
    protected boolean isStaff;
    private double libraryFees;
    private ArrayList<PhysicalBook> booksOnLoan;
    private ArrayList<EBook> currentEBooks;
    private ArrayList<LibraryItem> bookHistory;
    protected int loanPeriod;
    protected int maxBooksAllowed;
    protected String password;
    private ArrayList <String> notifications;


    /**
     * Class constructor that a generates a unique object ID and assigns default values to fields which are not provided.
     * Used to create entirely new user
     * @param name The name of the user.
     * @param password The password for the user as a string.
     */

    public User(String name, String password) {
        this._userId = (int) (Math.random()*1000*(Math.random()*1000*name.length()));
        this.name = name;
        this.isAdmin = false;
        this.isMember = false;
        this.isStaff = false;
        this.libraryFees = 0.0;
        this.booksOnLoan = new ArrayList<>();
        this.currentEBooks = null;
        this.bookHistory = new ArrayList<>();
        this.loanPeriod = 42;
        this.maxBooksAllowed = 5;
        this.password = password;
        this.notifications = new ArrayList<>();
    }

    /**
     * Class constructor takes unique object ID of user pre-existing in the database and assigns default values to fields which are not provided.
     * @param _userId The unique user ID.
     * @param name The name of the user.
     * @param password The password for the user as a string.
     */


    public User( int _userId, String name, String password) { //instantiating user that exists in the database
        this._userId = _userId;
        this.name = name;
        this.isAdmin = false;
        this.isMember = false;
        this.isStaff = false;
        this.libraryFees = 0.0;
        this.booksOnLoan = new ArrayList<>();
        this.currentEBooks = null;
        this.bookHistory = new ArrayList<>();
        this.loanPeriod = 42;
        this.maxBooksAllowed = 5;
        this.password = password;
        this.notifications = new ArrayList<>();
    }

    /**
     * Method that generates String representation of the object.
     * @return The string representation.
     */

    @Override
    public String toString() {
        return "User{" +
                "_userId=" + _userId +
                ", name='" + name + '\'' +
                ", isAdmin=" + isAdmin +
                ", isMember=" + isMember +
                ", isStaff=" + isStaff +
                ", libraryFees=" + libraryFees +
                ", booksOnLoan=" + booksOnLoan +
                ", currentEBooks=" + currentEBooks +
                ", bookHistory=" + bookHistory +
                ", loanPeriod=" + loanPeriod +
                ", maxBooksAllowed=" + maxBooksAllowed +
                ", password='" + password + '\'' +
                ", notifications=" + notifications +
                '}';
    }



    //Setters

    public String getName() {
        return name;
    }

    public int getUserID() {
        return _userId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isMember() {
        return isMember;
    }

    public boolean isStaff() {
        return isStaff;
    }

    public int get_userId() {
        return _userId;
    }

    public double getLibraryFees() {
        return libraryFees;
    }

    public ArrayList<EBook> getCurrentEBooks() {
        return currentEBooks;
    }


    public ArrayList<LibraryItem> getBookHistory() {
        return bookHistory;
    }

    public int getLoanPeriod() {
        return loanPeriod;
    }

    public int getMaxBooksAllowed() {
        return maxBooksAllowed;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<PhysicalBook> getBooksOnLoan() {
        return booksOnLoan;
    }

    public ArrayList<String> getNotifications() {
        return notifications;
    }

    //setters

    public void set_userId(int _userId) {
        this._userId = _userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public void setStaff(boolean staff) {
        isStaff = staff;
    }

    public void setLibraryFees(double libraryFees) {
        this.libraryFees += libraryFees;
    }

    public void setBooksOnLoan(ArrayList<PhysicalBook> booksOnLoan) {
        this.booksOnLoan = booksOnLoan;
    }

    public void setCurrentEBooks(ArrayList<EBook> currentEBooks) {
        this.currentEBooks = currentEBooks;
    }

    public void setBookHistory(ArrayList<LibraryItem> bookHistory) {
        this.bookHistory = bookHistory;
    }

    public void setLoanPeriod(int loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    public void setMaxBooksAllowed(int maxBooksAllowed) {
        this.maxBooksAllowed = maxBooksAllowed;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNotifications(String notification) {
        this.notifications.add(notification);
    }

    /**
     * Method that prints all the notifications associated with the user.
     */

    public void printNotifications() {
        String printString = "";
        for(String notification: notifications) {
            printString += notification + ", ";
        }
        System.out.println(printString);
    }

    /**
     * Method that adds a book to the booksOnLoan ArrayList&lt;PhysicalBook&gt;.
     * @param book The book being added.
     */

    public void addBook(PhysicalBook book){
        this.booksOnLoan.add(book);
        this.bookHistory.add(book);
    }

    /**
     * Method that adds an Ebook to the currentEbooks ArrayList&lt;EBook&gt;.
     * @param book The book being added.
     */

    public void addBook(EBook book){
        this.currentEBooks.add(book);
        this.bookHistory.add(book);
    }

    /**
     * Method that returns a book to the Library, removing the book from the currentEbooks ArrayList&lt;EBook&gt;.
     * @param book The book being returned.
     */

    public void returnBook(EBook book){
        this.currentEBooks.remove(book);
    }

    /**
     * Method that returns a book to the Library, removing the book from the booksOnLoan ArrayList&lt;PhysicalBook&gt;.
     * @param book The book being returned.
     */

    public void returnBook(PhysicalBook book){
        this.booksOnLoan.remove(book);
    }

    /**
     * Method that prints a coma separated list of the current books on loan and the current Ebooks being viewed.
     */

    public void printCurrentBooks(){
        String printString = ";";
        for(PhysicalBook pBook: booksOnLoan) {
            printString += "Physical Book: " + pBook + ", ";
        }
        for(EBook eBook: currentEBooks) {
            printString += "EBook: " + eBook + ", ";
        }
        System.out.println(printString);
    }

    /**
     * Method that returns the int number of current Physical Books that the user has on loan.
     * @return The int number of books.
     */

    public int numCurrentBooks() {
        int noBooks = 0;
        for (PhysicalBook pBook : booksOnLoan) {
            noBooks += 1;
        }
        return noBooks;
    }
}
