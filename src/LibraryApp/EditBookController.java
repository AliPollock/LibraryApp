package LibraryApp;

import LibraryApp.Models.Author;
import LibraryApp.Models.PhysicalBook;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Class that controls the EditBook page, initializes the data, controls the @FXML fields and actions.
 */

public class EditBookController implements Initializable {

    @FXML private Parent root;
    @FXML private int _id;
    @FXML private ComboBox<String> editChoiceValue;
    @FXML private TextField newPropertyValue;
    @FXML private Label currentValue;

    public DatabaseHandler handler = DatabaseHandler.getInstance();
    private String type;

    /**
     * Class constructor.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public EditBookController() throws SQLException {
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
     * Method loads the options into the combo box.
     */

    public void loadComboBoxData() {
        loadData(this._id);

    }

    /**
     * Method which is called from other controllers, sets event handler on comboBox and makes Label dynamic.
     * @param _id Unique book ID
     */

   public void loadData(int _id) {
        this._id=_id;

       ///checking whether id corresponds to an EBook or a PBook
       String typeCheck ="";
       String sqlTypeCheck = "Select * From PBooks where _id = " + _id;
       ResultSet typeCheckResults = handler.execQuery(sqlTypeCheck);

       try {
           while (typeCheckResults.next()) {
               typeCheck = typeCheckResults.getString("name");
           }
       } catch (SQLException exception) {}

       if(typeCheck != "") { //PBook


           editChoiceValue.getItems().clear();
           editChoiceValue.getItems().addAll("Topics", "Bio", "Overdue Charge", "Damages");

           String sqlBooks = "SELECT * FROM PBooks INNER JOIN Authors ON PBooks.author=Authors._authorId WHERE _id=" + _id;
           ResultSet results = handler.execQuery(sqlBooks);
           try {
               while (results.next()) {
                   String name = results.getString("name");
                   String authorName = results.getString("authorName");
                   String publicationDate = results.getString("publicationDate");
                   String publisher = results.getString("publisher");
                   String isbn = results.getString("isbn");
                   String topics = results.getString("topics");
                   String bio = results.getString("bio");
                   String damages = results.getString("damages");
                   double overDueCharge = results.getDouble("overDueCharge");

                   Author authorInstance = handler.checkIfAuthorExists(authorName);
                   PhysicalBook bookInstance = new PhysicalBook(name, authorInstance, publicationDate, publisher, isbn);
                   bookInstance.setBio(bio);
                   bookInstance.setTopicsCSV(topics);
                   bookInstance.setDamages(damages);
                   bookInstance.setOverDueCharge(overDueCharge);


                   editChoiceValue.setOnAction( e -> {
                       if (editChoiceValue.getValue() == "Bio") { //Bio
                            currentValue.setText(bookInstance.getBio());
                       } else if (editChoiceValue.getValue() == "Topics") { //topics
                           currentValue.setText(bookInstance.getTopicsAsString());
                       } else if (editChoiceValue.getValue() == "Damages") { //damages
                          currentValue.setText(bookInstance.getDamages());
                       } else if (editChoiceValue.getValue() == "Overdue Charge") { //overDueCharge
                           currentValue.setText(Double.toString(bookInstance.getOverDueCharge()));
                       }
                   });




               }
           } catch (SQLException exception) {
               exception.printStackTrace();
           }



       } else { //EBook


           editChoiceValue.getItems().addAll("Topics", "Bio");

           String sqlBooks = "SELECT * FROM EBooks INNER JOIN Authors ON EBooks.author=Authors._authorId WHERE _eBookId=" + _id;
           ResultSet results = handler.execQuery(sqlBooks);
           try {
               while (results.next()) {
                   String name = results.getString("name");
                   String authorName = results.getString("authorName");
                   String publicationDate = results.getString("publicationDate");
                   String publisher = results.getString("publisher");
                   String isbn = results.getString("isbn");
                   String topics = results.getString("topics");
                   String bio = results.getString("bio");

                   Author authorInstance = handler.checkIfAuthorExists(authorName);
                   PhysicalBook bookInstance = new PhysicalBook(name, authorInstance, publicationDate, publisher, isbn);
                   bookInstance.setBio(bio);
                   bookInstance.setTopicsCSV(topics);



                   editChoiceValue.setOnAction( e -> {
                       if (editChoiceValue.getValue() == "Bio") { //Bio
                           currentValue.setText(bookInstance.getBio());
                       } else if (editChoiceValue.getValue() == "Topics") { //topics
                           currentValue.setText(bookInstance.getTopicsAsString());
                       }
                   });


               }
           } catch (SQLException exception) {
               exception.printStackTrace();
           }

       }

   }

   //Page Actions

    /**
     * This method gets the choice from the choice box on submission.
     * @param userTypeOptions The ComboBox.
     * @return String indicating the choice extracted.
     */

   public String getChoice(ChoiceBox<String> userTypeOptions) {
       String userType = userTypeOptions.getValue();
       return userType;
   }


    /**
     * This method updates the database using the data collected from fxml.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void editBook(ActionEvent actionEvent) throws IOException, SQLException {
        String propertyValue = newPropertyValue.getText(); //textField output
        String propertyChoice = editChoiceValue.getValue(); //comboBox output


        ///checking whether id corresponds to an EBook or a PBook
        String typeCheck ="";
        String sqlTypeCheck = "Select * From PBooks where _id = " + _id;
        ResultSet typeCheckResults = handler.execQuery(sqlTypeCheck);

        try {
            while (typeCheckResults.next()) {
                typeCheck = typeCheckResults.getString("name");
            }
        } catch (SQLException exception) {}

        if(typeCheck != "") { //PBook

            if(propertyChoice == "Bio") {
                String sqlString = "UPDATE PBooks SET bio = '" + propertyValue + "' WHERE _id=" + this._id;
                handler.execAction(sqlString);

            } else if (propertyChoice == "Damages") {
                String sqlString = "UPDATE PBooks SET damages = '" + propertyValue + "' WHERE _id=" + this._id;
                handler.execAction(sqlString);

            } else if (propertyChoice == "Topics") {
                String sqlString = "UPDATE PBooks SET topics = '" + propertyValue + "' WHERE _id=" + this._id;
                handler.execAction(sqlString);

            } else { //overdue charge
                String sqlString = "UPDATE PBooks SET overDueCharge = '" + propertyValue + "' WHERE _id=" + this._id;
                handler.execAction(sqlString);
            }

        } else { //EBook

            if(propertyChoice == "Bio") {
                String sqlString = "UPDATE EBooks SET bio = '" + propertyValue + "' WHERE _eBookId=" + this._id;
                handler.execAction(sqlString);

            } else if (propertyChoice == "Damages") {
                String sqlString = "UPDATE EBooks SET damages = '" + propertyValue + "' WHERE _eBookId=" + this._id;
                handler.execAction(sqlString);

            } else if (propertyChoice == "Topics") {
                String sqlString = "UPDATE EBooks SET topics = '" + propertyValue + "' WHERE _eBookId=" + this._id;
                handler.execAction(sqlString);

            } else { //overdue charge
                String sqlString = "UPDATE EBooks SET overDueCharge = '" + propertyValue + "' WHERE _eBookId=" + this._id;
                handler.execAction(sqlString);
            }
        }
        cancel();
    }



    //Routes

    /**
     * Route that creates new ViewBookPage scene and links to the fxml file.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void cancel() throws IOException, SQLException {
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
     * Route that creates new ViewBookPage scene and links to the fxml file.
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
     * Method creates a new DeleteBook scene and links to the fxml, passing the book ID to the new scene.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */

    public void deleteBook(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmlFiles/DeleteBook.fxml"));

        Parent deleteBookParent = loader.load();

        Scene deleteBookScene = new Scene(deleteBookParent);

        DeleteBookController deleteController = loader.getController();

        deleteController.loadData(this._id);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(deleteBookScene);
        window.show();
    }
}
