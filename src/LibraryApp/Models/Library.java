package LibraryApp.Models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/***
 * This is a class used to represent a Library as a whole.
 * It stores the data and performs the operations that a library would store and do.
 * */


public class Library {
    private int _id;
    private String name;
    private ArrayList<LibraryItem> catalogue;
    private ArrayList<EBook> eBookCatalogue;
    private ArrayList<PhysicalBook> physicalCatalogue;
    private ArrayList<Admin> admins;
    private ArrayList<User> users;
    private String openHours;
    private String location;
    private ArrayList<Author> authors;


    /***
     * This constructor for a library takes three parameters and automatically generates a unique id along with setting default values for the other fields.
     * @param name The name of the Library.
     * @param openHours The opening hours of the library as a String.
     * @param location The location of the library as a String.
     */
    public Library(String name, String openHours, String location) {
        this._id = (int) (Math.random()*1000*(Math.random()*1000*name.length()));
        this.name = name;
        this.openHours = openHours;
        this.location = location;
        this.catalogue = new ArrayList<>();
        this.users = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.eBookCatalogue = new ArrayList<>();
        this.physicalCatalogue = new ArrayList<>();
        this.authors = new ArrayList<>();
    }

    /**
     * The constructor for a library takes parameters and automatically generates a unique id along with setting default values for the other fields.
     * @param name The name of the Library.
     */
    public Library(String name) {
        this._id = (int) (Math.random()*1000*(Math.random()*1000*name.length()));
        this.name = name;
        this.openHours = null;
        this.location=null;
        this.catalogue = new ArrayList<>();
        this.users = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.eBookCatalogue = new ArrayList<>();
        this.physicalCatalogue = new ArrayList<>();
        this.authors = new ArrayList<>();
    }

    /**
     * toString generates a String representation of the Library object.
     * @return The String representation.
     */

    @Override
    public String toString() {
        return "Library{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", catalogue=" + catalogue +
                ", eBookCatalogue=" + eBookCatalogue +
                ", physicalCatalogue=" + physicalCatalogue +
                ", admins=" + admins +
                ", users=" + users +
                ", openHours='" + openHours + '\'' +
                ", location='" + location + '\'' +
                ", authors=" + authors +
                '}';
    }


    /**
     * Method that gets ID.
     * @return int ID.
     */

    public int get_id() {
        return _id;
    }

    /**
     * Method that gets Name.
     * @return String Name.
     */

    public String getName() {
        return this.name;
    }

    /**
     * Method that gets the ArrayList&lt;LibraryItem&gt; catalogue of Library items in the Library.
     * @return ArrayList&lt;LibraryItem&gt;.
     */

    public ArrayList<LibraryItem> getCatalogue() {
        return this.catalogue;
    }

    /**
     * Method that gets the ArrayList&lt;PhysicalBook&gt; catalogue of Physical Books in the Library.
     * @return  ArrayList&lt;PhysicalBook&gt;.
     */


    public ArrayList<PhysicalBook> getPhysicalCatalogue() {
        return this.physicalCatalogue;
    }

    /**
     * Method that gets the ArrayList&lt;EBook&gt;catalogue of EBooks in the library.
     * @return ArrayList&lt;EBook&gt;.
     */

    public ArrayList<EBook> getEBookCatalogue() {
        return eBookCatalogue;
    }

    /**
     * Method that gets ArrayList&lt;Admin&gt; list of Admins in the Library
     * @return ArrayList&lt;Admin&gt;.
     */

    public ArrayList<Admin> getAdmins() {
        return this.admins;
    }

    /**
     * Method that gets ArrayList&lt;User&gt; list of users in the Library.
     * @return ArrayList&lt;User&gt;.
     */

    public ArrayList<User> getUsers() {
        return this.users;
    }

    /**
     * Method that gets the opening hours of the library.
     * @return String opening hours.
     */

    public String getOpenHours() {
        return this.openHours;
    }

    /**
     * Method that gets the location of the library.
     * @return String location.
     */

    public String getLocation() {
        return this.location;
    }

    /**
     * Method that gets ArrayList&lt;Author&gt; list of Authors who have works in the Library.
     * @return ArrayList&lt;Author&gt;.
     */

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    /**
     * Method that sets ID
     * @param _id int ID
     */

    public void set_id(int _id) {
        this._id = _id;
    }

    /**
     * Method that sets Name.
     * @param name String Name.
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method that sets the ArrayList&lt;LibraryItem&gt; catalogue of Library items in the Library.
     * @param catalogue ArrayList&lt;LibraryItem&gt; catalogue
     */

    public void setCatalogue(ArrayList<LibraryItem> catalogue) {
        this.catalogue = catalogue;
    }

    /**
     * Method that sets the ArrayList&lt;EBook&gt; catalogue of EBooks in the Library.
     * @param eBookCatalogue ArrayList&lt;EBook&gt;.
     */

    public void setEBookCatalogue(ArrayList<EBook> eBookCatalogue) {
        this.eBookCatalogue = eBookCatalogue;
    }

    /**
     * Method that sets the ArrayList&lt;PhysicalBooks&gt; catalogue of Physical Books in the Library.
     * @param physicalCatalogue ArrayList&lt;PhysicalBooks&gt;
     */

    public void setPhysicalCatalogue(ArrayList<PhysicalBook> physicalCatalogue) {
        this.physicalCatalogue = physicalCatalogue;
    }

    /**
     * Method that sets the ArrayList&lt;Admin&gt; list of Admins in the Library.
     * @param admins ArrayList&lt;Admin&gt;.
     */

    public void setAdmins(ArrayList<Admin> admins) {
        this.admins = admins;
    }

    /**
     * Method that sets the ArrayList&lt;User&gt; list of Users in the Library.
     * @param users ArrayList&lt;User&gt;.
     */

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    /**
     * Method that sets opening hours of the Library.
     * @param openHours String opening hours.
     */

    public void setOpenHours(String openHours) {
        this.openHours = openHours;
    }

    /**
     * Method that sets location of the Library.
     * @param location String location.
     */

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Method that sets the ArrayList&lt;Author&gt; list of Authors with works in the Library.
     * @param authors ArrayList&lt;Author&gt;
     */

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    /**
     * Takes a Library Book Item and returns Item if it is present in the catalogue Arraylist.
     * @param book The book that is being searched for.
     * @return Library Item if it is found, otherwise returns null.
     */

    public LibraryItem searchForItem(LibraryItem book){
        if (booleanSearchForItem(book)) {
            return book;
        } else {
            return null;
        }
    }

    /**
     * Takes a Library Book Item and returns a boolean indicating whether the item is present in the catalogue ArrayList.
     * @param book The book that is being searched for.
     * @return boolean
     */

    public boolean booleanSearchForItem(LibraryItem book){
        boolean result = false;
        for (LibraryItem item: this.getCatalogue()) {
            if (item == book) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Takes a String and returns a boolean indicating whether an item of that name is present in the catalogue ArrayList.
     * @param bookName The name of the book that is being searched for.
     * @return boolean
     */

    public boolean booleanSearchForItemName(String bookName){
        boolean result = false;
        for (LibraryItem item: this.getCatalogue()) {
            if (item.getName().equals(bookName)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Takes a String and returns a boolean indicating whether an Author of that name is present in the catalogue ArrayList.
     * @param authorName The name of the author of the book that is being searched for.
     * @return boolean
     */

    public boolean booleanSearchForAuthor(String authorName){
        boolean result = false;
        for (LibraryItem item: this.getCatalogue()) {
            if (item.getAuthor().getName().equals(authorName)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     *  Takes String and returns an ArrayList populated with the Library Items which are written by the author.
     * @param authorName The name  of the author of the book that is being searched for.
     * @return ArrayList&lt;LibraryItem&gt;.
     */

    public ArrayList<LibraryItem> searchForAuthor(String authorName){
        ArrayList<LibraryItem> booksFound = new ArrayList<>();
        for (LibraryItem item: this.getCatalogue()) {
            if (item.getAuthor().getName().equals(authorName)) {
                booksFound.add(item);
            }
        }
        System.out.println(booksFound);
        System.out.println(booksFound.size());
        return booksFound;
    }

    /**
     * Takes a String and Returns an ArrayList of Library book Objects which correspond to the ISBN string provided.
     * @param iSBN The ISBN number of the book that is being searched for.
     * @return ArrayList&lt;LibraryItem&gt;.
     */

    public ArrayList<LibraryItem> searchISBN(String iSBN) {
        ArrayList<LibraryItem> books = new ArrayList<>();
        for (LibraryItem item: this.getCatalogue()) {
            if (item.getISBN().equals(iSBN)) {
                books.add(item);
            }
        } return books;
    }


    /**
     * Takes a String and Returns a boolean if an item exists that corresponds to the ISBN string provided.
     * @param iSBN  The ISBN number of the book that is being searched for.
     * @return boolean
     */

    public boolean booleanSearchForISBN(String iSBN){
        boolean result = false;
        ArrayList<LibraryItem> booksFound= new ArrayList<>();
        int PBookCount =0;
        int EBookCount =0;
        for (LibraryItem item: this.getCatalogue()) {
            if (item.getISBN().equals(iSBN)) {
                result = true;
                booksFound.add(item);

                for(EBook eItem: this.eBookCatalogue) {
                    if(eItem.getISBN().equals(iSBN)) {
                        EBookCount += 1;
                    }
                }

                for(PhysicalBook pItem: this.physicalCatalogue) {
                    if(pItem.getISBN().equals(iSBN)) {
                        PBookCount += 1;
                    }
                }
            }
        }
        System.out.println(booksFound);
        System.out.println("Number of EBooks found: " + EBookCount + ", Number of PBooks found: " + PBookCount);
        return result;
    }

    /**
     * Takes an Ebook object and adds to Ebook ArrayList and main catalogue if it is not present already.
     * Prints error if item is already present.
     * @param eBook The eBook that is being added.
     */

    public void addEBook(EBook eBook) {
        if(this.catalogue.contains(eBook)) {
            System.out.println("item already exists in library");
        } else {
            this.getCatalogue().add(eBook);
            this.getEBookCatalogue().add(eBook);
        }
    }

    /**
     * Takes an Ebook object and removes from Ebook ArrayList and main catalogue if it is present already. Prints error if
     * item is not present.
     * @param ebook The eBook that is being removed.
     */

    public void removeEbook(EBook ebook) {
        if (this.getCatalogue().contains(ebook)) {
            this.getCatalogue().remove(ebook);
            this.getEBookCatalogue().remove(ebook);
        } else {
            System.out.println("Ebook object not found in catalogue");
        }
    }

    /**
     * Takes an int ID and removes corresponding item from EBook catalogue.
     * @param _id int ID.
     */

    public void removeEBook(int _id) {
        ArrayList<EBook> books = new ArrayList<>();
        for(EBook book: eBookCatalogue) {
            if(book.get_id() == _id){
                books.add(book);
            }
        }
        try {
            this.eBookCatalogue.remove(books.get(0));
        } catch (IndexOutOfBoundsException e){}
    }

    /**
     * Takes a Physical Book item and adds to physicalBook ArrayList and main catalogue if it is not present already. Prints error if
     * item is already present.
     * @param book The Book that is being added.
     */

    public void addPhysicalBook(PhysicalBook book) {
        if(this.catalogue.contains(book)) {
            System.out.println("item already exists in library");
        } else {
        this.getCatalogue().add(book);
        this.getPhysicalCatalogue().add(book);
        }
    }

    /**
     *Takes a Physical Book object and removes from physicalBook ArrayList and main catalogue if it is present already. Prints error if
     * item is not present.
     * @param book The Book that is being added.
     */

    public void removePhysicalBook(PhysicalBook book) {
        if (this.getCatalogue().contains(book)) {
            this.getCatalogue().remove(book);
            this.getPhysicalCatalogue().remove(book);
        } else {
            System.out.println("Physical book object not found in catalogue");
        }
    }

    /**
     * Takes an int ID and removes corresponding item from Physical Book catalogue.
     * @param _id int ID.
     */

    public void removePhysicalBook(int _id) {
        ArrayList<PhysicalBook> books = new ArrayList<>();
        for(PhysicalBook book: physicalCatalogue) {
            if(book.get_id() == _id){
                books.add(book);
            }
        }
        try {
            this.physicalCatalogue.remove(books.get(0));
        } catch (IndexOutOfBoundsException e){}
    }

    /**
     * Takes an int and removes item at that index in the main catalogue. If the index is not present, an error will be printed.
     * Also removes item from either the PhysicalBook catalogue or the Ebook catalogue depending on the type.
     * @param index The index of he Book that is being added.
     */

    public void removeResourceAtIndex(int index) {
        LibraryItem item;
        try {
            item = catalogue.get(index);
            catalogue.remove(item);

            if (item instanceof PhysicalBook){
                removePhysicalBook(item.get_id());
            } else {
                removeEBook(item.get_id());
            }
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("No object at this index");
        }
    }

    /**
     * Static Method that returns the present date and time in a String of format: dd/MM/yyyy HH:mm:ss
     * @return String
     */

    public static String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    /**
     *  Checks if given Library item is in catalogue and changes the object's name to the string provided.
     * @param item The eBook that is being searched for.
     * @param newTitle The new title of the book.
     */

    public void editBookTitle(LibraryItem item, String newTitle) {
        if (booleanSearchForItem(item)) {
            item.setName(newTitle);
        } else {
            System.out.println("item not in catalogue");
        }
    }

    /**
     * Prints all available books in the main catalogue
     */

    public void printAvailableBooks() {
        ArrayList<PhysicalBook> books = new ArrayList<>();
        for(PhysicalBook item: physicalCatalogue) {
            if( !item.isOnLoan()) {
                books.add(item);
                System.out.println(item.toString());
            }
        }
    }

    /**
     * Calculates number of items in the catalogue
     * @return int
     */

    public int noItemsInCatalogue(){
        return catalogue.size();
    }

    /**
     * Checks out book.
     * Adds the user as currentReader of the book, changes loan status and increments times read.
     * Adds the book to the user's loan ArrayList.
     * @param reader The user checking the book out.
     * @param book The physical book being checked out.
     */

    public void checkOutBook(User reader, PhysicalBook book) {
        if (users.contains(reader) && reader.numCurrentBooks() <= reader.maxBooksAllowed && catalogue.contains(book) && !book.isOnLoan()) {
            reader.addBook(book);
            book.setCurrentUser(reader);
            book.changeLoanStatus();
            book.incrementTimesRead();
        } else {
            if (!users.contains(reader)) {
                System.out.println("User not recognised");
            } else if (reader.numCurrentBooks() > reader.maxBooksAllowed) {
                System.out.println("Reader already has check out the maximum number of books");
            } else if (!catalogue.contains(book)) {
                System.out.println("Book not found in catalogue");
            } else if (!book.isOnLoan()) {
                System.out.println("Book is already out on Loan");
            }
        }
    }

    /**
     * Takes a physical book and returns book.
     * Removes the user as currentReader of the book and changes loan status.
     * Removes the book from the user's loan ArrayList.
     * @param book The eBook that is being returned.
     * @param isDamaged The boolean that indicates if the book has been damages.
     * @param damage The string that described the damage.
     */

    public void returnBook(PhysicalBook book, boolean isDamaged, String damage){
        if(catalogue.contains(book)) {
            if(isDamaged) {
                book.setDamages(damage);
            }
            User user = book.getCurrentUser();
            book.removeCurrentUser();
            user.returnBook(book);
            book.changeLoanStatus();

        } else {
            System.out.println("Item not found in catalogue");
        }
    }

    /**
     * Takes an Ebook and allows user to view book. Adds the user as a current reader and increments times read.
     * @param book The eBook that is being viewed.
     * @param reader The user that is viewing the book.
     */

    public void viewEBook(EBook book, User reader) {
        if (users.contains(reader) && catalogue.contains(book)) {
            reader.addBook(book);
            book.addCurrentReader();
            book.incrementTimesRead();
        } else {
            if (!users.contains(reader)) {
                System.out.println("User not recognised");
            } else if (!catalogue.contains(book)) {
                System.out.println("Book not found in catalogue");
            }
        }
    }

    /**
     * Takes an Ebook and stops the user viewing it. Removes the reader as a current reader.
     * @param book The eBook that has been viewed.
     * @param reader The user that has been viewing the book.
     */

    public void stopViewingEBook(EBook book, User reader){
        if(catalogue.contains(book)) {
            book.removeCurrentReader();
            reader.returnBook(book);
        } else {
            System.out.println("Item not found in catalogue");
        }
    }

    /**
     * Sends notification.
     * Sends the provided string to every user in the user catalogue that currently has a book out on loan.
     * @param notification The text of the notification.
     */

    public void sendNotifications(String notification) {
        for(PhysicalBook book: physicalCatalogue) {
            if(book.isOnLoan()) {
                book.getCurrentUser().setNotifications(notification);
            }
        }
    }

    /**
     * prints the details of every physical book in the catalogue
      */

    public void printPhysicalBooks(){
        for(PhysicalBook book: physicalCatalogue) {
            System.out.println(book.toString());
        }
    }

    /**
     * Prints the details of every eBook in the catalogue
     */

    public void printEBooks(){
        for(EBook book: eBookCatalogue) {
            System.out.println(book.toString());
        }
    }

    /**
     * Method that adds user to the userList if the list doesn't contain the user already.
     * @param user The user being added to the list.
     */

    public void addUser(User user) {
        if (this.users.contains(user)) {
            System.out.println("User is already in the database");
        } else {
            this.users.add(user);
        }
    }

    /**
     * Method that prints details of the Library.
     */

    public void printLibraryDetails(){
        System.out.println(toString());
    }

}
