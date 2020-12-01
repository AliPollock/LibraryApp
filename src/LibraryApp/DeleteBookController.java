package LibraryApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Class that controls the DeleteBook page, initializes the data, controls the @FXML fields and actions.
 */

public class DeleteBookController implements Initializable {

    @FXML private Parent root;

    private int _id;
    public DatabaseHandler handler = DatabaseHandler.getInstance();

    /**
     * Class constructor.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public DeleteBookController() throws SQLException {
    }

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
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Method which is called from other controllers. Initializes ID field.
     * @param _id Unique book ID
     */

    public void loadData(int _id){
        this._id = _id;
    }

    /**
     * Method which removes Book from database.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */

    public void delete() throws IOException {

        String typeCheck ="";
        String sqlTypeCheck = "Select * From PBooks where _id = " + _id;
        ResultSet typeCheckResults = handler.execQuery(sqlTypeCheck);

        try {
            while (typeCheckResults.next()) {
                typeCheck = typeCheckResults.getString("name");
            }
        } catch (SQLException exception) {}

        if(typeCheck != "") { //PBook

            String sqlDeleteBook = "DELETE FROM PBooks WHERE _id = " + this._id;
            boolean bookDeleted = handler.execAction(sqlDeleteBook);
            if (bookDeleted == true) {
                home();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Error Deleting Book");
                alert.show();
            }

        } else {

            String sqlDeleteBook = "DELETE FROM EBooks WHERE _eBookId = " + this._id;
            boolean bookDeleted = handler.execAction(sqlDeleteBook);
            if (bookDeleted == true) {
                home();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Error Deleting EBook");
                alert.show();
            }
        }
    }

    // Routes

    /**
     * Route that creates new EditBookPage scene and links to the fxml file.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */

    public void cancel(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmlFiles/EditBook.fxml"));

        Parent editBookParent = loader.load();

        Scene editBookScene = new Scene(editBookParent);

        EditBookController editController = loader.getController();

        editController.loadData(this._id);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(editBookScene);
        window.show();
    }

    /**
     * Home route which creates new scene and links to the Home fxml file.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */

    public void home() throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/Home.fxml"));
        Scene createBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }
}
