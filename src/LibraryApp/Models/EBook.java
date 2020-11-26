package LibraryApp.Models;


import java.util.ArrayList;

public class EBook extends LibraryItem {
    private int currentUsers;
    private String accessExpiresHours;
    private static final int maximumConcurrentUsers = 10;
    private ArrayList<Computer> devices;

    public EBook(String name, Author author, String publicationDate, String publisher, String iSBN) {
        super(name, author, publicationDate, publisher, iSBN);
        this.devices = new ArrayList<Computer>();
        this.accessExpiresHours = null;
        this.currentUsers = 0;
    }

    @Override
    public String toString() {
        return "EBook: " + this.getName() +
                ", author: " + this.getAuthor().getName() +
                '}';
    }

    // getters

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

    // setters


//    public void setAccessExpires() { //#########needs to be edited so it sets time for future ####################
//        String timeNow = this.getLibrary().getDate();
//        this.accessExpiresHours= null;
//    }

    public void setCurrentUsers(int currentReaders) {
        this.currentUsers = currentReaders;
    }

    public void setAccessExpiresHours(String accessExpiresHours) {
        this.accessExpiresHours = accessExpiresHours;
    }

    public void setDevices(ArrayList<Computer> devices) {
        this.devices = devices;
    }
    // other methods

    public void addCurrentReader() {
        this.currentUsers += 1;
    }

    public void removeCurrentReader() {
        this.currentUsers -=1;
    }

//    public void addDevice() {}
//    public void removeDevice() {}


}
