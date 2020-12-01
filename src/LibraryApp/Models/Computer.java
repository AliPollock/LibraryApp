package LibraryApp.Models;

/**
 * Class the represents a computer. Holds the data and functionality in with regards to library interactions that a physical computer would.
 */

public class Computer {
    private int _id;
    private boolean isInUse;
    public static final int TotalNumber = 20;
    public static int numberBeingUsed = 0;
    public String location;

    /**
     * Class constructor, creates a unique ID and assigns default values to the other fields. Used to create a new computer.
     */

    public Computer() {
        this._id = (int) (Math.random()*1000*(Math.random()*6000));
        this.isInUse = false;
        this.location = null;
    }

    /**
     * Class constructor,assigns default values to the other fields. Used to instantiate a computer that already exists in the database.
     * @param _id The unique int ID of the object.
     */

    public Computer(int _id) {
        this._id = _id;
        this.isInUse = false;
        this.location = null;
    }

    /**
     * Method that generates a String representation of the object.
     * @return String object representation.
     */

    @Override
    public String toString() {
        return "Computer {" +
                "_id=" + _id +
                ", isInUse=" + isInUse +
                ", location='" + location + '\'' +
                '}';
    }

    /**
     * Method that gets ID.
     * @return int ID.
     */

    public int get_id() {
        return _id;
    }

    /**
     * Method that checks if the computer is in use.
     * @return boolean true if the computer is in use, otherwise false.
     */

    public boolean isInUse() {
        return isInUse;
    }

    /**
     * Method that gets the total number of computer objects.
     * @return int total number of computer objects.
     */

    public static int getTotalNumber() {
        return TotalNumber;
    }

    /**
     * Method that gets the number of computers being used.
     * @return int number being used.
     */

    public static int getNumberBeingUsed() {
        return numberBeingUsed;
    }

    /**
     * Method that gets the location of the Computer.
     * @return String location.
     */

    public String getLocation() {
        return location;
    }

    /**
     * Method that sets the ID.
     * @param _id int ID.
     */

    public void set_id(int _id) {
        this._id = _id;
    }

    /**
     * Method that changes if the computer is in use or not.
     * @param inUse boolean true if in use.
     */

    public void setInUse(boolean inUse) {
        isInUse = inUse;
        numberBeingUsed +=1;
    }

    /**
     * Method that sets the number being used.
     * @param numberBeingUsed int number being used.
     */

    public static void setNumberBeingUsed(int numberBeingUsed) {
        Computer.numberBeingUsed = numberBeingUsed;
    }

    /**
     * Method that sets the location of the Computer.
     * @param location String location.
     */

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Method that allows the computer to be used, changes isInUse and increments numberInUse.
     * @return boolean true if in use.
     */

    public boolean useComputer() {
        if(this.isInUse == false && Computer.numberBeingUsed < Computer.getTotalNumber()) {
            Computer.numberBeingUsed += 1;
            this.isInUse = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method that allows the computer to stop being used, changes isInUse and decrements numberInUse.
     * @return boolean true if the computer is in use.
     */

    public boolean stopUsingComputer() {
        if(this.isInUse == true) {
            Computer.numberBeingUsed -= 1;
            this.isInUse = false;
            return true;
        } else {
            return false;
        }
    }

}
