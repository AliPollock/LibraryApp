package LibraryApp;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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

    ObservableList<UserModel> bookList = FXCollections.observableArrayList();
    DatabaseHandler handler;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            handler = DatabaseHandler.getInstance();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        initCol();
        loadData();
    }

    /**
     * This method Initialised the columns and assigns properties to each one.
     */

    public void initCol() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
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
                bookList.add(new UserModel(username, type));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        tableView.getItems().setAll(bookList);
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
     * This is the inner class that will display book values in a table
     */

    public class UserModel {
        private SimpleStringProperty username, type;

        /**
         * The Class constructor, it accepts string parameters and parses them into simple strings.
         * @param username
         * @param type
         */

        public UserModel(String username, String type) {
            this.username = new SimpleStringProperty(username);
            this.type = new SimpleStringProperty(type);
        }

        public String getUsername() {
            return username.get();
        }

        public String getType() {
            return type.get();
        }

        public void setUsername(String username) {
            this.username.set(username);
        }

        public void setType(String type) {
            this.type.set(type);
        }
    }
}

