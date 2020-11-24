package LibraryApp;

import java.util.ArrayList;

public abstract class User {
    private String name;
    protected String userID;
    protected boolean isAdmin;
    protected boolean isMember;
    protected boolean isStaff;
    private double libraryFees;
    protected Library library;
    private ArrayList<LibraryItem> booksOnLoan;
    private ArrayList<EBook> currentEBooks;
    private ArrayList<LibraryItem> requestedBooks;
    private ArrayList<LibraryItem> bookHistory;
    protected int loanPeriod;
    protected int maxBooksAllowed;
    protected String password;
    private ArrayList <String> notifications;


    public User(String name, Library library, String password) {
        this.name = name;
        this.userID = "";
        this.isAdmin = false;
        this.isMember = false;
        this.isStaff = false;
        this.libraryFees = 0.0;
        this.library = library;
        this.booksOnLoan = new ArrayList<>();
        this.currentEBooks = null;
        this.requestedBooks = new ArrayList<>();
        this.bookHistory = new ArrayList<>();
        this.loanPeriod = 42;
        this.maxBooksAllowed = 15;
        this.password = password;
        this.notifications = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getUserID() {
        return userID;
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

    public Library getLibrary() {
        return library;
    }

    public ArrayList<LibraryItem> getBooksOnLoan() {
        return booksOnLoan;
    }

    public double getLibraryFees() {
        return libraryFees;
    }

    public ArrayList<EBook> getCurrentEBooks() {
        return currentEBooks;
    }

    public ArrayList<LibraryItem> getRequestedBooks() {
        return requestedBooks;
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

    public ArrayList<String> getNotifications() {
        return notifications;
    }

    public void accessEBook(EBook eBook){ //###needs logic added
        if (eBook.getCurrentReaders() != eBook.getMaximumConcurrentUsers())
        eBook.setAccessExpires();
        eBook.changeCurrentReaders(1);
        eBook.incrementTimesRead();
    }
    public void endAccessEBook(EBook eBook) {
        if (this.currentEBooks.contains(eBook)) {
            this.currentEBooks.remove(eBook);
        }
    }

//    public void reportDamage(){}
//    public void commentBook(){}?
//    public void requestBook(){}
//    public void requestBookAddedtoLibrary(){}
//    public void checkPopularity(){}
//    public void payLibraryFees(double amount){}
//    @overload public void pay(all)LibraryFees(){}
//    public void signIntoComputer(){}
//    public void signOutofComputer() {}
//    public void changePassword() {}
}
