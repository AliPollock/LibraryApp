package LibraryApp;

import LibraryApp.Models.Author;
import LibraryApp.Models.PhysicalBook;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BookPageController implements Initializable {

    @FXML private Parent root;
    DatabaseHandler handler = DatabaseHandler.getInstance();
    @FXML private Label _id;

    @FXML public Label nameLabel;
    @FXML public Label authorNameLabel;
    @FXML public Label publicationDateLabel;
    @FXML public Label publisherLabel;
    @FXML public Label topicsLabel;
    @FXML public Label timesReadLabel;
    @FXML public Label bioLabel;
    @FXML public Label iSBNLabel;
    @FXML public Label loanStatusLabel;

    public BookPageController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DatabaseHandler.getInstance();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void viewBook(String name, int _id, String authorName) {
//        this._id.setText(Integer.toString(_id));

        System.out.println("hi there");
        loadData(_id);
    }



    private void loadData(int _id) {
        String sqlBooks = "SELECT * FROM PBooks INNER JOIN Authors ON PBooks.author=Authors._authorId WHERE _id=" + _id;
        ResultSet results = handler.execQuery(sqlBooks);
        try {
            while (results.next()) {
                String name = results.getString("name");
                String authorName = results.getString("authorName");
                String publicationDate = results.getString("publicationDate");
                String publisher = results.getString("publisher");
                String topics = results.getString("topics");
                int timesRead = results.getInt("timesRead");
                String isbn = results.getString("isbn");
                String bio = results.getString("bio");
                Boolean booleanAvailability = results.getBoolean("isOnLoan");
                String availability = "";
                if (!booleanAvailability) {
                    availability += "available";
                } else {
                    availability += "On Loan";
                }

                Author authorInstance = handler.checkIfAuthorExists(authorName);
                PhysicalBook bookInstance = new PhysicalBook(name, authorInstance, publicationDate, publisher, isbn);



                nameLabel.setText(bookInstance.getName());
                authorNameLabel.setText(bookInstance.getAuthor().getName());
                publicationDateLabel.setText(bookInstance.getName());
                publisherLabel.setText(bookInstance.getName());
                iSBNLabel.setText(bookInstance.getISBN());
                topicsLabel.setText(topics);
                bioLabel.setText(bio);
                timesReadLabel.setText(Integer.toString(timesRead));
                loanStatusLabel.setText(availability);



            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void home(ActionEvent actionEvent) throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/Home.fxml"));
        Scene createBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }

}
