package LibraryApp;

import LibraryApp.Models.Author;
import LibraryApp.Models.PhysicalBook;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Class that controls the Book page, initializes the data, controls the @FXML fields and actions.
 */

public class BookPageController implements Initializable {

    @FXML private Parent root;
    @FXML private int _authorId;
    @FXML private int _id;
    @FXML public Label nameLabel;
    @FXML public Label authorNameLabel;
    @FXML public Label publicationDateLabel;
    @FXML public Label publisherLabel;
    @FXML public TextArea topicsLabel;
    @FXML public Label timesReadLabel;
    @FXML public TextArea bioLabel;
    @FXML public Label iSBNLabel;
    @FXML public Label loanStatusLabel;
    @FXML public Button checkOut;

    public DatabaseHandler handler = DatabaseHandler.getInstance();

    /**
     * Class constructor.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public BookPageController() throws SQLException {
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
            DatabaseHandler.getInstance();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    //Actions

    /**
     * Method which is called from other controllers. Initializes ID field and calls loadData.
     * @param _id Unique book ID
     */

    public void viewBook(int _id) {
        loadData(_id);
        this._id = _id;
    }

    /**
     * Method which loads the fxml with data from the database.
     * @param _id The unique ID corresponding to the book.
     */

    private void loadData(int _id) {


        ///checking whether id corresponds to an EBook or a PBook
        String typeCheck ="";
        String sqlTypeCheck = "Select * From PBooks where _id = " + _id;
        ResultSet typeCheckResults = handler.execQuery(sqlTypeCheck);

        try {
            while (typeCheckResults.next()) {
                typeCheck = typeCheckResults.getString("name");
            }
        } catch (SQLException exception) {}

        if(typeCheck != "") { //PBook


            String sqlBooks = "SELECT * FROM PBooks INNER JOIN Authors ON PBooks.author=Authors._authorId WHERE _id=" + _id;
            ResultSet results = handler.execQuery(sqlBooks);
            try {
                while (results.next()) {
                    String name = results.getString("name");
                    String authorName = results.getString("authorName");
                    String publicationDate = results.getString("publicationDate");
                    String publisher = results.getString("publisher");
                    String topics = results.getString("topics");
                    int timesRead = results.getInt("timesRead");
                    String isbn = results.getString("isbn");
                    String bio = results.getString("bio");
                    int booleanAvailability = results.getInt("isOnLoan");
                    String availability = "";
                    if (booleanAvailability == 0) {
                        availability += "available";
                    } else {
                        availability += "On Loan";
                    }

                    Author authorInstance = handler.checkIfAuthorExists(authorName);
                    PhysicalBook bookInstance = new PhysicalBook(name, authorInstance, publicationDate, publisher, isbn);
                    this._authorId = results.getInt("_authorId");


                    nameLabel.setText(bookInstance.getName());
                    authorNameLabel.setText(bookInstance.getAuthor().getName());
                    publicationDateLabel.setText(bookInstance.getName());
                    publisherLabel.setText(bookInstance.getName());
                    iSBNLabel.setText(bookInstance.getISBN());
                    topicsLabel.setText(topics);
                    bioLabel.setText(bio);
                    timesReadLabel.setText(Integer.toString(timesRead));
                    loanStatusLabel.setText(availability);

                    if (booleanAvailability == 1){
                        checkOut.setText("Return Book");
                        checkOut.setOnAction( e -> {
                            try {
                                returnBook();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        });
                    }
                    else {
                        checkOut.setText("Check Out");
                        checkOut.setOnAction( e -> {
                            try {
                                checkOutBook();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        });
                    }
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }

        } else { //EBook


            String sqlBooks = "SELECT * FROM EBooks INNER JOIN Authors ON EBooks.author=Authors._authorId WHERE _eBookId=" + _id;
            ResultSet results = handler.execQuery(sqlBooks);
            try {
                while (results.next()) {
                    String name = results.getString("name");
                    String authorName = results.getString("authorName");
                    String publicationDate = results.getString("publicationDate");
                    String publisher = results.getString("publisher");
                    String topics = results.getString("topics");
                    int timesRead = results.getInt("timesRead");
                    String isbn = results.getString("isbn");
                    String bio = results.getString("bio");

                    Author authorInstance = handler.checkIfAuthorExists(authorName);
                    PhysicalBook bookInstance = new PhysicalBook(name, authorInstance, publicationDate, publisher, isbn);
                    this._authorId = results.getInt("_authorId");


                    nameLabel.setText(bookInstance.getName());
                    authorNameLabel.setText(bookInstance.getAuthor().getName());
                    publicationDateLabel.setText(bookInstance.getName());
                    publisherLabel.setText(bookInstance.getName());
                    iSBNLabel.setText(bookInstance.getISBN());
                    topicsLabel.setText(topics);
                    bioLabel.setText(bio);
                    timesReadLabel.setText(Integer.toString(timesRead));
                    loanStatusLabel.setText("View Online");

                    checkOut.setText("View Online");
                    checkOut.setOnAction( e -> {
                        try {
                            viewBookOnline();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    });

                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }

    }

    /**
     * Method that allows user to view Ebook online
     */

    private void viewBookOnline() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmlFiles/ViewEBook.fxml"));

        Parent viewEBookParent = loader.load();

        Scene viewEBookScene = new Scene(viewEBookParent);

        ViewEBookController viewEBookController = loader.getController();

        viewEBookController.loadData(this._id);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(viewEBookScene);
        window.show();
    }

    /**
     * Method that calculates the number of days a book is overdue.
     * @param dueDateString The due date in the format: dd/mm/yyyy.
     * @return The number of days late a book is.
     */

    public double feeCalculator(String dueDateString) {

        // list of days in each month where index is the month of the year
        int[] monthDays = {0,31,28,31,30,31,30,31,31,30,31,30,31}; //zero at the beginning because there is no zeroth month

        //current date
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String currentDateString = formatter.format(currentDate);

        int currentDays = Integer.parseInt(currentDateString.substring(0,2));
        int dueDays = Integer.parseInt(dueDateString.substring(0,2));

        int currentMonths = Integer.parseInt(currentDateString.substring(3,5));
        int dueMonths = Integer.parseInt(dueDateString.substring(3,5));

        int currentYears = Integer.parseInt(currentDateString.substring(6,10));
        int dueYears = Integer.parseInt(dueDateString.substring(6,10));

        double total = 0;
        if(dueYears > currentYears) { // not due till next year
            return 0.0;
        } else if( dueYears == currentYears) { // year is the same

            if(dueMonths > currentMonths) { // dueMonth > current Month - not due yet
                return 0.0;

            } else if (dueMonths  < currentMonths) { // dueMonth < currentMonth - overdue
                for (int i = dueMonths +1; i <currentMonths; i++) {
                    total += monthDays[i]; // day in months between two months
                }
                total += monthDays[dueMonths] - dueDays; // days till end of due month
                total += currentDays; //days in the current month

            } else { // Month is the same

                if (dueDays == currentDays) {
                    return 0.0;
                } else if (dueDays > currentDays) {
                    return 0.0;
                } else {
                    return currentDays-dueDays;
                }
            }

        } else { // dueYear < currentYear - overdue

            //days till the end of the due year
            total += monthDays[dueMonths] - dueDays; //days till end of the due month

            for(int i = dueMonths+1; i < 13; i++) { // days in months between due months and end of the year
                total += monthDays[i];
            }

            //days into the current year
            total+= currentDays; // days into current month

            for(int i = 0; i< currentMonths; i++) { // days in months between year start and current month
                total += monthDays[i];
            }
        }
        return total;
    }

    /**
     * Method that returns book and updates database.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    private void returnBook() throws IOException, SQLException {

        System.out.println("return book clicked");

        ResultSet results = handler.execQuery("SELECT * FROM PBooks WHERE _id=" + this._id);
        try{
            while (results.next()) {
                String dueDate = results.getString("dueDate");

                Double overDueFee = feeCalculator(dueDate);

                if (overDueFee != 0.0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("There is an overdue charge of " + overDueFee + " pounds associated with this book, please ensure this has been paid.");
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Book has been returned.");
                    alert.show();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //SQL return actions

        String sqlLoanAction = "UPDATE PBooks SET isOnLoan=" + 0 + " WHERE _id=" + this._id;
        boolean loanAction = handler.execAction(sqlLoanAction);

        String sqlUserAction = "UPDATE PBooks SET currentUser = " + null + " WHERE _id=" + this._id;
        boolean userAction = handler.execAction(sqlUserAction);

        String sqlFeeAction = "UPDATE PBooks SET overDueCharge = 0.0 WHERE _id=" + this._id;
        boolean feeAction = handler.execAction(sqlFeeAction);

        String sqlDueAction = "UPDATE PBooks SET dueDate = '' WHERE _id=" + this._id;
        boolean dueAction = handler.execAction(sqlDueAction);

        if (!feeAction||!userAction||!loanAction||!dueAction) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Error returning book");
            alert.show();
        }
        refresh(this._id);

    }

    //Routes

    /**
     * Route that creates checkOutBook controller, creates new scene and passes book ID to new scene.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */

    public void checkOutBook() throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmlFiles/CheckOutBook.fxml"));

        Parent checkOutBookParent = loader.load();

        Scene checkOutBookScene = new Scene(checkOutBookParent);

        CheckOutBookController checkOutController = loader.getController();

        checkOutController.loadData(this._id);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(checkOutBookScene);
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
     * Route that refreshes the controller, reinitializing the data and the fxml file.
     * @param _id The unique ID which corresponds to the book.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws SQLException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */

    public void refresh(int _id) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmlFiles/ViewBook.fxml"));

        Parent createBookParent = loader.load();

        Scene createBookScene = new Scene(createBookParent);

        BookPageController newController = loader.getController();

        newController.viewBook(_id);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(createBookScene);
        window.show();
    }

    /**
     * Route that creates new viewAuthor scene and links to the fxml file, passing the author ID to the new scene.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */

    public void viewAuthor() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmlFiles/ViewAuthor.fxml"));

        Parent viewAuthorParent = loader.load();

        Scene viewAuthorScene = new Scene(viewAuthorParent);

        ViewAuthorController viewAuthorController = loader.getController();

        viewAuthorController.viewAuthor(this._authorId);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(viewAuthorScene);
        window.show();
    }

    /**
     * Route that creates new editBook scene and links to the fxml file, passing the book ID to the new scene.
     * @param actionEvent An external stimulus from user interface.
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */

    public void editBook(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmlFiles/EditBook.fxml"));

        Parent editBookParent = loader.load();

        Scene editBookScene = new Scene(editBookParent);

        EditBookController editBookController = loader.getController();

        editBookController.loadData(this._id);

        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(editBookScene);
        window.show();
    }


}
