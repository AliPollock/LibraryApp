package LibraryApp.Models;

/**
 * Class that represents a Staff member.
 * Stores the information specific to a Staff member of the Library.
 */


public class Staff extends User {

    /**
     * Class constructor that sets default values for the student and calls {@link LibraryApp.Models.User#User}
     * @param name The name of the Staff member.
     * @param password The password for the Staff member.
     */

    public Staff(String name, String password) {
        super(name, password);
        this.isAdmin = false;
        this.isMember = true;
        this.isStaff = true;
        this.maxBooksAllowed = 30;
    }


}
