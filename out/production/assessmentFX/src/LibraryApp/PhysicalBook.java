package LibraryApp;

public class PhysicalBook extends LibraryItem {
    private boolean isOnLoan;
    private boolean isOverdue;
    private double overDueCharge;
    private int copiesInStock;
    private int noCopies;
    private String dueDate;
    private String url;
    private String ebookVersionLink;
    private boolean isDamaged;



    public PhysicalBook(String name, Author author, String publicationDate, String publisher, Library library, String iSBN, int noCopies) { //need to have another constructor which will expect a string for author and create an author in the constructor
        super(name, author, publicationDate, publisher, library, iSBN);
        this.isOverdue = false;
        this.overDueCharge = 0.0;
        this.isOnLoan = false;
        this.copiesInStock = noCopies;
        this.noCopies = noCopies;
        this.dueDate = null;
        this.url = null;
        this.ebookVersionLink = null;
        this.isDamaged = false;
    }

    public PhysicalBook(String name, Author author, String publicationDate, Library library, String iSBN, int noCopies) {
        super(name, author, publicationDate, library, iSBN);
        this.isOverdue = false;
        this.overDueCharge = 0.0;
        this.isOnLoan = false;
        this.copiesInStock = noCopies;
        this.noCopies = noCopies;
        this.dueDate = null;
        this.url = null;
        this.ebookVersionLink = null;
        this.isDamaged = false;
    }

    @Override
    public String toString() {
        return "LibraryItem: " + getName() +
                ", author: " + getAuthor() +
                ", library: " + getLibrary();
    }

    // getters

    public boolean isOnLoan() {
        return this.isOnLoan;
    }

    public boolean isOverdue() {
        return this.isOverdue;
    }

    public double getOverDueCharge() {
        return this.overDueCharge;
    }

    public int getCopiesInStock() {
        return this.copiesInStock;
    }

    public int getNoCopies() {
        return this.noCopies;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public String getUrl() {
        return this.url;
    }

    public String getEbookVersionLink() {
        return this.ebookVersionLink;
    }

    public boolean isDamaged() {
        return this.isDamaged;
    }

    // setters

    public void setDueDate() {
        this.dueDate = this.getLibrary().getDate();
    }

    public void setOverDueCharge(double overDueCharge) {
        this.overDueCharge = overDueCharge;
    }

    public void setOverdue(boolean overdue) {
        this.isOverdue = overdue;
    }
    public void setEbookVersionLink(String name) {
        for (EBook book: this.getLibrary().getEBookCatalogue()) {
            if (book.getName() == name) {
                this.ebookVersionLink = book.getUrl();
            }
        }
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

    public void incrementCopies(int num) {
        this.copiesInStock +=num;
    }

    public void decrementCopies(int num) {
        this.copiesInStock -=num;
    }



//    public void changeDueDate() {}?
//    public void deleteDueDate() {}
}
