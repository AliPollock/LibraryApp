package LibraryApp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main extends Application {

    Stage window;
    Stage searchView, bookView;

    @Override
    public void start(Stage window) throws Exception {

        DatabaseHandler databaseHandler = new DatabaseHandler();
//        databaseHandler.createConnection();
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        window.setTitle("Library Application");
        window.setScene(new Scene(root));
        window.show();
    }

    public static void main(String[] args) {
        launch(args);

    }


}
