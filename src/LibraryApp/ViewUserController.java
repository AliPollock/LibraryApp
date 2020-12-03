package LibraryApp;

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
 * Class that controls the ViewUserBook page, initializes the data, controls the @FXML fields and actions.
 */

public class ViewUserController implements Initializable {


    @FXML private Label userNameLabel;
    @FXML private Parent root;
    @FXML public TableView<ViewAuthorController.LibraryItemModel> tableView;
    @FXML public TableColumn<ViewAuthorController.LibraryItemModel, String> nameCol;
    @FXML public TableColumn<ViewAuthorController.LibraryItemModel, String> authorCol;
    @FXML public TableColumn<ViewAuthorController.LibraryItemModel, String> publisherCol;
    @FXML public TableColumn<ViewAuthorController.LibraryItemModel, String> topicsCol;
    @FXML public TableColumn<ViewAuthorController.LibraryItemModel, Boolean> availabilityCol;
    @FXML public TableColumn viewBookCol;

    public ObservableList<ViewAuthorController.LibraryItemModel> bookList = FXCollections.observableArrayList();
    public DatabaseHandler handler;

    /**
     * Method that is called from other constructors and calls loadData.
     * @param _userid int user ID.
     */

    public void viewUser(int _userid) {
        loadData(_userid);
    }

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
     * Method that loads data retreived from the database about the author into fxml.
     * @param _userId int user ID.
     */

    private void loadData(int _userId) {

        String sqlBooks = "SELECT * FROM PBooks INNER JOIN Users ON PBooks.currentUser=Users._userId INNER JOIN Authors ON PBooks.author=Authors._authorId WHERE PBooks.currentUser=" + _userId;
        ResultSet results = handler.execQuery(sqlBooks);

        try {
            while (results.next()) {
                String name = results.getString("name");
                String author = results.getString("authorName");
                String publisher = results.getString("publisher");
                String topics = results.getString("topics");
                int _id = results.getInt("_id");
                String userName = results.getString("userName");
                Boolean booleanAvailability = results.getBoolean("isOnLoan");
                String availability = "";
                if (!booleanAvailability) {
                    availability += "available";
                } else {
                    availability += "On Loan";
                }
            ViewAuthorController.LibraryItemModel authorInstance = new ViewAuthorController.LibraryItemModel(name, author, publisher, topics, availability, _id);
            bookList.add(authorInstance);


            userNameLabel.setText(userName);
            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
            publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
            topicsCol.setCellValueFactory(new PropertyValueFactory<>("topics"));
            availabilityCol.setCellValueFactory(new PropertyValueFactory<>("availability"));

            }

            Callback<TableColumn<ViewAuthorController.LibraryItemModel, String>, TableCell<ViewAuthorController.LibraryItemModel, String>> cellFactory =(param) -> {

                // make cell for holding button
                final TableCell<ViewAuthorController.LibraryItemModel, String> cell = new TableCell<ViewAuthorController.LibraryItemModel, String>(){

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
                                ViewAuthorController.LibraryItemModel bookModelInstance=getTableView().getItems().get(getIndex());
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
        tableView.getItems().setAll(bookList);
    }

    /**
     * Home route which creates new scene and links to the Home fxml file.
     * @param actionEvent responds to an Action event.
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

    public void viewBook(ViewAuthorController.LibraryItemModel bookModel) throws IOException, SQLException {
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

}
