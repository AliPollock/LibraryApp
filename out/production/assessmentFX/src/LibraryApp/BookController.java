package LibraryApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class BookController {

    @FXML
    private Parent root;

    @FXML
    private TextField title;
    @FXML
    private TextField author;
    @FXML
    private TextField publisher;
    @FXML
    private TextField date;
    @FXML
    private TextField lib;
    @FXML
    private TextField ISBN;
    @FXML
    private TextField copies;
    @FXML
    private Button cancelButton;
    @FXML
    private Button okayButton;

    DatabaseHandler databaseHandler = new DatabaseHandler();

    public BookController() throws SQLException {
    }


    @FXML
    private void createBook(ActionEvent event) throws SQLException {
        String bookTitle = title.getText();
        String bookAuthor = author.getText();
        String bookPublisher = publisher.getText();
        String publicationDate = date.getText();
        String library = lib.getText();
        String iSBN = ISBN.getText();
        int noCopies = Integer.parseInt(copies.getText().trim());

        if(bookTitle.isEmpty()||bookAuthor.isEmpty()||bookPublisher.isEmpty()||publicationDate.isEmpty()||library.isEmpty()||iSBN.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please complete all fields");
            alert.showAndWait();
            return;
        }
        Author author1 = new Author(bookAuthor); //this all needs to be sorted by checking if the data exists in the database already or not
        Library library1 = new Library(library);
        PhysicalBook book1 = new PhysicalBook(bookTitle, author1, publicationDate, bookPublisher, library1, iSBN, noCopies);
        //if library in library db
        //if db author in Author db
        databaseHandler.addPBook(bookTitle, bookAuthor, publicationDate, bookPublisher, library, noCopies, iSBN);
        System.out.println(book1);
    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene createBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }


    public void title(ActionEvent actionEvent) {
    }

    public void author(ActionEvent actionEvent) {
    }

    public void date(ActionEvent actionEvent) {
    }

    public void lib(ActionEvent actionEvent) {
    }

    public void ISBN(ActionEvent actionEvent) {
    }

    public void copies(ActionEvent actionEvent) {
    }

    public void publisher(ActionEvent actionEvent) {
    }
}
