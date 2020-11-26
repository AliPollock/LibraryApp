package LibraryApp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewAuthorController implements Initializable {

    @FXML private Label testLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



    public void viewBook(String name, int id, String author) {
        System.out.println("in here");
        testLabel.setText(name);
        loadData();
    }

    public void loadData() {}
}
