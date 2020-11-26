package LibraryApp;

import LibraryApp.Models.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateUserController implements Initializable {

    @FXML private ChoiceBox<String> userTypeOptions = new ChoiceBox<>();
    @FXML private Parent root;
    @FXML private TextField userTypeSelection;

    @FXML private TextField username;
    @FXML private TextField password;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DatabaseHandler.getInstance();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        loadEditOptions();
    }



    public void createUser(ActionEvent actionEvent) throws SQLException, IOException {
        String userType = getChoice(userTypeOptions);
        String usernameText = username.getText();
        String passwordText = password.getText();


        if(usernameText.isEmpty()||passwordText.isEmpty()|| userType.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please complete all fields");
            alert.showAndWait();
            return;
        }

        boolean userExists = DatabaseHandler.getInstance().checkIfUserExists(usernameText);

        if(userExists) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("User exists already, please choose a different username");
            alert.showAndWait();
            return;

        } else {
            if (userType == "Student") {
                Student user = new Student(usernameText, passwordText);
                String sqlString = "INSERT INTO Users (_userId, userName, password, isAdmin, libraryFees, type)" +
                        "VALUES(" + user.get_userId() + ", '" + user.getName() + "', '" + user.getPassword() + "', " + 0 + ", " + user.getLibraryFees() + ", 'student')";
                DatabaseHandler.getInstance().execAction(sqlString);
                cancel();
            } else if (userType == "Admin") {
                Admin user = new Admin(usernameText, passwordText);
                String sqlString = "INSERT INTO Users (_userId, userName, password, isAdmin, libraryFees, type)" +
                        "VALUES(" + user.get_userId() + ", '" + user.getName() + "', '" + user.getPassword() + "', " + 0 + ", " + user.getLibraryFees() + ", 'admin')";
                DatabaseHandler.getInstance().execAction(sqlString);
                cancel();
            }
        }
    }


    public void cancel() throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/Home.fxml"));
        Scene createBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/Home.fxml"));
        Scene createBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }

    private void loadEditOptions() { //this works but was causing some error, wait till it's moved to book view
        String admin = "Admin";
        String student = "Student";

        userTypeOptions.getItems().addAll(admin, student);
        userTypeOptions.setValue(student);

    }

    private String getChoice(ChoiceBox<String> userTypeOptions) {
        String userType = userTypeOptions.getValue();
        return userType;
    }

}
