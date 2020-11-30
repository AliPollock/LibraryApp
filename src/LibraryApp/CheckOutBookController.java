package LibraryApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Class that controls the CheckOutBook page, initializes the data, controls the @FXML fields and actions.
 */

public class CheckOutBookController implements Initializable {

    @FXML private Parent root;
    @FXML private int _id;
    @FXML private TextField username;

    DatabaseHandler handler = DatabaseHandler.getInstance();

    /**
     * Class constructor
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public CheckOutBookController() throws SQLException {
    }

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

    public void loadData(int _id) throws IOException, SQLException {
        this._id = _id;
    }

    /**
     * Route that creates new createBookPage scene and links to the fxml file, passing the book ID to the new scene.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void cancel() throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmlFiles/ViewBook.fxml"));

        Parent createBookParent = loader.load();

        Scene createBookScene = new Scene(createBookParent);

        BookPageController newController = loader.getController();

        newController.viewBook(this._id);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }

    /**
     * Route that creates new ViewBookPage scene and links to the fxml file, passing the ID to the new scene.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void cancel(ActionEvent actionEvent) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmlFiles/ViewBook.fxml"));

        Parent viewBookParent = loader.load();

        Scene viewBookScene = new Scene(viewBookParent);

        BookPageController viewController = loader.getController();

        viewController.viewBook(this._id);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(viewBookScene);
        window.show();
    }

    /**
     * Method that checks out book, updates database and calls cancel method to reroute to book page.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */


    public void checkOutBook(ActionEvent actionEvent) throws IOException, SQLException { /// currently broken version trying to incremement timesRead and currentUser

        String sqlUsernameQuery = "SELECT * FROM Users WHERE userName='" + this.username.getText() + "'";
        ResultSet usernameResults = handler.execQuery(sqlUsernameQuery);
        handler.closeDatabase();
        int sqlUsernameId = 0;
        try{
            while (usernameResults.next()) {
                sqlUsernameId += usernameResults.getInt("_userId");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        usernameResults.close();


        if(sqlUsernameId != 0) {

            String sqlTimesReadQuery = "SELECT * FROM PBooks WHERE _id=" + this._id;
            ResultSet timesReadResults = handler.execQuery(sqlTimesReadQuery);
            handler.closeDatabase();

            int sqlTimesRead = 0;
            try{
                while (timesReadResults.next()) {
                    sqlTimesRead = timesReadResults.getInt("timesRead");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            timesReadResults.close();

            sqlTimesRead++;
            String sqlTimesReadAction = "UPDATE PBooks SET timesRead=" + sqlTimesRead + " WHERE _id=" + this._id;
            handler.execAction(sqlTimesReadAction);

            String sqlBooksAction = "UPDATE PBooks SET isOnLoan=" + 1 + " WHERE _id=" + this._id;
            handler.execAction(sqlBooksAction);

            String usernameAction = "UPDATE PBooks SET currentUser=" + sqlUsernameId + " WHERE _id=" + this._id;
            handler.execAction(usernameAction);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setContentText("Book has been checked out by " + username.getText() + ".");
            alert.show();


            ///still need to set due date here ####################################################################################################

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("This username is not on the system, create a new profile or select a valid existing user");
            alert.show();
        }


        cancel();
    }
}
