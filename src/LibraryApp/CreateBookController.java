package LibraryApp;

import LibraryApp.Models.Author;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Class that controls the CreateBook page, initializes the data, controls the @FXML fields and actions.
 */

public class CreateBookController implements Initializable {


    @FXML private Parent root;
    @FXML private TextField title;
    @FXML private TextField author;
    @FXML private TextField publisher;
    @FXML private TextField date;
    @FXML private TextField ISBN;

    ObservableList editOptionsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DatabaseHandler.getInstance();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }



    /**
     * Method takes the data from the fxml, checks if the author already exists in the database and if not creates a new one.
     * It creates a new book in the database.
     * @param actionEvent An external stimulus from user interface.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */

    @FXML
    private void createBook(ActionEvent actionEvent) throws SQLException, IOException {
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

        PhysicalBook book = new PhysicalBook(bookTitle, authorInstance, publicationDate, bookPublisher, iSBN);



        String sqlString = "INSERT INTO PBooks (_id, name, author, publicationDate, publisher, topics, timesRead, bio, isbn, currentUser, isOnLoan, isOverdue, overDueCharge, dueDate, damages)" +
                    "VALUES(" + book.get_id() + ", '" + book.getName() + "', '" + book.getAuthor().get_id() + "', '" + book.getPublicationDate() + "', '" + book.getPublisher() + "', '" +
                    book.getTopicsAsString() + "', " + book.getTimesRead() + ", '" + book.getBio() + "', '" + book.getISBN() + "', '" + 0 + "', '" + 0 +
                    "', '" + 0 + "', " + book.getOverDueCharge() + ", '" + book.getDueDate() + "', '" + book.getDamages() + "')";


        DatabaseHandler.getInstance().execAction(sqlString);
        System.out.println(book);
        cancel();
    }

    /**
     * Route that creates new Home scene and links to the fxml file.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */

    public void cancel() throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/Home.fxml"));
        Scene createBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }

    /**
     * Route that creates new Home scene and links to the fxml file.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */

    public void cancel(ActionEvent actionEvent) throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/Home.fxml"));
        Scene createBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }


}
