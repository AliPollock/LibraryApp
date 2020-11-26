package LibraryApp.Models;

public class Guest extends User {
//    private String expiryDate;

    public Guest(String name, String password) {
        super(name, password);
        this.isAdmin = false;
        this.isMember = false;
        this.isStaff = false;
    }
}
