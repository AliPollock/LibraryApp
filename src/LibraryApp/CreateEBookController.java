package LibraryApp;

import LibraryApp.Models.Author;
import LibraryApp.Models.EBook;
import LibraryApp.Models.PhysicalBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateEBookController implements Initializable {

    ObservableList editOptionsList = FXCollections.observableArrayList();

    @FXML private Parent root;
    @FXML private TextField title;
    @FXML private TextField author;
    @FXML private TextField publisher;
    @FXML private TextField date;
    @FXML private TextField ISBN;
    @FXML private Button cancelButton;
    @FXML private Button okayButton;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DatabaseHandler.getInstance();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }





    /**The createEbook method takes the fields filled by the createEbook.fxml form and converts them to sql compatible data types,
     it checks if the author already exists in the database and if not creates a new one. It then creates an Ebook instance and uses this to
     create a new Ebook database entry
     **/
    @FXML
    private void createEBook(ActionEvent event) throws SQLException, IOException {
        String bookTitle = title.getText();
        String bookAuthor = author.getText();
        String bookPublisher = publisher.getText();
        String publicationDate = date.getText();
        String iSBN = ISBN.getText();

        if(bookTitle.isEmpty()||bookAuthor.isEmpty()||bookPublisher.isEmpty()||publicationDate.isEmpty()||iSBN.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please complete all fields");
            alert.showAndWait();
            return;
        }

        Author authorInstance = DatabaseHandler.getInstance().checkIfAuthorExists(bookAuthor);

        EBook book = new EBook(bookTitle, authorInstance, publicationDate, bookPublisher, iSBN);


        String sqlString = "INSERT INTO EBooks (_eBookId, name, author, publicationDate, publisher, topics, timesRead, bio, isbn, currentUsers, AccessExpiresHours)" +
                "VALUES(" + book.get_id() + ", '" + book.getName() + "', '" + book.getAuthor().get_id() + "', '" + book.getPublicationDate() + "', '" + book.getPublisher() + "', '" +
                book.getTopicsAsString() + "', " + book.getTimesRead() + ", '" + book.getBio() + "', '" + book.getISBN() + "', '" + book.getCurrentUsers() + "', '" + book.getAccessExpiresHours() + "')";


        DatabaseHandler.getInstance().execAction(sqlString);
        System.out.println(book);
        cancel();
    }

    public void cancel() throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/Home.fxml"));
        Scene createBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/Home.fxml"));
        Scene createBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }
}

