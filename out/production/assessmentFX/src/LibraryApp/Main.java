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
//        launch(args);
        System.out.println("Hi there");
        Computer computer = new Computer();
        System.out.println(computer.toString());
        Author author = new Author("Michael Kolling");
        EBook ebook = new EBook("Bluej", author, "2016", "Pearson", "12345");
        Student student = new Student("testUsername", "password");

        PhysicalBook book = new PhysicalBook("Bluej", author, "2016", "Pearson", "12345");
        Library library = new Library("Strathclyde", "8-11", "Glasgow");
        System.out.println(student);
        student.setNotifications("first notification, second not");
        student.printNotifications();
        book.setTopicsCSV("one topic, two topics");
        System.out.println(book.getTopicsAsString());

    }


}
