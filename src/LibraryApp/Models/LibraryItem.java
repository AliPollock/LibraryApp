package LibraryApp.Models;

import java.util.ArrayList;

public abstract class LibraryItem {
    private int _id;
    private String name;
    private Author author;
    private String publicationDate;
    private String publisher;
    private ArrayList<String> topics;
    private int timesRead;
    private String bio;
    private String iSBN;

    public LibraryItem(String name, Author author, String publicationDate, String publisher, String iSBN) {
        this._id = (int) (Math.random()*1000*(Math.random()*1000*name.length()));
        this.name = name;
        this.author = author;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        this.topics = new ArrayList<>();
        this.bio = null;
        this.iSBN = iSBN;
        this.timesRead = 0;
    }


    @Override
    public String toString() {
        return "LibraryItem: " + name +
                ", author: " + author;
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

    public ArrayList<String> getTopics() {
        return topics;
    }

    public String getTopicsAsString() {
        String outputString = null;
        for(String string: this.topics) {
            outputString += string + ", ";
        }
        return outputString;
    }

    public int getTimesRead() {
        return timesRead;
    }

    public String getBio() {
        return bio;
    }

    public String getISBN() {
        return iSBN;
    }

    public int get_id() {
        return _id;
    }

    public String getiSBN() {
        return iSBN;
    }

    // setters

    public void setName(String name) {
        this.name = name;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void addTopic(String topics) {
        this.topics.add(topics);
    }

    public void incrementTimesRead() {
        this.timesRead += 1;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setTopics(ArrayList<String> topics) {
        this.topics = topics;
    }

    public void setTimesRead(int timesRead) {
        this.timesRead = timesRead;
    }

    public void setiSBN(String iSBN) {
        this.iSBN = iSBN;
    }
}
