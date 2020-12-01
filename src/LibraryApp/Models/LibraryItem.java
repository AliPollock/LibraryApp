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
        this.bio = "";
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


    /**
     * Method that gets ID.
     * @return String ID.
     */

    public int get_id() {
    return _id;
    }

    /**
     * Method that gets Name.
     * @return String Name.
     */

    public String getName() {
        return name;
    }

    /**
     * Method that gets Author.
     * @return Author object.
     */

    public Author getAuthor() {
        return author;
    }

    /**
     * Method that gets the publication date of the book.
     * @return String publication date
     */

    public String getPublicationDate() {
        return publicationDate;
    }

    /**
     * Method that gets the publisher of the book.
     * @return String publisher.
     */

    public String getPublisher() {
        return publisher;
    }

    /**
     * Method that gets an ArrayList&lt;String&gt; list of topics associated with the book.
     * @return ArrayList&lt;String&gt;.
     */

    public ArrayList<String> getTopics() {
        return topics;
    }

    /**
     * An alternative getter for topics which returns topics as a comma separated string in order to enter into the database.
     * @return String representation of topics.
     */

    public String getTopicsAsString() {
        String buildString = "";
        for(String string: this.topics) {
            buildString += string + ", ";
        }
        String outputString = "";
        if(buildString != "") {
            outputString = buildString.substring(0, buildString.length() - 2);
        }
        return outputString;
    }

    /**
     * Method that gets bio.
     * @return String bio.
     */

    public String getBio() {
        return bio;
    }

    /**
     * Method that gets the ISBN.
     * @return String ISBN.
     */

    public String getISBN() {
        return iSBN;
    }

    /**
     * Method that gets the times a book has been read.
     * @return int times read.
     */

    public int getTimesRead() {
        return timesRead;
    }

    /**
     * Method that sets the ID.
     * @param _id int ID.
     */

    public void set_id(int _id) {
        this._id = _id;
    }

    /**
     * Method that sets the Name.
     * @param name String Name.
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method that sets the Author.
     * @param author String Author.
     */

    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * Method that sets the publication date.
     * @param publicationDate String publication date.
     */

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    /**
     * Method that sets the publisher.
     * @param publisher String publisher.
     */

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Method that sets an ArrayList&lt;String&gt; list of topics associated with the book.
     * @param topics ArrayList&lt;String&gt;
     */

    public void setTopics(ArrayList<String> topics) {
        this.topics = topics;

    }

    /**
     * Method that sets the bio.
     * @param bio String bio
     */

    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * Method that sets the times the book has been read.
     * @param timesRead int times the book has been read.
     */

    public void setTimesRead(int timesRead) {
        this.timesRead = timesRead;
    }

    /**
     * Method that sets the ISBN.
     * @param iSBN int ISBN.
     */

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
