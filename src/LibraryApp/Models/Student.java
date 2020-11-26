package LibraryApp.Models;

public class Student extends User {
//  private String expiryDate;

    public Student(String name, String password) {
        super(name, password);
        this.isAdmin = false;
        this.isMember = true;
        this.isStaff = false;
    }

    public void addLibraryFees(double fee){
    }

    public void payLibraryFees(double fee){
    }

//    public ??? requestBook(String name){}


}
