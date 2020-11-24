package LibraryApp.Models;

public class PhysicalBook extends LibraryItem {
    private User currentUser;
    private boolean isOnLoan;
    private boolean isOverdue;
    private double overDueCharge;
    private String dueDate;
    private String damages;

    public PhysicalBook(String name, Author author, String publicationDate, String publisher, String iSBN) {
        super(name, author, publicationDate, publisher, iSBN);
        this.currentUser = null;
        this.isOnLoan = false;
        this.isOverdue = false;
        this.overDueCharge = 0;
        this.dueDate = null;
        this.damages = null;
    }


    @Override
    public String toString() {
        return "LibraryItem: " + getName() +
                ", author: " + getAuthor();
    }

    // getters

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
    // setters

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
        this.damages = damages;
    }

    // other methods

    public void changeLoanStatus(String userID) {
        if(userID.charAt(3) == 3) {
            isOnLoan = true;
        }
    }

    public void changeOverdueStatus(){
        if(this.isOverdue) {
            this.isOverdue = false;
        } else {
            this.isOverdue = true;
        }
    }

//    public void addCurrentUser() {}
//    public void removeCurrentUser() {}
//    public void changeDueDate() {}?
//    public void deleteDueDate() {}
//    public void addOverdueCharge() {}
//    public void addDamages() {}
}
