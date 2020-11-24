package LibraryApp;

import java.util.ArrayList;

public class Author {
    private String name;
    private ArrayList<LibraryItem> authorWorks;

    public Author(String name) {
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
