package LibraryApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML private Parent root;
    @FXML private TextField search;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DatabaseHandler.getInstance();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public HomeController() {
    }

    //Create Methods

    @FXML
    public void createBook(ActionEvent event) throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/CreateBook.fxml"));
        Scene createBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }

    @FXML
    public void createEBook(ActionEvent actionEvent) throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/CreateEBook.fxml"));
        Scene createBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }

    @FXML
    public void addUser(ActionEvent actionEvent) throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/CreateUser.fxml"));
        Scene createBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }

    //Read Methods

    @FXML
    public void search() throws IOException { //gets ResultsController and passes search data field to search field in ResultsController then sets a new scene
        String searchString = this.search.getText();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmlFiles/Results.fxml"));

        Parent createBookParent = loader.load();

        Scene createBookScene = new Scene(createBookParent);

        ResultsController newController = loader.getController();

        newController.setSearch(this.search.getText());

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }

    @FXML
    public void showAllBooks(ActionEvent event) throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/AllBooks.fxml"));
        Scene editBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(editBookScene);
        window.show();
    }

    @FXML
    public void showAllEBooks(ActionEvent event) throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/AllEBooks.fxml"));
        Scene editBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(editBookScene);
        window.show();
    }

    public void showAllLibraryItems(ActionEvent actionEvent) throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/AllLibraryItems.fxml"));
        Scene editBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(editBookScene);
        window.show();
    }

    public void showAllUsers(ActionEvent actionEvent) throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/AllUsers.fxml"));
        Scene editBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(editBookScene);
        window.show();
    }

    // update routes to go in book display page
    @FXML
    public void editBook(ActionEvent event) throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/EditBook.fxml"));
        Scene editBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(editBookScene);
        window.show();
    }



    public void editEBook(ActionEvent actionEvent) {
    }


}
