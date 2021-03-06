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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Class that controls the AllBooks page, initializes the data, controls the @FXML fields and actions.
 */

public class AllBooksController implements Initializable {


    @FXML private Parent root;
    @FXML public TableView<BookModel> tableView;
    @FXML public TableColumn<BookModel, String> nameCol;
    @FXML public TableColumn<BookModel, String> authorCol;
    @FXML public TableColumn<BookModel, String> publisherCol;
    @FXML public TableColumn<BookModel, String> topicsCol;
    @FXML public TableColumn<BookModel, Boolean> availabilityCol;
    @FXML public TableColumn viewBookCol;
    @FXML private TextField search;

    public DatabaseHandler handler;
    public ObservableList<BookModel> bookList = FXCollections.observableArrayList();

    /**
     * Method that initialised the database handler and calls loadData.
     * @param url Class {@code URL} represents a Uniform Resource
     * Locator, a pointer to a "resource" on the World
     * Wide Web.
     * @param resourceBundle Resource bundles contain locale-specific objects.  When your program needs a
     * locale-specific resource, a <code>String</code> for example, your program can
     * load it from the resource bundle that is appropriate for the current user's
     * locale.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            handler = DatabaseHandler.getInstance();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        loadData();
    }

    /**
     * Method used to initialize table and fill with data for all books returned from Database
     */

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
                int booleanAvailability = results.getInt("isOnLoan");
                String availability = "";
                if (booleanAvailability == 0) {
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
                            final Button viewBookButton= new Button("View Book");
                            //listener
                            viewBookButton.setOnAction(event -> {
                                BookModel bookModel=getTableView().getItems().get(getIndex());
                                try { //making the call to the viewBook method here when the button is clicked
                                    viewBook(bookModel);
                                } catch (IOException | SQLException e) {
                                    e.printStackTrace();
                                }

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

    // Routes

    /**
     * Route that creates new Results scene and links to the fxml file.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */

    @FXML
    public void search() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmlFiles/Results.fxml"));

        Parent resultsParent = loader.load();

        Scene resultsScene = new Scene(resultsParent);

        ResultsController resultsController = loader.getController();

        resultsController.setSearch(this.search.getText());

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(resultsScene);
        window.show();
    }

    /**
     * Home route which creates new scene and links to the Home fxml file.
     * @param actionEvent An external stimulus from user interface.
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
     * Route to view book which creates a new ViewBook Scene, and passes the ID of the book chosen to the new scene.
     * @param bookModel The Internal class with simple string properties used to represent a Book object.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void viewBook(BookModel bookModel) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmlFiles/ViewBook.fxml"));

        Parent viewBookParent = loader.load();

        Scene viewBookScene = new Scene(viewBookParent);

        BookPageController viewController = loader.getController();

        viewController.viewBook(bookModel.get_id());

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(viewBookScene);
        window.show();
    }


    /**
     * This is the inner class that will display book values in a table.
     */

    public class BookModel {
        private SimpleStringProperty name, author, publisher, topics, availability;
        private int _id;

        /**
         * The Class constructor, it accepts string parameters and parses them into simple strings.
         * @param name The name of the Book.
         * @param author The name of the Author.
         * @param publisher The name of the publisher.
         * @param topics The topics as a coma separated list of strings.
         * @param availability The availability as a String.
         * @param _id The unique ID of the book.
         */

        public BookModel(String name, String author, String publisher, String topics, String availability, int _id) {
            this.name = new SimpleStringProperty(name);
            this.author = new SimpleStringProperty(author);
            this.publisher = new SimpleStringProperty(publisher);
            this.topics = new SimpleStringProperty(topics);
            this.availability = new SimpleStringProperty(availability);
            this._id = _id;
        }

        /**
         * Method to get the Name and return as a String.
         * @return String Name.
         */

        public String getName() {
            return name.get();
        }

        /**
         * Method to get the Author and return as a String.
         * @return String Author
         */

        public String getAuthor() {
            return author.get();
        }

        /**
         * Method to get the Publisher and return as a String.
         * @return String Publisher.
         */

        public String getPublisher() {
            return publisher.get();
        }

        /**
         * Method to get the Topics and return as a String.
         * @return String Topics.
         */

        public String getTopics() {
            return topics.get();
        }

        /**
         * Method to get the availability and return as a String.
         * @return String availability.
         */

        public String isAvailability() {
            return availability.get();
        }

        /**
         * Method to get the ID and return as a String.
         * @return String ID.
         */

        public int get_id() {
            return _id;
        }

        /**
         * Method to set the Name.
         * @param name String Name.
         */

        public void setName(String name) {
            this.name.set(name);
        }

        /**
         * Method to set the Author.
         * @param author String Author
         */

        public void setAuthor(String author) {
            this.author.set(author);
        }

        /**
         * Method to set the Publisher.
         * @param publisher String Publisher.
         */

        public void setPublisher(String publisher) {
            this.publisher.set(publisher);
        }

        /**
         * Method to set the Topics.
         * @param topics String Topics
         */

        public void setTopics(String topics) {
            this.topics.set(topics);
        }

        /**
         * Method to set the Availability.
         * @param availability String availability.
         */

        public void setAvailability(String availability) {
            this.availability.set(availability);
        }

        /**
         * Method to set the ID.
         * @param _id String ID.
         */

        public void set_id(int _id) {
            this._id = _id;
        }
    }

}
