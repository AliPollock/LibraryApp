package LibraryApp.Models;
import java.util.ArrayList;

/**
 * Abstract class that generally represents items in Library. Stores the information and carries out the operations that
 * would be stored or implicit in a Library book or e-resource.
 */

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

    /**
     * Constructor for a library item, generates a unique object ID and assigns default values to fields which are not provided.
     * @param name The name of the item.
     * @param author The name of the author.
     * @param publicationDate The publication date of the object as a string.
     * @param publisher The name of the publisher.
     * @param iSBN The identification number the library attaches to the work.
     */

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

    /**
     * Method which generates a string representation of the object.
     * @return The string representation.
     */

    @Override
    public String toString() {
        return "LibraryItem{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", publicationDate='" + publicationDate + '\'' +
                ", publisher='" + publisher + '\'' +
                ", topics=" + topics +
                ", timesRead=" + timesRead +
                ", bio='" + bio + '\'' +
                ", iSBN='" + iSBN + '\'' +
                '}';
    }


    //Getters

    public int get_id() {
    return _id;
    }

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

    /**
     * An alternative getter for topics which returns topics as a comma separated string in order to enter into the database.
     * @return String representation of topics.
     */

    public String getTopicsAsString() {
        String outputString = null;
        for(String string: this.topics) {
            outputString += string + ", ";
        }
        return outputString;
    }

    public String getBio() {
        return bio;
    }

    public String getISBN() {
        return iSBN;
    }

    public int getTimesRead() {
        return timesRead;
    }

    //Setters

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setTimesRead(int timesRead) {
        this.timesRead = timesRead;
    }

    public void setISBN(String iSBN) {
        this.iSBN = iSBN;
    }

    /**
     * An alternative Setter which breaks apart the topic string returned from the database and adds each topic into the topics arraylist.
     * @param topicString A comma separated list of topics.
     */

    public void setTopicsCSV(String topicString){
        String[] untrimmedTopics = topicString.split(",");
        for (String topic: untrimmedTopics){
            String stringTopic = topic.trim();
            this.topics.add(stringTopic);
        }
    }

    /**
     * Increments the times a book has been read.
     */

    public void incrementTimesRead() {
        this.timesRead += 1;
    }
}
