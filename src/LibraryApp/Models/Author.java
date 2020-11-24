package LibraryApp.Models;

import java.util.ArrayList;

public class Author {
    private int _id;
    private String name;
    private ArrayList<LibraryItem> authorWorks;

    public Author(String name) {
        this._id = (int) (Math.random()*1000*(Math.random()*1000*name.length()));
        this.name = name;
        this.authorWorks = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "(Author: " + name + ")";
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
