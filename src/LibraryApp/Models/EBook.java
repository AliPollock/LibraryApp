package LibraryApp.Models;
import java.util.ArrayList;

/**
 * This class represents an electronic resource. Stores the information and carries out the operations that
 *  are specific to an Electronic Library book. Inherits from LibraryItem.
 */

public class EBook extends LibraryItem {
    private int currentUsers;
    private String accessExpiresHours;
    private static final int maximumConcurrentUsers = 10;
    private ArrayList<Computer> devices;

    /**
     * Class constructor which provides default values for fields and calls  {@link LibraryApp.Models.LibraryItem#LibraryItem}
     * @param name The name of the EBook.
     * @param author The name of the author.
     * @param publicationDate The publication date as a String.
     * @param publisher The name of the publisher.
     * @param iSBN The ISBN number of the book as a string.
     */

    public EBook(String name, Author author, String publicationDate, String publisher, String iSBN) {
        super(name, author, publicationDate, publisher, iSBN);
        this.devices = new ArrayList<Computer>();
        this.accessExpiresHours = null;
        this.currentUsers = 0;
    }

    /**
     * Method that generates a String representation of the EBook.
     * @return The String representation.
     */

    @Override
    public String toString() {
        return  "EBook {" +
                "_id=" + get_id() +
                ", name='" + getName() + '\'' +
                ", author=" + getAuthor() +
                ", publicationDate='" + getPublicationDate() + '\'' +
                ", publisher='" + getPublisher() + '\'' +
                ", topics=" + getTopics() +
                ", timesRead=" + getTimesRead() +
                ", bio='" + getBio() + '\'' +
                ", iSBN='" + getISBN() + '\'' +
                ", currentUsers=" + currentUsers +
                ", accessExpiresHours='" + accessExpiresHours + '\'' +
                ", devices=" + devices +
                '}';
    }


    /**
     * Method that gets current number of users.
     * @return int number of users.
     */

    public int getCurrentUsers() {
        return currentUsers;
    }

    /**
     * Method that gets maximum number of users allowed.
     * @return int maximum users.
     */

    public static int getMaximumConcurrentUsers() {
        return maximumConcurrentUsers;
    }

    /**
     * Method that gets how many hours till the user's access expires.
     * @return String number of hours till access expires.
     */

    public String getAccessExpiresHours() {
        return accessExpiresHours;
    }

    /**
     * Method that gets an ArrayList&lt;Computer&gt; of computers.
     * @return  ArrayList&lt;LibraryItem&gt;.
     */

    public ArrayList<Computer> getDevices() {
        return devices;
    }

    /**
     * Method that sets a count of the current users viewing the book.
     * @param currentReaders int current users.
     */

    public void setCurrentUsers(int currentReaders) {
        this.currentUsers = currentReaders;
    }

    /**
     * Method that sets the number of hours till access to an EBook expires.
     * @param accessExpiresHours String hours till access expires.
     */

    public void setAccessExpiresHours(String accessExpiresHours) {
        this.accessExpiresHours = accessExpiresHours;
    }

    /**
     * Method that sets ArrayList&lt;Computer&gt; of computers the EBook is available on.
     * @param devices ArrayList&lt;Computer&gt;.
     */

    public void setDevices(ArrayList<Computer> devices) {
        this.devices = devices;
    }


    /**
     * Method that increments the integer count of current readers.
     */

    public void addCurrentReader() {
        this.currentUsers += 1;
    }

    /**
     * Method that decrements the integer count of current readers.
     */


    public void removeCurrentReader() {
        this.currentUsers -=1;
    }

    /**
     * Method that adds a computer to the devices ArrayList&lt;Computer&gt;.
     * @param computer The computer being added.
     */

    public void addDevice(Computer computer) {
        if(this.devices.contains(computer)) {
            System.out.println("This list already contains the device");
        } else {
            this.devices.add(computer);
        }
    }

    /**
     * Method that removes a computer from the devices ArrayList&lt;Computer&gt;.
     * @param computer The computer being added.
     */

    public void removeDevice(Computer computer) {
        this.devices.remove(computer);
    }


}
