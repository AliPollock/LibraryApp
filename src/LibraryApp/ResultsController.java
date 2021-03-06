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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Class that controls the DeleteBook page, initializes the data, controls the @FXML fields and actions.
 */

public class ResultsController implements Initializable {


    @FXML private Label search;
    @FXML private Parent root;
    @FXML public TableView<LibraryItemModel> tableView;
    @FXML public TableColumn<LibraryItemModel, String> nameCol;
    @FXML public TableColumn<LibraryItemModel, String> authorCol;
    @FXML public TableColumn<LibraryItemModel, String> publisherCol;
    @FXML public TableColumn<LibraryItemModel, String> topicsCol;
    @FXML public TableColumn<LibraryItemModel, Boolean> availabilityCol;
    @FXML public TableColumn viewBookCol;
    @FXML private TextField newSearch;

    public ObservableList<LibraryItemModel> bookList = FXCollections.observableArrayList();
    public DatabaseHandler handler;

    /**
     * Method that initialised the database handler.
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
    }

    /**
     * Method which is called from other controllers. Sets the Search label and calls the loadData function.
     * @param search String search.
     */

    public void setSearch(String search){
        this.search.setText(search);

        loadData();
    }

    /**
     * Home route which creates new scene and links to the Home fxml file
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */

    public void home() throws IOException {
        Parent createBookParent = FXMLLoader.load(getClass().getResource("fxmlFiles/Home.fxml"));
        Scene createBookScene = new Scene(createBookParent);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }

    /**
     * Method which searches the database for search term.
     * Initializes the table and fills it with data for books returned from Database.
     */

    private void loadData() {
        String[] columns = {"name", "author", "topics", "bio", "isbn", "publisher", "authorName"};
        for (String columnName: columns) {

            String sqlBooks = "SELECT * FROM PBooks INNER JOIN Authors ON PBooks.author=Authors._authorId WHERE " + columnName + " LIKE '%" + search.getText() + "%'";
            ResultSet results = handler.execQuery(sqlBooks);

            try {
                while (results.next()) {
                    String name = results.getString("name");
                    String author = results.getString("authorName");
                    String publisher = results.getString("publisher");
                    String topics = results.getString("topics");
                    int _id = results.getInt("_id");
                    Boolean booleanAvailability = results.getBoolean("isOnLoan");
                    String availability = "";
                    if (!booleanAvailability) {
                        availability += "available";
                    } else {
                        availability += "On Loan";
                    }

                    bookList.add(new LibraryItemModel(name, author, publisher, topics, availability, _id));
                }

                nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
                publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
                topicsCol.setCellValueFactory(new PropertyValueFactory<>("topics"));
                availabilityCol.setCellValueFactory(new PropertyValueFactory<>("availability"));

                Callback<TableColumn<LibraryItemModel, String>, TableCell<LibraryItemModel, String>> cellFactory =(param) -> {

                    // make cell for holding button
                    final TableCell<LibraryItemModel, String> cell = new TableCell<LibraryItemModel, String>(){

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
                                    LibraryItemModel bookModelInstance=getTableView().getItems().get(getIndex());
                                    try { //making the call to the viewBook method here when the button is clicked
                                        viewBook(bookModelInstance);
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
                viewBookCol.setCellFactory(cellFactory);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }


            /** ################  Ebook section below  ################ **/



            String sqlEBooks = "SELECT * FROM EBooks INNER JOIN Authors ON EBooks.author=Authors._authorId WHERE " + columnName +" LIKE '%" + search.getText() + "%'";
            ResultSet eResults = handler.execQuery(sqlEBooks);
            try {
                while (eResults.next()) {
                    String name = eResults.getString("name");
                    String author = eResults.getString("authorName");
                    String publisher = eResults.getString("publisher");
                    String topics = eResults.getString("topics");
                    int _id = eResults.getInt("_eBookId");

                    bookList.add(new LibraryItemModel(name, author, publisher, topics, _id));
                }

                nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
                publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
                topicsCol.setCellValueFactory(new PropertyValueFactory<>("topics"));
                availabilityCol.setCellValueFactory(new PropertyValueFactory<>("availability"));

                // creating cell factory to create button in each populated row
                Callback<TableColumn<LibraryItemModel, String>, TableCell<LibraryItemModel, String>> cellFactory =(param) -> {

                    // make cell for holding button
                    final TableCell<LibraryItemModel, String> cell = new TableCell<LibraryItemModel, String>(){

                        @Override //override cell updateItem method
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);

                            //make sure cell is only created on populated rows
                            if(empty) {
                                setGraphic(null);
                            } else {
                                final Button viewEBookButton= new Button("View Book");
                                //listener
                                viewEBookButton.setOnAction(event -> {
                                    LibraryItemModel eBookModel=getTableView().getItems().get(getIndex());
                                    //making the call to the viewBook method here when the button is clicked
                                    try {
                                        viewBook(eBookModel);
                                    } catch (IOException | SQLException e) {
                                        e.printStackTrace();
                                    }
                                });
                                //now set button to cell
                                setGraphic(viewEBookButton);
                            }
                            setText(null);
                        }
                    };
                    return cell;
                };
                viewBookCol.setCellFactory(cellFactory);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        tableView.getItems().setAll(bookList);
    }

    //Routes

    /**
     * Route that creates new Results scene and links to the fxml file.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */

    @FXML
    public void search() throws IOException {
        String searchString = this.newSearch.getText();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmlFiles/Results.fxml"));

        Parent resultsParent = loader.load();

        Scene resultsScene = new Scene(resultsParent);

        ResultsController resultsController = loader.getController();

        resultsController.setSearch(this.newSearch.getText());

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(resultsScene);
        window.show();
    }

    /**
     * Home route which creates new scene and links to the Home fxml file.
     * @param actionEvent responds to an Action event
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
     * Method which creates a new viewBook scene, and passes the ID of the book chosen to the new scene.
     * @param bookModel The Internal class with simple string properties used to represent a Book object.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void viewBook(LibraryItemModel bookModel) throws IOException, SQLException {
        String searchString = bookModel.getName();
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
     **/

    public class LibraryItemModel {
        private SimpleStringProperty name, author, publisher, topics, availability;
        private int _id;

        /**
         * The Class constructor for a Physical Book, it accepts string parameters and parses them into simple strings.
         * @param name The name of the Book.
         * @param author The name of the Author.
         * @param publisher The name of the publisher.
         * @param topics The topics as a coma separated list of strings.
         * @param availability The availability as a String.
         * @param _id The unique ID of the book.
         */

        public LibraryItemModel(String name, String author, String publisher, String topics, String availability, int _id) {
            this.name = new SimpleStringProperty(name);
            this.author = new SimpleStringProperty(author);
            this.publisher = new SimpleStringProperty(publisher);
            this.topics = new SimpleStringProperty(topics);
            this.availability = new SimpleStringProperty(availability);
            this._id = _id;
        }

        /**
         * The Class constructor for an Ebook, it accepts string parameters and parses them into simple strings.
         * @param name The name of the Book.
         * @param author The name of the Author.
         * @param publisher The name of the publisher.
         * @param topics The topics as a coma separated list of strings.
         * @param _id The unique ID of the book.
         */

        public LibraryItemModel(String name, String author, String publisher, String topics, int _id) {
            this.name = new SimpleStringProperty(name);
            this.author = new SimpleStringProperty(author);
            this.publisher = new SimpleStringProperty(publisher);
            this.topics = new SimpleStringProperty(topics);
            this.availability = new SimpleStringProperty("Ebook (View online)");
            this._id = _id;
        }

        /**
         * Method to get Name.
         * @return String Name.
         */

        public String getName() {
            return name.get();
        }

        /**
         * Method to get Author.
         * @return String Author.
         */

        public String getAuthor() {
            return author.get();
        }

        /**
         * Method to get Publisher.
         * @return String Publisher.
         */

        public String getPublisher() {
            return publisher.get();
        }

        /**
         * Method to get Topics.
         * @return String Topics.
         */

        public String getTopics() {
            return topics.get();
        }

        /**
         * Method to get Availability.
         * @return String Availability.
         */

        public String getAvailability() {
            return availability.get();
        }

        /**
         * Method to get ID.
         * @return String ID.
         */

        public int get_id() {
            return _id;
        }

        /**
         * Method to set ID.
         * @param _id String ID.
         */

        public void set_id(int _id) {
            this._id = _id;
        }

        /**
         * Method to set Name.
         * @param name String Name.
         */

        public void setName(String name) {
            this.name.set(name);
        }

        /**
         * Method to set Author.
         * @param author String Author.
         */

        public void setAuthor(String author) {
            this.author.set(author);
        }

        /**
         * Method to set Publisher.
         * @param publisher String Publisher.
         */

        public void setPublisher(String publisher) {
            this.publisher.set(publisher);
        }

        /**
         * Method to set Topics.
         * @param topics String Topics.
         */

        public void setTopics(String topics) {
            this.topics.set(topics);
        }

        /**
         * Method to set Availability.
         * @param availability String Availability.
         */

        public void setAvailability(String availability) {
            this.availability.set(availability);
        }
    }

}
