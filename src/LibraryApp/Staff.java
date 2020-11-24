package LibraryApp;

public class Staff extends User {
    public Staff(String name, Library library, String password) {
        super(name, library, password);
        this.isAdmin = false;
        this.isMember = true;
        this.isStaff = true;
        double num = (Math.random() * ((999-1) + 1)) + 1;
        this.userID = "sta" + "2" + Double.toString(num) + name.substring(0,1);
        this.maxBooksAllowed = 30;
    }


}
