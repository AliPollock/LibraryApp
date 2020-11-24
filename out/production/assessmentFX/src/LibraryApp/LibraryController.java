package LibraryApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LibraryController implements Initializable {

    @FXML private Parent root;
    @FXML private TextField Name;
    @FXML private TextField open;
    @FXML private TextField hours;
    @FXML private TextField Location;
    @FXML private Button cancelButton;
    @FXML private Button okayButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DatabaseHandler.getInstance();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public LibraryController() throws SQLException {
    }




    public void cancel(ActionEvent actionEvent) throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/Home.fxml"));
        Scene createBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }

    public void createLibrary(ActionEvent actionEvent) {
    }


//    @FXML
//    private void createLibrary(ActionEvent event) throws SQLException {
//        String name = Name.getText();
//        String openHours = hours.getText();
//        String location = Location.getText();
//        String isOpenString = open.getText(); //should get rid of this from the constructors once I have time working
//
//        boolean isOpen = false;
//        if (isOpenString == "true"){
//            isOpen = true;
//        } else if (isOpenString == "false"){
//            isOpen = false;
//        }
//
//        if(name.isEmpty()||openHours.isEmpty()||location.isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText(null);
//            alert.setContentText("Please complete all fields");
//            alert.showAndWait();
//            return;
//        }
//
//        Library library1 = new Library(name, isOpen, openHours, location);
//
//        DatabaseHandler.getInstance().addLibrary(name, isOpen, openHours, location);
//        System.out.println(library1 +" added to db");
//    }

}
