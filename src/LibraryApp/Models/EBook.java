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
        return  "EBook{" +
                "_id=" + get_id() +
                ", name='" + getName() + '\'' +
                ", author=" + getAuthor() +
                ", publicationDate='" + getPublicationDate() + '\'' +
                ", publisher='" + getPublisher() + '\'' +
                ", topics=" + getTopics() +
                ", timesRead=" + getTimesRead() +
                ", bio='" + getBio() + '\'' +
                ", iSBN='" + getISBN() + '\'' +
                "currentUsers=" + currentUsers +
                ", accessExpiresHours='" + accessExpiresHours + '\'' +
                ", devices=" + devices +
                '}';
    }


    //Getters

    public int getCurrentUsers() {
        return currentUsers;
    }

    public static int getMaximumConcurrentUsers() {
        return maximumConcurrentUsers;
    }

    public String getAccessExpiresHours() {
        return accessExpiresHours;
    }

    public ArrayList<Computer> getDevices() {
        return devices;
    }

    //Setters

    public void setCurrentUsers(int currentReaders) {
        this.currentUsers = currentReaders;
    }

    public void setAccessExpiresHours(String accessExpiresHours) {
        this.accessExpiresHours = accessExpiresHours;
    }

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
        this.devices.add(computer);
    }

    /**
     * Method that removes a computer from the devices ArrayList&lt;Computer&gt;.
     * @param computer The computer being added.
     */

    public void removeDevice(Computer computer) {
        this.devices.remove(computer);
    }

}
