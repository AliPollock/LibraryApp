package LibraryApp.Models;

/**
 * Class that represents a Student.
 * Stores the information and carries out the operations specific to a Student user of the Library.
 * Inherits from User.
 */

public class Student extends User {

    /**
     * Class constructor that sets default values for the student and calls {@link LibraryApp.Models.User#User}
     * @param name The name of the Student.
     * @param password The password for the Student.
     */

    public Student(String name, String password) {
        super(name, password);
        this.isAdmin = false;
        this.isMember = true;
        this.isStaff = false;
    }

    /**
     * Method that adds to the Student's library fees.
     * @param fee The amount being added.
     */

    public void addLibraryFees(double fee){
        this.setLibraryFees(fee);
    }

    /**
     * Method that pays the Student's library fees.
     * @param fee The amount being paid.
     */

    public void payLibraryFees(double fee){
        this.setLibraryFees(-fee);
    }



}
