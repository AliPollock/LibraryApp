package LibraryApp.Models;

public class Staff extends User {
    public Staff(String name, Library library, String password) {
        super(name, password);
        this.isAdmin = false;
        this.isMember = true;
        this.isStaff = true;
        this.maxBooksAllowed = 30;
    }


}
