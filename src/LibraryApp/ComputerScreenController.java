package LibraryApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ComputerScreenController implements Initializable {

    @FXML private Parent root;
    @FXML ImageView imageView;
    @FXML Label computerNumber;
    public DatabaseHandler handler;
    public int _eBookId;

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
     * Method which is called from other controllers. Initializes ID field and the computerNumber Label then sets an image to the fxml..
     * @param _eBookId The unique ID of the EBook.
     * @param computerName The name of the computer.
     */

    public void viewEBook(int _eBookId, String computerName){
        this._eBookId=_eBookId;
        computerNumber.setText(computerName);

        Image image = new Image("Desktop.PNG");
        imageView.setImage(image);
    }

    /**
     * Home route which creates new scene and links to the Home fxml file.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */

    public void home(ActionEvent actionEvent) throws IOException {

        String sqlEBook = "UPDATE EBooks SET currentUsers = 0 WHERE _eBookId=" + _eBookId;
        handler.execAction(sqlEBook);
        String sqlComputer = "UPDATE Computers SET isInUse = 0 WHERE computerName='" + computerNumber.getText() + "'";
        handler.execAction(sqlComputer);

        Parent createBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/Home.fxml"));
        Scene createBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }


}
