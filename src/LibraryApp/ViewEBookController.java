package LibraryApp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Class that controls the ViewEBook page, initializes the data, controls the @FXML fields and actions.
 */

public class ViewEBookController implements Initializable {

    public ObservableList<ViewAuthorController.LibraryItemModel> bookList = FXCollections.observableArrayList();
    public DatabaseHandler handler;
    private int _eBookId;
    @FXML private Parent root;
    private String computerNumber;

    @FXML public Button computer1;
    @FXML public Button computer2;
    @FXML public Button computer3;
    @FXML public Button computer4;
    @FXML public Button computer5;
    @FXML public Button computer6;
    @FXML public Button computer7;
    @FXML public Button computer8;
    @FXML public Button computer9;
    @FXML public Button computer10;
    @FXML public Button computer11;
    @FXML public Button computer12;
    @FXML public Button computer13;
    @FXML public Button computer14;
    @FXML public Button computer15;
    @FXML public Button computer16;
    @FXML public Button computer17;
    @FXML public Button computer18;
    @FXML public Button computer19;
    @FXML public Button computer20;

    /**
     * Method which is called from other controllers. Initializes ID field
     * @param _eBookId Unique book ID
     */

    public void loadData(int _eBookId) {
        this._eBookId = _eBookId;
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
            handler = DatabaseHandler.getInstance();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Method which responds to a button click event and updates database and calls viewEBookOnComputer.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void computerPress1(ActionEvent actionEvent) throws IOException, SQLException {
        computerNumber = "computer1";
        String sqlString = "UPDATE Computers SET isInUse = 1 WHERE computerName='computer1'";
        handler.execAction(sqlString);
        String sqlEBook = "UPDATE EBooks SET currentUsers = 1 WHERE _eBookId=" + _eBookId;
        handler.execAction(sqlEBook);
        viewEBookOnComputer();
    }

    /**
     * Method which responds to a button click event and updates database and calls viewEBookOnComputer.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void computerPress2(ActionEvent actionEvent) throws IOException, SQLException {
        computerNumber = "computer2";
        String sqlString = "UPDATE Computers SET isInUse = 1 WHERE computerName='computer2'";
        handler.execAction(sqlString);
        String sqlEBook = "UPDATE EBooks SET currentUsers = 1 WHERE _eBookId=" + _eBookId;
        handler.execAction(sqlEBook);
        viewEBookOnComputer();
    }

    /**
     * Method which responds to a button click event and updates database and calls viewEBookOnComputer.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void computerPress3(ActionEvent actionEvent) throws IOException, SQLException {
        computerNumber = "computer3";
        String sqlString = "UPDATE Computers SET isInUse = 1 WHERE computerName='computer3'";
        handler.execAction(sqlString);
        String sqlEBook = "UPDATE EBooks SET currentUsers = 1 WHERE _eBookId=" + _eBookId;
        handler.execAction(sqlEBook);
        viewEBookOnComputer();
    }

    /**
     * Method which responds to a button click event and updates database and calls viewEBookOnComputer.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void computerPress4(ActionEvent actionEvent) throws IOException, SQLException {
        computerNumber = "computer4";
        String sqlString = "UPDATE Computers SET isInUse = 1 WHERE computerName='computer4'";
        handler.execAction(sqlString);
        String sqlEBook = "UPDATE EBooks SET currentUsers = 1 WHERE _eBookId=" + _eBookId;
        handler.execAction(sqlEBook);
        viewEBookOnComputer();
    }

    /**
     * Method which responds to a button click event and updates database and calls viewEBookOnComputer.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void computerPress5(ActionEvent actionEvent) throws IOException, SQLException {
        computerNumber = "computer5";
        String sqlString = "UPDATE Computers SET isInUse = 1 WHERE computerName='computer5'";
        handler.execAction(sqlString);
        String sqlEBook = "UPDATE EBooks SET currentUsers = 1 WHERE _eBookId=" + _eBookId;
        handler.execAction(sqlEBook);
        viewEBookOnComputer();
    }

    /**
     * Method which responds to a button click event and updates database and calls viewEBookOnComputer.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void computerPress6(ActionEvent actionEvent) throws IOException, SQLException {
        computerNumber = "computer6";
        String sqlString = "UPDATE Computers SET isInUse = 1 WHERE computerName='computer6'";
        handler.execAction(sqlString);
        String sqlEBook = "UPDATE EBooks SET currentUsers = 1 WHERE _eBookId=" + _eBookId;
        handler.execAction(sqlEBook);
        viewEBookOnComputer();
    }

    /**
     * Method which responds to a button click event and updates database and calls viewEBookOnComputer.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void computerPress7(ActionEvent actionEvent) throws IOException, SQLException {
        computerNumber = "computer7";
        String sqlString = "UPDATE Computers SET isInUse = 1 WHERE computerName='computer7'";
        handler.execAction(sqlString);
        String sqlEBook = "UPDATE EBooks SET currentUsers = 1 WHERE _eBookId=" + _eBookId;
        handler.execAction(sqlEBook);
        viewEBookOnComputer();
    }

    /**
     * Method which responds to a button click event and updates database and calls viewEBookOnComputer.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void computerPress8(ActionEvent actionEvent) throws IOException, SQLException {
        computerNumber = "computer8";
        String sqlString = "UPDATE Computers SET isInUse = 1 WHERE computerName='computer8'";
        handler.execAction(sqlString);
        String sqlEBook = "UPDATE EBooks SET currentUsers = 1 WHERE _eBookId=" + _eBookId;
        handler.execAction(sqlEBook);
        viewEBookOnComputer();
    }

    /**
     * Method which responds to a button click event and updates database and calls viewEBookOnComputer.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void computerPress9(ActionEvent actionEvent) throws IOException, SQLException {
        computerNumber = "computer9";
        String sqlString = "UPDATE Computers SET isInUse = 1 WHERE computerName='computer9'";
        handler.execAction(sqlString);
        String sqlEBook = "UPDATE EBooks SET currentUsers = 1 WHERE _eBookId=" + _eBookId;
        handler.execAction(sqlEBook);
        viewEBookOnComputer();
    }

    /**
     * Method which responds to a button click event and updates database and calls viewEBookOnComputer.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void computerPress10(ActionEvent actionEvent) throws IOException, SQLException {
        computerNumber = "computer10";
        String sqlString = "UPDATE Computers SET isInUse = 1 WHERE computerName='computer10'";
        handler.execAction(sqlString);
        String sqlEBook = "UPDATE EBooks SET currentUsers = 1 WHERE _eBookId=" + _eBookId;
        handler.execAction(sqlEBook);
        viewEBookOnComputer();
    }

    /**
     * Method which responds to a button click event and updates database and calls viewEBookOnComputer.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void computerPress11(ActionEvent actionEvent) throws IOException, SQLException {
        computerNumber = "computer11";
        String sqlString = "UPDATE Computers SET isInUse = 1 WHERE computerName='computer11'";
        handler.execAction(sqlString);
        String sqlEBook = "UPDATE EBooks SET currentUsers = 1 WHERE _eBookId=" + _eBookId;
        handler.execAction(sqlEBook);
        viewEBookOnComputer();
    }

    /**
     * Method which responds to a button click event and updates database and calls viewEBookOnComputer.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void computerPress12(ActionEvent actionEvent) throws IOException, SQLException {
        computerNumber = "computer12";
        String sqlString = "UPDATE Computers SET isInUse = 1 WHERE computerName='computer12'";
        handler.execAction(sqlString);
        String sqlEBook = "UPDATE EBooks SET currentUsers = 1 WHERE _eBookId=" + _eBookId;
        handler.execAction(sqlEBook);
        viewEBookOnComputer();
    }

    /**
     * Method which responds to a button click event and updates database and calls viewEBookOnComputer.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void computerPress13(ActionEvent actionEvent) throws IOException, SQLException {
        computerNumber = "computer13";
        String sqlString = "UPDATE Computers SET isInUse = 1 WHERE computerName='computer13'";
        handler.execAction(sqlString);
        String sqlEBook = "UPDATE EBooks SET currentUsers = 1 WHERE _eBookId=" + _eBookId;
        handler.execAction(sqlEBook);
        viewEBookOnComputer();
    }

    /**
     * Method which responds to a button click event and updates database and calls viewEBookOnComputer.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void computerPress14(ActionEvent actionEvent) throws IOException, SQLException {
        computerNumber = "computer14";
        String sqlString = "UPDATE Computers SET isInUse = 1 WHERE computerName='computer14'";
        handler.execAction(sqlString);
        String sqlEBook = "UPDATE EBooks SET currentUsers = 1 WHERE _eBookId=" + _eBookId;
        handler.execAction(sqlEBook);
        viewEBookOnComputer();
    }

    /**
     * Method which responds to a button click event and updates database and calls viewEBookOnComputer.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void computerPress15(ActionEvent actionEvent) throws IOException, SQLException {
        computerNumber = "computer15";
        String sqlString = "UPDATE Computers SET isInUse = 1 WHERE computerName='computer15'";
        handler.execAction(sqlString);
        String sqlEBook = "UPDATE EBooks SET currentUsers = 1 WHERE _eBookId=" + _eBookId;
        handler.execAction(sqlEBook);
        viewEBookOnComputer();
    }

    /**
     * Method which responds to a button click event and updates database and calls viewEBookOnComputer.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void computerPress16(ActionEvent actionEvent) throws IOException, SQLException {
        computerNumber = "computer16";
        String sqlString = "UPDATE Computers SET isInUse = 1 WHERE computerName='computer16'";
        handler.execAction(sqlString);
        String sqlEBook = "UPDATE EBooks SET currentUsers = 1 WHERE _eBookId=" + _eBookId;
        handler.execAction(sqlEBook);
        viewEBookOnComputer();
    }

    /**
     * Method which responds to a button click event and updates database and calls viewEBookOnComputer.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void computerPress17(ActionEvent actionEvent) throws IOException, SQLException {
        computerNumber = "computer17";
        String sqlString = "UPDATE Computers SET isInUse = 1 WHERE computerName='computer17'";
        handler.execAction(sqlString);
        String sqlEBook = "UPDATE EBooks SET currentUsers = 1 WHERE _eBookId=" + _eBookId;
        handler.execAction(sqlEBook);
        viewEBookOnComputer();
    }

    /**
     * Method which responds to a button click event and updates database and calls viewEBookOnComputer.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void computerPress18(ActionEvent actionEvent) throws IOException, SQLException {
        computerNumber = "computer18";
        String sqlString = "UPDATE Computers SET isInUse = 1 WHERE computerName='computer18'";
        handler.execAction(sqlString);
        String sqlEBook = "UPDATE EBooks SET currentUsers = 1 WHERE _eBookId=" + _eBookId;
        handler.execAction(sqlEBook);
        viewEBookOnComputer();
    }

    /**
     * Method which responds to a button click event and updates database and calls viewEBookOnComputer.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void computerPress19(ActionEvent actionEvent) throws IOException, SQLException {
        computerNumber = "computer19";
        String sqlString = "UPDATE Computers SET isInUse = 1 WHERE computerName='computer19'";
        handler.execAction(sqlString);
        String sqlEBook = "UPDATE EBooks SET currentUsers = 1 WHERE _eBookId=" + _eBookId;
        handler.execAction(sqlEBook);
        viewEBookOnComputer();
    }

    /**
     * Method which responds to a button click event and updates database and calls viewEBookOnComputer.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void computerPress20(ActionEvent actionEvent) throws IOException, SQLException {
        computerNumber = "computer20";
        String sqlString = "UPDATE Computers SET isInUse = 1 WHERE computerName='computer20'";
        handler.execAction(sqlString);
        String sqlEBook = "UPDATE EBooks SET currentUsers = 1 WHERE _eBookId=" + _eBookId;
        handler.execAction(sqlEBook);
        viewEBookOnComputer();
    }

    /**
     * Method which creates the computerScreen scene and links to the fxml file, passiong the EBook ID and the computer number to the new scene.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */

    public void viewEBookOnComputer() throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmlFiles/ComputerScreen.fxml"));

        Parent computerScreenParent = loader.load();

        Scene computerScreenScene = new Scene(computerScreenParent);

        ComputerScreenController computerScreenController = loader.getController();

        computerScreenController.viewEBook(_eBookId, computerNumber);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(computerScreenScene);
        window.show();
    }

}
