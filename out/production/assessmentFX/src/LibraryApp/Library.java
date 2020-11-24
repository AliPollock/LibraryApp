package LibraryApp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Library {
    private String name;
    private ArrayList<LibraryItem> catalogue;
    private ArrayList<EBook> eBookCatalogue;
    private ArrayList<PhysicalBook> physicalCatalogue;
    private ArrayList<Admin> admins;
    private ArrayList<User> users;
    private boolean isOpen; //should remove this from constructors once I have time working
    private String openHours;
    private String location;
    private ArrayList<Author> authors;

    public Library(String name, boolean isOpen, String openHours, String location) {
        this.name = name;
        this.isOpen = isOpen;
        this.openHours = openHours;
        this.location = location;
        this.catalogue = new ArrayList<>();
        this.users = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.eBookCatalogue = new ArrayList<>();
        this.physicalCatalogue = new ArrayList<>();
        this.authors = new ArrayList<>();
    }

    public Library(String name) {
        this.name = name;
        this.catalogue = new ArrayList<>();
        this.users = new ArrayList<>();
        this.admins = new ArrayList<>();
    }


    // getters

    public String getName() {
        return this.name;
    }

    public ArrayList<LibraryItem> getCatalogue() {
        return this.catalogue;
    }

    public ArrayList<Admin> getAdmins() {
        return this.admins;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public String getOpenHours() {
        return this.openHours;
    }

    public String getLocation() {
        return this.location;
    }

    public ArrayList<EBook> getEBookCatalogue() {
        return this.eBookCatalogue;
    }

    public ArrayList<PhysicalBook> getPhysicalCatalogue() {
        return this.physicalCatalogue;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    // setters

    public void setName(String name) {
        this.name = name;
    }

    public void setCatalogue(ArrayList<LibraryItem> catalogue) {
        this.catalogue = catalogue;
    }

    public void setAdmins(ArrayList<Admin> admins) {
        this.admins = admins;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void setOpen(boolean open) {
        this.isOpen = open;
    }

    public void setOpenHours(String openHours) {
        this.openHours = openHours;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // search methods

    public ArrayList searchForItem(String name){
        ArrayList<LibraryItem> booksFound = new ArrayList<>();
        for (LibraryItem item: this.getCatalogue()) {
            if (item.getName() == name) {
                booksFound.add(item);
            }
        } return booksFound;
    }

    public ArrayList searchForAuthor(String authorName){
        ArrayList<LibraryItem> booksFound = new ArrayList<>();
        for (LibraryItem item: this.getCatalogue()) {
            if (item.getAuthor().getName() == authorName) {
                booksFound.add(item);
            }
        } return booksFound;
    }

    public LibraryItem searchISBN(String iSBN) {
        for (LibraryItem item: this.getCatalogue()) {
            if (item.getISBN() == iSBN) {
                return item;
            }
        } return null;
    }

    // add and remove book methods

    public void addEBook(EBook ebook) {
        this.getCatalogue().add(ebook);
        this.getEBookCatalogue().add(ebook);
    }

    public void removeEbook(EBook ebook) {
        this.getCatalogue().remove(ebook);
        this.getEBookCatalogue().remove(ebook);
    }

    public void addPhysicalBook(PhysicalBook book) {
        this.getCatalogue().add(book);
        this.getPhysicalCatalogue().add(book);
    }

    public void removePhysicalBook(PhysicalBook book) {
        this.getCatalogue().remove(book);
        this.getPhysicalCatalogue().remove(book);
    }

    public void changeTitle(LibraryItem item, String name) {
        for (LibraryItem searchItem: this.getCatalogue()) {
            if (searchItem == item) {
                item.setName(name);
            }
        }
    }

    // other methods

    public static String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }


    public int checkLatestEdition(EBook searchItem) { //### definitely unsure about this one
        EBook placeholderEBook = searchItem;
        for (EBook ebook: this.getEBookCatalogue()) {
            if (ebook.getName() == searchItem.getName()) {
                if (ebook.getEdition() > searchItem.getEdition()) {
                    placeholderEBook = ebook;
                }
            }
        } return placeholderEBook.getEdition();
    }

    public int checkLatestEdition(PhysicalBook searchItem) { //### definitely unsure about this one
        PhysicalBook placeholderBook = searchItem;
        for (PhysicalBook book: this.getPhysicalCatalogue()) {
            if (book.getName() == searchItem.getName()) {
                if (book.getEdition() > searchItem.getEdition()) {
                    placeholderBook = book;
                }
            }
        } return placeholderBook.getEdition();
    }

    public LibraryItem getNewestEdition(EBook searchItem) { //### definitely unsure about this one
        EBook placeholderEBook = searchItem;
        for (EBook ebook: this.getEBookCatalogue()) {
            if (ebook.getName() == searchItem.getName()) {
                if (ebook.getEdition() > searchItem.getEdition()) {
                    placeholderEBook = ebook;
                }
            }
        } return placeholderEBook;
    }

    public LibraryItem getNewestEdition(PhysicalBook searchItem) { //### definitely unsure about this one
        PhysicalBook placeholderBook = searchItem;
        for (PhysicalBook book: this.getPhysicalCatalogue()) {
            if (book.getName() == searchItem.getName()) {
                if (book.getEdition() > searchItem.getEdition()) {
                    placeholderBook = book;
                }
            }
        } return placeholderBook;
    }



    //ISBN Methods and new field

    //    public ArrayList browse() {}
    //    public ArrayList searchKeywords(){}
    // public void displayCatalogue() {}

    // public void refreshBookOverdueStatus() {
    // for (PhysicalBook book: this.getPhysicalCatalogue()) {
    //      if (currenttime >= book.getDueDate()) {
    //          book.changeOverdueStatus();
    //      }
    // }
}
