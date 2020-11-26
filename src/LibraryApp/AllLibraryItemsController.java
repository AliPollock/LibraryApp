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

public class AllLibraryItemsController implements Initializable {

    ObservableList<LibraryItemModel> bookList = FXCollections.observableArrayList();

    @FXML
    private Parent root;

    @FXML
    public TableView<LibraryItemModel> tableView;
    @FXML
    public TableColumn<LibraryItemModel, String> nameCol;
    @FXML
    public TableColumn<LibraryItemModel, String> authorCol;
    @FXML
    public TableColumn<LibraryItemModel, String> publisherCol;
    @FXML
    public TableColumn<LibraryItemModel, String> topicsCol;
    @FXML
    public TableColumn<LibraryItemModel, Boolean> availabilityCol;
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


    public void initCol() {

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        topicsCol.setCellValueFactory(new PropertyValueFactory<>("topics"));
        availabilityCol.setCellValueFactory(new PropertyValueFactory<>("availability"));

    }

    private void loadData() {
        String sqlBooks = "SELECT * FROM PBooks INNER JOIN Authors ON PBooks.author=Authors._authorId";
        ResultSet results = handler.execQuery(sqlBooks);
        try {
            while (results.next()) {
                String name = results.getString("name");
                String author = results.getString("authorName");
                String publisher = results.getString("publisher");
                String topics = results.getString("topics");
                Boolean booleanAvailability = results.getBoolean("isOnLoan");
                String availability = "";
                if (!booleanAvailability) {
                    availability += "available";
                } else {
                    availability += "On Loan";
                }

                bookList.add(new LibraryItemModel(name, author, publisher, topics, availability));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        String sqlEBooks = "SELECT * FROM EBooks INNER JOIN Authors ON EBooks.author=Authors._authorId";
        ResultSet eResults = handler.execQuery(sqlEBooks);
        try {
            while (eResults.next()) {
                String name = eResults.getString("name");
                String author = eResults.getString("authorName");
                String publisher = eResults.getString("publisher");
                String topics = eResults.getString("topics");

                bookList.add(new LibraryItemModel(name, author, publisher, topics));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        tableView.getItems().setAll(bookList);
    }

    public void home(ActionEvent actionEvent) throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/Home.fxml"));
        Scene createBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }

    /**
     * This is the inner class that will display book values in a table
     **/

    public class LibraryItemModel {
        private SimpleStringProperty name, author, publisher, topics, availability;

        public LibraryItemModel(String name, String author, String publisher, String topics, String availability) {
            this.name = new SimpleStringProperty(name);
            this.author = new SimpleStringProperty(author);
            this.publisher = new SimpleStringProperty(publisher);
            this.topics = new SimpleStringProperty(topics);
            this.availability = new SimpleStringProperty(availability);
        }

        public LibraryItemModel(String name, String author, String publisher, String topics) {
            this.name = new SimpleStringProperty(name);
            this.author = new SimpleStringProperty(author);
            this.publisher = new SimpleStringProperty(publisher);
            this.topics = new SimpleStringProperty(topics);
            this.availability = new SimpleStringProperty("Ebook (View online)");
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

        public String getAvailability() {
            return availability.get();
        }
    }

}