package LibraryApp;

import LibraryApp.Models.Author;
import LibraryApp.Models.PhysicalBook;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AllBooksController implements Initializable {

    ObservableList<BookModel> bookList = FXCollections.observableArrayList();

    @FXML private Parent root;

    @FXML public TableView<BookModel> tableView;
    @FXML public TableColumn<BookModel, String> nameCol;
    @FXML public TableColumn<BookModel, String> authorCol;
    @FXML public TableColumn<BookModel, String> publisherCol;
    @FXML public TableColumn<BookModel, String> topicsCol;
    @FXML public TableColumn<BookModel, Boolean> availabilityCol;
    @FXML public TableColumn viewBookCol;
    DatabaseHandler handler;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            handler = DatabaseHandler.getInstance();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        loadData();
    }



    private void loadData() {
        String sql = "SELECT * FROM PBooks INNER JOIN Authors ON PBooks.author=Authors._authorId";
        ResultSet results = handler.execQuery(sql);
        try {
            while (results.next()) {
                String name = results.getString("name");
                String author = results.getString("authorName");
                String publisher = results.getString("publisher");
                String topics = results.getString("topics");
                int _id = results.getInt("_id");
                Boolean booleanAvailability = results.getBoolean("isOnLoan");
                String availability = "";
                if(!booleanAvailability) {
                    availability += "available";
                } else {
                    availability += "On Loan";
                }

                BookModel bookModelInstance = new BookModel(name, author, publisher, topics, availability, _id);
                bookList.add(bookModelInstance);
            }

            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
            publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
            topicsCol.setCellValueFactory(new PropertyValueFactory<>("topics"));
            availabilityCol.setCellValueFactory(new PropertyValueFactory<>("availability"));

            // creating cell factory to create button in each populated row
            Callback<TableColumn<BookModel, String>, TableCell<BookModel, String>> cellFactory =(param) -> {

                // make cell for holding button
                final TableCell<BookModel, String> cell = new TableCell<BookModel, String>(){

                    @Override //override cell updateItem method
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        //make sure cell is only created on populated rows
                        if(empty) {
                            setGraphic(null);
                        } else {
                            final Button viewBookButton= new Button("viewBookButton");
                            //listener
                            viewBookButton.setOnAction(event -> {
                                BookModel bookModel=getTableView().getItems().get(getIndex());
                                try { //making the call to the viewBook method here when the button is clicked
                                    viewBook(bookModel);
                                } catch (IOException | SQLException e) {
                                    e.printStackTrace();
                                }
//                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                                alert.setContentText("You have clicked \n " + bookModel.getName() + ", author: " + bookModel.getAuthor());
//                                alert.show();

                            });

                            //now set button to cell
                            setGraphic(viewBookButton);
                        }
                        setText(null);
                    }

                };
                return cell;
            };

            //set data into table view
            viewBookCol.setCellFactory(cellFactory);
            tableView.getItems().setAll(bookList);

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    // routes

    public void home(ActionEvent actionEvent) throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/Home.fxml"));
        Scene createBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }


    public void viewBook(BookModel bookModel) throws IOException, SQLException { //think for this I may need the book ID which means I'll have to edit the book model so that it contains an id.
        String searchString = bookModel.getName();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmlFiles/ViewBook.fxml"));

        Parent createBookParent = loader.load();

        Scene createBookScene = new Scene(createBookParent);

        BookPageController newController = loader.getController();

        newController.viewBook(bookModel.getName(), bookModel.get_id(), bookModel.getAuthor());

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }


    /** This is the inner class that will display book values in a table **/

    public class BookModel {
        private SimpleStringProperty name, author, publisher, topics, availability;
        private int _id;

        public BookModel(String name, String author, String publisher, String topics, String availability, int _id) {
            this.name = new SimpleStringProperty(name);
            this.author = new SimpleStringProperty(author);
            this.publisher = new SimpleStringProperty(publisher);
            this.topics = new SimpleStringProperty(topics);
            this.availability = new SimpleStringProperty(availability);
            this._id = _id;
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


        public String isAvailability() {
            return availability.get();
        }

        public int get_id() {
            return _id;
        }

        public void set_id(int _id) {
            this._id = _id;
        }
    }

}
