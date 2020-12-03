package LibraryApp;

import LibraryApp.Models.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Class that controls the AllUsers page, initializes the data, controls the @FXML fields and actions.
 */

public class AllUsersController implements Initializable {

    @FXML private Parent root;
    @FXML public TableView<UserModel> tableView;
    @FXML public TableColumn<UserModel, String> nameCol;
    @FXML public TableColumn<UserModel, String> typeCol;
    @FXML public TableColumn viewUserCol;

    public ObservableList<UserModel> userList = FXCollections.observableArrayList();
    public DatabaseHandler handler;

    /**
     * Method that initialised the database handler and calls loadData and initCol.
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
        loadData();
    }

    /**
     * Method used to initialize table and fill with data for all users returned from Database.
     */

    private void loadData() {
        String sql = "SELECT * FROM Users";
        ResultSet results = handler.execQuery(sql);
        try {
            while (results.next()) {
                String username = results.getString("username");
                String type = results.getString("type");
                int _id = results.getInt("_userId");

                UserModel userInstance = new UserModel(username, type, _id);
                userList.add(userInstance);
            }

            nameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

            // creating cell factory to create button in each populated row
            Callback<TableColumn<UserModel, String>, TableCell<UserModel, String>> cellFactory =(param) -> {

                // make cell for holding button
                final TableCell<UserModel, String> cell = new TableCell<UserModel, String>(){

                    @Override //override cell updateItem method
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        //make sure cell is only created on populated rows
                        if(empty) {
                            setGraphic(null);
                        } else {
                            final Button viewUserButton= new Button("View User");
                            //listener
                            viewUserButton.setOnAction(event -> {
                                UserModel userModel=getTableView().getItems().get(getIndex());
                                try { //making the call to the viewBook method here when the button is clicked
                                    viewUser(userModel);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            });

                            //now set button to cell
                            setGraphic(viewUserButton);
                        }
                        setText(null);
                    }

                };
                return cell;
            };

            viewUserCol.setCellFactory(cellFactory);
            tableView.getItems().setAll(userList);

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


    /**
     * Home route which creates new scene and links to the Home fxml file
     * @param actionEvent responds to an Action event
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */

    public void home(ActionEvent actionEvent) throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/Home.fxml"));
        Scene createBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }

    /**
     * Route to view book which creates a new ViewBook Scene, and passes the ID of the book chosen to the new scene.
     * @param userModel The internal class with simple string properties used to represent a User object
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */


    public void viewUser(UserModel userModel) throws IOException {
        String searchString = userModel.getUsername();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmlFiles/ViewUser.fxml"));

        Parent viewUserParent = loader.load();

        Scene viewUserScene = new Scene(viewUserParent);

       ViewUserController viewUserController = loader.getController();

        viewUserController.viewUser(userModel.get_id());

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(viewUserScene);
        window.show();
    }

    /**
     * This is the inner class that will display book values in a table
     */

    public class UserModel {
        private SimpleStringProperty username, type;
        private int _id;

        /**
         * The Class constructor, it accepts string parameters and parses them into simple strings.
         * @param username String username.
         * @param type String type of resource i.e. Book or EBook.
         */

        public UserModel(String username, String type, int _id) {
            this.username = new SimpleStringProperty(username);
            this.type = new SimpleStringProperty(type);
            this._id = _id;
        }

        /**
         * Method to get the Username.
         * @return String username.
         */

        public String getUsername() {
            return username.get();
        }

        /**
         * Method to get the type.
         * @return String type.
         */

        public String getType() {
            return type.get();
        }

        /**
         * Method to get the ID and return as a String.
         * @return String ID.
         */

        public int get_id() {
            return _id;
        }

        /**
         * Method to set the Username.
         * @param username String Username.
         */


        public void setUsername(String username) {
            this.username.set(username);
        }

        /**
         * Method to set the type.
         * @param type String type.
         */


        public void setType(String type) {
            this.type.set(type);
        }

        /**
         * Method to set the ID.
         * @param _id String ID.
         */

        public void set_id(int _id) {
            this._id = _id;
        }
    }
}

