package LibraryApp.Models;

/**
 * Class that represents a Guest user of the library.
 * Stores the information and carries out the operations specific to a Staff member of the Library.
 * Inherits from User.
 */

public class Guest extends User {

    /**
     * Class constructor that sets default values for the student and calls {@link LibraryApp.Models.User#User}
     * @param name The name of the Student.
     * @param password The password for the Student.
     */

    public Guest(String name, String password) {
        super(name, password);
        this.isAdmin = false;
        this.isMember = false;
        this.isStaff = false;
    }


}
