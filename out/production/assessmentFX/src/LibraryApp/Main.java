package LibraryApp;
import LibraryApp.Models.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    public Stage window;
    public Stage searchView, bookView;

    @Override
    public void start(Stage window) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("fxmlFiles/Home.fxml"));
        window.setTitle("Library Application");
        window.setScene(new Scene(root));
        window.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
        DatabaseHandler.getInstance().openDatabase();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        DatabaseHandler.getInstance().closeDatabase();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
