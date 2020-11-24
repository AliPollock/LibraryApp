package LibraryApp;


public class EBook extends LibraryItem {
    private static int currentReaders;
    private String url;
    private boolean openAccess;
    private String accessExpiresHours;
    private static final int maximumConcurrentUsers = 10;
    private String physicalCopyLink;


    public EBook(String name, Author author, String publicationDate, String publisher, Library library, String iSBN) {
        super(name, author, publicationDate, publisher, library, iSBN);
        this.url = null;
        this.openAccess = false;
        this.accessExpiresHours = "1";
        this.physicalCopyLink = null;
    }

    // getters

    public int getCurrentReaders() {
        return currentReaders;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isOpenAccess() {
        return this.openAccess;
    }

    public String getAccessExpires() {
        return this.accessExpiresHours;
    }

    public static int getMaximumConcurrentUsers() {
        return maximumConcurrentUsers;
    }

    public String getAccessExpiresHours() {
        return accessExpiresHours;
    }

    public String getPhysicalCopyLink() {
        return physicalCopyLink;
    }

    // setters

    public void setUrl(String url) {
        this.url = url;
    }

    public void setOpenAccess(boolean openAccess) {
        this.openAccess = openAccess;
    }

    public void setAccessExpires() { //#########needs to be edited so it sets time for future ####################
        this.accessExpiresHours = this.getLibrary().getDate();
    }

    public void setPhysicalCopyLink(String name) {
        for (PhysicalBook book: this.getLibrary().getPhysicalCatalogue()) {
            if (book.getName() == name) {
                this.physicalCopyLink = book.getUrl();
            }
        }
    }

    // other methods

    public void changeCurrentReaders(int currentReaders) {
        this.currentReaders += currentReaders;
    }



}
