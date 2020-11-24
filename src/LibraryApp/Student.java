package LibraryApp;

public class Student extends User {
//  private String expiryDate;
    public Student(String name, Library library, String password) {
        super(name, library, password);
        this.isAdmin = false;
        this.isMember = true;
        this.isStaff = false;
        double num = (Math.random() * ((999-1) + 1)) + 1;
        this.userID = "stu" + "1" + Double.toString(num) + name.substring(0,1);
    }

    public void addLibraryFees(double fee){
    }

    public void payLibraryFees(double fee){
    }

//    public ??? requestBook(String name){}


}
