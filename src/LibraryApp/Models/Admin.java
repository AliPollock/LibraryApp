package LibraryApp.Models;

/**
 * Class that represents an Admin. Stores the information specific to an Admin member of the Library.
 * Inherits from User
 */

public class Admin extends User {

    /**
     * Class constructor that sets default values for the student and calls {@link LibraryApp.Models.User#User}
     * @param name The name of the Student.
     * @param password The password for the Student.
     */

    public Admin(String name, String password) {
        super(name, password);
        this.isAdmin = true;
        this.isMember = true;
        this.isStaff = true;
        this.maxBooksAllowed = 30;
    }

}
