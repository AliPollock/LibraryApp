package LibraryApp.Models;

public class Guest extends User {
//    private String expiryDate;

    public Guest(String name, Library library, String password) {
        super(name, library, password);
        this.isAdmin = false;
        this.isMember = false;
        this.isStaff = false;
        double num = (Math.random() * ((999-1) + 1)) + 1;
        this.userID = "gue" + "0" + Double.toString(num) + name.substring(0,1);
    }
}
