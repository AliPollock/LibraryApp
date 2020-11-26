package LibraryApp.Models;

import java.util.ArrayList;

public class Author {
    private int _id;
    private String name;
    private ArrayList<LibraryItem> authorWorks;

    public Author(int _id, String name) { //already exists in db author
        this._id = _id;
        this.name = name;
    }

    public Author(String name) { //create new author
        this._id = (int) (Math.random()*1000*(Math.random()*1000*name.length()));
        this.name = name;
        this.authorWorks = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "(Author: " + name + ")";
    }

    public int get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<LibraryItem> getAuthorWorks() {
        return authorWorks;
    }

    public void addWork(LibraryItem item) {
        this.authorWorks.add(item);
    }

}
