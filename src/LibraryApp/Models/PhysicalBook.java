package LibraryApp.Models;

/**
 * A class to represent a physical book. Stores the information and carries out the operations that
 *  are specific to a Physical Library book. Inherits from LibraryItem.
 */

public class PhysicalBook extends LibraryItem {
    private User currentUser;
    private boolean isOnLoan;
    private boolean isOverdue;
    private double overDueCharge;
    private String dueDate;
    private String damages;

    /**
     * Class constructor which provides default values for fields and calls {@link LibraryApp.Models.LibraryItem#LibraryItem}
     * @param name The name of the user.
     * @param author The name of the author.
     * @param publicationDate The publication date as a String.
     * @param publisher The name of the publisher.
     * @param iSBN the ISBN number of the book as a String.
     */

    public PhysicalBook(String name, Author author, String publicationDate, String publisher, String iSBN) {
        super(name, author, publicationDate, publisher, iSBN);
        this.currentUser = null;
        this.isOnLoan = false;
        this.isOverdue = false;
        this.overDueCharge = 0;
        this.dueDate = null;
        this.damages = null;
    }

    /**
     * Method that generates a String representation of the object.
     * @return String representation.
     */
    @Override
    public String toString() {
        String toString = "PhysicalBook{" +
                "_id=" + get_id() +
                ", name='" + getName() + '\'' +
                ", author=" + getAuthor() +
                ", publicationDate='" + getPublicationDate() + '\'' +
                ", publisher='" + getPublisher() + '\'' +
                ", topics=" + getTopics() +
                ", timesRead=" + getTimesRead() +
                ", bio='" + getBio() + '\'' +
                ", iSBN='" + getISBN() + '\'' +
                ", isOnLoan=" + isOnLoan +
                ", isOverdue=" + isOverdue +
                ", overDueCharge=" + overDueCharge +
                ", dueDate='" + dueDate + '\'' +
                ", damages='" + damages + '\'';
        if (availability()) {
            toString += "currentUser=" + currentUser;
        }
        return toString;
    }


    //Getters

    public boolean isOverdue() {
        return this.isOverdue;
    }

    public double getOverDueCharge() {
        return this.overDueCharge;
    }


    public String getDueDate() {
        return this.dueDate;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isOnLoan() {
        return isOnLoan;
    }

    public String getDamages() {
        return damages;
    }


    //Setters

    public void setOverDueCharge(double overDueCharge) {
        this.overDueCharge = overDueCharge;
    }

    public void setOverdue(boolean overdue) {
        this.isOverdue = overdue;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void setOnLoan(boolean onLoan) {
        isOnLoan = onLoan;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setDamages(String damages) {
        this.damages += damages + ", ";
    }

    /**
     * Method that takes no parameters and changes boolean loan status.
     */

    public void changeLoanStatus() {
        isOnLoan=!isOnLoan;
    }

    /**
     * method that takes no parameters and changes boolean overdue status.
     */

    public void changeOverdueStatus(){
        isOverdue= !isOverdue;
    }

    /**
     * Method that takes no parameters and determines boolean availability if there is a current user or not.
     * @return A boolean, true if available.
     */

    public boolean availability() {
        return this.currentUser == null;
    }

    /**
     * Method that changes current user to the user given.
     * @param currentUser The user of this book.
     */

    public void addCurrentUser(User currentUser) {
        if (availability()) {
            this.currentUser = currentUser;
        } else {
            System.out.println("book is currently on loan");
        }
    }

    /**
     * Method that removes the current user and sets current user to null.
     */
    public void removeCurrentUser() {
        this.currentUser=null;
    }

}
