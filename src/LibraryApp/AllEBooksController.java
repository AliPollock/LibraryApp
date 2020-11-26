package LibraryApp;

import javafx.beans.property.SimpleBooleanProperty;
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

public class AllEBooksController implements Initializable {

    ObservableList<EBookModel> bookList = FXCollections.observableArrayList();
    DatabaseHandler handler;

    @FXML private Parent root;
    @FXML public TableView<EBookModel> tableView;
    @FXML public TableColumn<EBookModel, String> nameCol;
    @FXML public TableColumn<EBookModel, String> authorCol;
    @FXML public TableColumn<EBookModel, String> publisherCol;
    @FXML public TableColumn<EBookModel, String> topicsCol;

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


    public void home() throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/Home.fxml"));
        Scene createBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }

    public void initCol() {


        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        topicsCol.setCellValueFactory(new PropertyValueFactory<>("topics"));

    }

    private void loadData() {
        String sql = "SELECT * FROM EBooks INNER JOIN Authors ON EBooks.author=Authors._authorId";
        ResultSet results = handler.execQuery(sql);
        try {
            while (results.next()) {
                String name = results.getString("name");
                String author = results.getString("authorName");
                String publisher = results.getString("publisher");
                String topics = results.getString("topics");

                bookList.add(new LibraryApp.AllEBooksController.EBookModel(name, author, publisher, topics));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        tableView.getItems().setAll(bookList);
    }


    /** This is the inner class that will display book values in a table **/

    public class EBookModel {
        private SimpleStringProperty name, author, publisher, topics;

        public EBookModel(String name, String author, String publisher, String topics) {
            this.name = new SimpleStringProperty(name);
            this.author = new SimpleStringProperty(author);
            this.publisher = new SimpleStringProperty(publisher);
            this.topics = new SimpleStringProperty(topics);
        }

        public String getName() {
            return name.get();
        }

        public String getAuthor() {
            return author.get();
        }

        public String getPublisher() {
            return publisher.get();
        }

        public String getTopics() {
            return topics.get();
        }

    }


}
