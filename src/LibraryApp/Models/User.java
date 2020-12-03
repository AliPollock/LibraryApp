package LibraryApp.Models;
import java.util.ArrayList;

/**
 * Abstract class that generally represents a user of the Library. Stores the information and carries out the operations that
 * would be stored or implicit in a user of the Library.
 */

public abstract class User {
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
        this.currentEBooks = new ArrayList<>();
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
        this.currentEBooks = new ArrayList<>();
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
        return "User {" +
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

    /**
     * Method that gets ID.
     * @return int ID.
     */

    public int get_userId() {
        return _userId;
    }

    /**
     * Method that gets Name.
     * @return String Name.
     */

    public String getName() {
        return name;
    }

    /**
     * Method that gets ID.
     * @return int ID.
     */


    /**
     * Method that gets if the user is an Admin.
     * @return boolean true if user is an Admin.
     */

    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Method that gets if the user is a member.
     * @return boolean true if user is a member.
     */

    public boolean isMember() {
        return isMember;
    }

    /**
     * Method that gets if the user is Staff.
     * @return boolean true if user is Staff.
     */

    public boolean isStaff() {
        return isStaff;
    }


    /**
     * Method that gets the users library fees.
     * @return double library fees.
     */

    public double getLibraryFees() {
        return libraryFees;
    }

    /**
     * Method that gets the ArrayList&lt;EBook&gt; current books the user is viewing.
     * @return ArrayList&lt;EBook&gt;.
     */

    public ArrayList<EBook> getCurrentEBooks() {
        return currentEBooks;
    }

    /**
     * Method that gets the ArrayList&lt;LibraryItem&gt; the user has viewed or loaned.
     * @return ArrayList&lt;LibraryItem&gt;.
     */

    public ArrayList<LibraryItem> getBookHistory() {
        return bookHistory;
    }

    /**
     * Method that gets the loan period the user is allowed to have a book for.
     * @return int number of days a user can loan a book for.
     */

    public int getLoanPeriod() {
        return loanPeriod;
    }

    /**
     * Method that gets the maximum number of books a user is allowed to loan at a time.
     * @return in max number of books.
     */

    public int getMaxBooksAllowed() {
        return maxBooksAllowed;
    }

    /**
     * Method that gets the password.
     * @return String password.
     */

    public String getPassword() {
        return password;
    }

    /**
     * Method that gets ArrayList&lt;PhysicalBook&gt; list of books the user has on loan.
     * @return ArrayList&lt;PhysicalBook&gt;.
     */

    public ArrayList<PhysicalBook> getBooksOnLoan() {
        return booksOnLoan;
    }

    /**
     * Method that gets the ArrayList&lt;String&gt; list of notifications the user has been sent.
     * @return ArrayList&lt;String&gt;.
     */

    public ArrayList<String> getNotifications() {
        return notifications;
    }

    /**
     * Method that sets the user ID.
     * @param _userId int ID.
     */

    public void set_userId(int _userId) {
        this._userId = _userId;
    }

    /**
     * Method that sets the username.
     * @param name String username.
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method that sets if the user is an Admin.
     * @param admin boolean true if user is an Admin.
     */

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    /**
     * Method that sets if the user is a member.
     * @param member boolean true of user is a member.
     */

    public void setMember(boolean member) {
        isMember = member;
    }

    /**
     * Method that sets if user is staff.
     * @param staff boolean true if user is staff.
     */

    public void setStaff(boolean staff) {
        isStaff = staff;
    }

    /**
     * Method that sets library fees.
     * @param libraryFees double library fees associated with user.
     */

    public void setLibraryFees(double libraryFees) {
        this.libraryFees += libraryFees;
    }

    /**
     * Method that sets the ArrayList&lt;PhysicalBook&gt; of books the user currently has on loan.
     * @param booksOnLoan ArrayList&lt;PhysicalBook&gt;.
     */

    public void setBooksOnLoan(ArrayList<PhysicalBook> booksOnLoan) {
        this.booksOnLoan = booksOnLoan;
    }

    /**
     * Method that sets ArrayList&lt;EBook&gt; of EBooks the user is currently viewing.
     * @param currentEBooks ArrayList&lt;EBook&gt;.
     */

    public void setCurrentEBooks(ArrayList<EBook> currentEBooks) {
        this.currentEBooks = currentEBooks;
    }

    /**
     * Method that sets the ArrayList&lt;LibraryItems&gt; list of library items the user has viewed or loaned.
     * @param bookHistory ArrayList&lt;LibraryItems&gt;.
     */

    public void setBookHistory(ArrayList<LibraryItem> bookHistory) {
        this.bookHistory = bookHistory;
    }

    /**
     * Method that sets the period the user is allowed to loan a book for.
     * @param loanPeriod int days a user can loan a book for.
     */

    public void setLoanPeriod(int loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    /**
     * Method that sets the maximum number of books a user is allowed to have on loan.
     * @param maxBooksAllowed int maximum number of books.
     */

    public void setMaxBooksAllowed(int maxBooksAllowed) {
        this.maxBooksAllowed = maxBooksAllowed;
    }

    /**
     * Method that sets the user's password.
     * @param password String password.
     */

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method that sets the ArrayList&lt;String&gt; list of notifications sent to the user.
     * @param notification ArrayList&lt;String&gt;
     */

    public void setNotifications(String notification) {
        this.notifications.add(notification);
    }

    /**
     * Method that prints all the notifications associated with the user.
     */

    public void printNotifications() {
        String buildString = "";
        for(String notification: notifications) {
            buildString += notification + ", ";
        }
        String outputString = "";
        if(buildString != "") {
            outputString = buildString.substring(0, buildString.length() - 2);
        }
        System.out.println(outputString);
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
        String buildString = "";
        for(PhysicalBook pBook: booksOnLoan) {
            buildString +=  pBook + ", ";
        }
        for(EBook eBook: currentEBooks) {
            buildString +=  eBook + ", ";
        }
        String printString = "";
        if(buildString != "") {
            printString = buildString.substring(0, buildString.length() - 2);
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

    /**
     * Method that clears the user notifications
     */

    public void clearNotifications(){
        this.notifications.clear();
    }

    /**
     * Method that prints details of object.
     */

    public void printDetails(){
        System.out.println(toString());
    }
}
