package LibraryApp.Models;
import java.util.ArrayList;

/**
 * Class that represents an Author. Stores the information related to the library associated with an author.
 * Inherits from User.
 */

public class Author {
    private int _id;
    private String name;
    private ArrayList<LibraryItem> authorWorks;

    /**
     * The Class constructor which generates a unique ID and initializes the authorWorks ArrayList&lt;LibraryItem&gt;.
     * This is used to create a new Author.
     * @param name The name of the Author.
     */

    public Author(String name) {
        this._id = (int) (Math.random()*1000*(Math.random()*1000*name.length()));
        this.name = name;
        this.authorWorks = new ArrayList<>();
    }

    /**
     * The Class constructor used to instantiate an Author which already exists in the database.
     * @param _id The unique ID of the Author.
     * @param name The name of the author.
     */

    public Author(int _id, String name) {
        this._id = _id;
        this.name = name;
        this.authorWorks = new ArrayList<>();
    }

    /**
     * Method which generates a String representation of the object.
     * @return The String representation.
     */

    @Override
    public String toString() {
        return "(Author: " + name + ")";
    }

    /**
     * Method that gets ID.
     * @return int ID.
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
     * Method that gets author works.
     * @return ArrayList&lt;LibraryItem&gt; works by the author.
     */

    public ArrayList<LibraryItem> getAuthorWorks() {
        return authorWorks;
    }

    /**
     * Method that sets ID.
     * @param _id int ID.
     */

    public void set_id(int _id) {
        this._id = _id;
    }

    /**
     * Method that sets Name.
     * @param name String Name.
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method that sets works by the author.
     * @param authorWorks ArrayList&lt;LibraryItem&gt; works by the author.
     */

    public void setAuthorWorks(ArrayList<LibraryItem> authorWorks) {
        this.authorWorks = authorWorks;
    }

    /**
     * Method that adds a Library Item to the authorWorks ArrayList&lt;LibraryItem&gt;.
     * @param item The item being added.
     */

    public void addWork(LibraryItem item) {
        this.authorWorks.add(item);
    }

}
