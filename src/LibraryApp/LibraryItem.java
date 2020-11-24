package LibraryApp;

import java.util.ArrayList;

public abstract class LibraryItem {
    private String name;
    private Author author;
    private String publicationDate;
    private String publisher;
    private Library library;
    private ArrayList<String> topics;
    private static int timesRead;
    private int edition; //maybe get rid of this ####################
    private String bio;
    private double monetaryValue;
    private ArrayList<String> keywords;
    private String iSBN;


    public LibraryItem(String name, Author author, String publicationDate, String publisher, Library library, String iSBN) {
        this.name = name;
        this.author = author;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        this.library = library;
        this.topics = new ArrayList<>();
        this.timesRead = 0;
        this.edition = 1;
        this.bio = null;
        this.monetaryValue = 0;
        this.keywords = new ArrayList<>();
        this.author.addWork(this);
        this.keywords.add(this.author.getName());
        this.keywords.add(this.name);
        this.keywords.add(this.publisher);
        this.iSBN = iSBN;
    }

    public LibraryItem(String name, Author author, String publicationDate, Library library, String iSBN) {
        this.name = name;
        this.author = author;
        this.publicationDate = publicationDate;
        this.publisher = null;
        this.library = library;
        this.topics = new ArrayList<>();
        this.timesRead = 0;
        this.edition = 1;
        this.bio = null;
        this.monetaryValue = 0;
        this.keywords = new ArrayList<>();
        this.author.addWork(this);
        this.keywords.add(this.author.getName());
        this.keywords.add(this.name);
        this.keywords.add(this.publisher);
        this.iSBN = iSBN;
    }

    @Override
    public String toString() {
        return "LibraryItem: " + name +
                ", author: " + author +
                ", library: " + library;
    }

    // getters

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public Library getLibrary() {
        return library;
    }

    public ArrayList<String> getTopics() {
        return topics;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public static int getTimesRead() {
        return timesRead;
    }

    public int getEdition() {
        return edition;
    }

    public String getBio() {
        return bio;
    }

    public double getMonetaryValue() {
        return monetaryValue;
    }

    public String getISBN() {
        return iSBN;
    }

    // setters


    public void setName(String name) {
        this.name = name;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void addKeyword(String keyword) {
        this.keywords.add(keyword);
    }

    public void addTopic(String topics) {
        this.topics.add(topics);
    }

    public void incrementTimesRead() {
        this.timesRead += 1;
    }


}
