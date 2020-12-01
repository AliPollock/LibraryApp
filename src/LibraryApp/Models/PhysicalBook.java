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
        this.damages = "";
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
                ", author=" + getAuthor().getName() +
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
        if (isOnLoan) {
            toString += ", currentUser=" + currentUser;
        }
        return toString;
    }

    /**
     * Method that gets if a book is overdue.
     * @return boolean true if overdue.
     */

    public boolean isOverdue() {
        return this.isOverdue;
    }

    /**
     * Method that gets the overdue charge.
     * @return double overdue charge.
     */

    public double getOverDueCharge() {
        return this.overDueCharge;
    }

    /**
     * Method that gets the date a book is due on.
     * @return String due date.
     */

    public String getDueDate() {
        return this.dueDate;
    }

    /**
     * Method that gets the current user of the book.
     * @return the User object currently using the book.
     */

    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Method that gets if the book is on loan.
     * @return boolean true if the book is on loan.
     */

    public boolean isOnLoan() {
        return isOnLoan;
    }

    /**
     * Method that gets a description of damages the book has.
     * @return comma separated String of damages.
     */

    public String getDamages() {
        return damages;
    }

    /**
     * Method that sets the overdue charge associated with the book.
     * @param overDueCharge double overdue charge.
     */

    public void setOverDueCharge(double overDueCharge) {
        this.overDueCharge = overDueCharge;
    }

    /**
     * Method that sets if the book is overdue.
     * @param overdue boolean true if the book is overdue.
     */

    public void setOverdue(boolean overdue) {
        this.isOverdue = overdue;
    }

    /**
     * Method that sets the current user of the book.
     * @param currentUser User object currently using the book.
     */

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Method that sets if the book is on loan.
     * @param onLoan boolean true if the book is on loan.
     */

    public void setOnLoan(boolean onLoan) {
        isOnLoan = onLoan;
    }

    /**
     * Method that sets the due date of the the book.
     * @param dueDate String due date.
     */

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Method that sets the damages associated with the book as a comma separated String.
     * @param damages comma separated String of damages.
     */

    public void setDamages(String damages) {
        if(this.damages.equals("")) {
            this.damages +=  damages;
        } else {
            this.damages += ", " + damages;
        }
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
     * Method that removes the current user and sets current user to null.
     */
    public void removeCurrentUser() {
        this.currentUser=null;
    }

    /**
     * Method that deletes all damages.
     */
    public void removeDamages() {
        this.damages ="";
    }

}
