package LibraryApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import LibraryApp.Author;
import LibraryApp.Library;
import LibraryApp.LibraryItem;
import LibraryApp.PhysicalBook;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class Controller {

    @FXML
    private Parent root;

    @FXML
    private TextField libraryName;
    @FXML
    private TextField search;
    @FXML
    private ArrayList<LibraryItem> catalogue;
    @FXML
    private ListView<LibraryItem> catalogueView;
    @FXML
    private TextArea bookDetailsTextArea;

    public void initialize() {
        Library strathclyde = new Library("strathclyde");
        Author Michael = new Author("Michael Kolling");
        PhysicalBook Bluej1 = new PhysicalBook("BlueJ1", Michael, "10/10/2000","infomorma",  strathclyde, "3533424221", 5);
        PhysicalBook Bluej2 = new PhysicalBook("BlueJ2", Michael, "10/10/2000","infomorma",  strathclyde, "3533424222", 4);
        PhysicalBook Bluej3 = new PhysicalBook("BlueJ3", Michael, "10/10/2000","infomorma",  strathclyde, "3533424223", 7);
        PhysicalBook Bluej4 = new PhysicalBook("BlueJ4", Michael, "10/10/2000","infomorma",  strathclyde, "3533424224", 3);
        PhysicalBook Bluej5 = new PhysicalBook("BlueJ5", Michael, "10/10/2000","infomorma",  strathclyde, "3533424225", 2);

        catalogue = new ArrayList<>();
        catalogue.add(Bluej1);
        catalogue.add(Bluej2);
        catalogue.add(Bluej3);
        catalogue.add(Bluej4);
        catalogue.add(Bluej5);

        catalogueView.getItems().setAll(catalogue);
        catalogueView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    @FXML
    public void handleClickCatalogueView() {
        LibraryItem item = catalogueView.getSelectionModel().getSelectedItem();
        bookDetailsTextArea.setText(item.getName());
    }


    @FXML
    public void createLibrary() throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("library.fxml"));
        Scene createBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }

    @FXML
    public void createBook(ActionEvent event) throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("book.fxml"));
        Scene createBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }

    public void createEBook(ActionEvent actionEvent) throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("Ebook.fxml"));
        Scene createBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }

    @FXML
    public void search() {
    }
}
