package LibraryApp;

import LibraryApp.Models.Author;
import LibraryApp.Models.PhysicalBook;
import javafx.beans.Observable;
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

public class BookController implements Initializable {

    ObservableList editOptionsList = FXCollections.observableArrayList();

    @FXML private Parent root;

    @FXML private TextField title;
    @FXML private TextField author;
    @FXML private TextField publisher;
    @FXML private TextField date;
    @FXML private TextField ISBN;
    @FXML private ChoiceBox<String> editChoiceValue;
    @FXML private TextField propertySelection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DatabaseHandler.getInstance();
            loadEditOptions();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public BookController() throws SQLException {
    }


    @FXML
    private void createBook(ActionEvent event) throws SQLException {
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

        Author author1 = new Author(bookAuthor); //this all needs to be sorted by checking if the data exists in the database already or not
        PhysicalBook book = new PhysicalBook(bookTitle, author1, publicationDate, bookPublisher, iSBN);

        String bookUser = null;
        if(book.getCurrentUser() != null) {
            bookUser = book.getCurrentUser().getName();
        }

        String isOnLoanString = "";
        if(book.isOnLoan()) {
            isOnLoanString = "true";
        } else {
            isOnLoanString = "false";
        }

        String sqlString = "INSERT INTO PBooks (_id, name, author, publicationDate, publisher, topics, timesRead, bio, isbn, currentUser, isOnLoan, isOverdue, overDueCharge, dueDate, damages)" +
                    "VALUES(" + book.get_id() + ", '" + book.getName() + "', '" + book.getAuthor().getName() + "', '" + book.getPublicationDate() + "', '" + book.getPublisher() + "', '" +
                    book.getTopicsAsString() + "', " + book.getTimesRead() + ", '" + book.getBio() + "', '" + book.getISBN() + "', '" + bookUser + "', '" + book.isOnLoan() +
                    "', '" + book.isOverdue() + "', " + book.getOverDueCharge() + ", '" + book.getDueDate() + "', '" + book.getDamages() + "')";


        DatabaseHandler.getInstance().execAction(sqlString);
        System.out.println(book);
    }

    public void editBook(ActionEvent actionEvent) { //this functionality needs to go on the book page
        String newValue = propertySelection.getText();
        String choice = editChoiceValue.getValue();

        if(newValue.isEmpty()|| choice == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please make a selection and enter a new value");
            alert.showAndWait();
            return;
        }
        //pseudocode for how to finish edit method once it is embedded in book page
        //SQL query database for specific book values, use to instantiate book
        //Book newBook = Book()
        //newBook.methodToChangeChoice(newValue)
        //SQL action to overwrite old value with new

    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/Home.fxml"));
        Scene createBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }

    private void loadEditOptions() { //this works but was causing some error, wait till it's moved to book view
//        editOptionsList.removeAll();
//        String editName = "Name";
//        String editAuthor = "Author";
//        String editPublicationDate = "Publication Date";
//        String editPublisher = "Publisher";
//
//        editOptionsList.addAll(editName, editAuthor, editPublicationDate, editPublisher);
//
//        editOptionsList.add("Name");
//        editChoiceValue.getItems().addAll(editOptionsList);

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
