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

/**
 * Class that controls the Home page, initializes the data, controls the @FXML fields and actions.
 */

public class HomeController implements Initializable {

    @FXML private Parent root;
    @FXML private TextField search;

    /**
     * Method that initialised the database handler.
     * @param url Class {@code URL} represents a Uniform Resource
     * Locator, a pointer to a "resource" on the World
     * Wide Web.
     * @param resourceBundle Resource bundles contain locale-specific objects.  When your program needs a
     * locale-specific resource, a <code>String</code> for example, your program can
     * load it from the resource bundle that is appropriate for the current user's
     * locale.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DatabaseHandler.getInstance();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Class constructor
     */

    public HomeController() {
    }

    //Create Routes

    /**
     * Route that creates new CreateBook scene and links to the fxml file.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */

    @FXML
    public void createBook(ActionEvent actionEvent) throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/CreateBook.fxml"));
        Scene createBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }

    /**
     * Route that creates new CreateEBook scene and links to the fxml file.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */

    @FXML
    public void createEBook(ActionEvent actionEvent) throws IOException {
        Parent createEBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/CreateEBook.fxml"));
        Scene createEBookScene = new Scene(createEBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createEBookScene);
        window.show();
    }

    /**
     * Route that creates new CreateUser scene and links to the fxml file.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */

    @FXML
    public void addUser(ActionEvent actionEvent) throws IOException {
        Parent createUserParent = FXMLLoader.load(getClass().getResource("fxmlFiles/CreateUser.fxml"));
        Scene createUserScene = new Scene(createUserParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createUserScene);
        window.show();
    }

    //Read Routes

    /**
     * Route that creates new Results scene and links to the fxml file.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */

    @FXML
    public void search() throws IOException {
        String searchString = this.search.getText();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmlFiles/Results.fxml"));

        Parent resultsParent = loader.load();

        Scene resultsScene = new Scene(resultsParent);

        ResultsController resultsController = loader.getController();

        resultsController.setSearch(this.search.getText());

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(resultsScene);
        window.show();
    }

    /**
     * Route that creates new AllBooks scene and links to the fxml file.
     * @param actionEvent responds to an Action event.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */

    @FXML
    public void showAllBooks(ActionEvent actionEvent) throws IOException {
        Parent allBooksParent = FXMLLoader.load(getClass().getResource("fxmlFiles/AllBooks.fxml"));
        Scene allBooksScene = new Scene(allBooksParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(allBooksScene);
        window.show();
    }

    /**
     * Route that creates new AllEBooks scene and links to the fxml file.
     * @param actionEvent responds to an Action event.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */

    @FXML
    public void showAllEBooks(ActionEvent actionEvent) throws IOException {
        Parent allEBooksParent = FXMLLoader.load(getClass().getResource("fxmlFiles/AllEBooks.fxml"));
        Scene allEBooksScene = new Scene(allEBooksParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(allEBooksScene);
        window.show();
    }

    /**
     * Route that creates new AllLibraryItems scene and links to the fxml file.
     * @param actionEvent responds to an Action event.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */

    public void showAllLibraryItems(ActionEvent actionEvent) throws IOException {
        Parent allLibraryItemsParent = FXMLLoader.load(getClass().getResource("fxmlFiles/AllLibraryItems.fxml"));
        Scene allLibraryItemsScene = new Scene(allLibraryItemsParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(allLibraryItemsScene);
        window.show();
    }

    /**
     * Route that creates new AllUsers scene and links to the fxml file.
     * @param actionEvent responds to an Action event.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */

    public void showAllUsers(ActionEvent actionEvent) throws IOException {
        Parent allUsersParent = FXMLLoader.load(getClass().getResource("fxmlFiles/AllUsers.fxml"));
        Scene allUsersScene = new Scene(allUsersParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(allUsersScene);
        window.show();
    }

    /**
     * Route that creates new EditBook scene and links to the fxml file.
     * @param actionEvent responds to an Action event.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */


    @FXML
    public void editBook(ActionEvent actionEvent) throws IOException {
        Parent editBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/EditBook.fxml"));
        Scene editBookScene = new Scene(editBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(editBookScene);
        window.show();
    }

    /**
     * Route to editEBook.
     * @param actionEvent responds to an Action event.
     */

    //**********************delete this method and fxml on home page.

    public void editEBook(ActionEvent actionEvent) {
    }
}
