package LibraryApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class EditBookController {

    @FXML private ChoiceBox<String> editChoiceValue;
    @FXML private TextField propertySelection;

    public void editBook(ActionEvent actionEvent) { //this functionality needs to go on the book page
        String newValue = propertySelection.getText();
        String choice = editChoiceValue.getValue();

        if(newValue.isEmpty()|| choice == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please make a selection and enter a new value");
            alert.showAndWait();
            return;
        }
        //pseudocode for how to finish edit method once it is embedded in book page
        //SQL query database for specific book values, use to instantiate book
        //Book newBook = Book()
        //newBook.methodToChangeChoice(newValue)
        //SQL action to overwrite old value with new

    }
    private void loadEditOptions() { //this works but was causing some error, wait till it's moved to book view
//        editOptionsList.removeAll();
//        String editName = "Name";
//        String editAuthor = "Author";
//        String editPublicationDate = "Publication Date";
//        String editPublisher = "Publisher";
//
//        editOptionsList.addAll(editName, editAuthor, editPublicationDate, editPublisher);
//
//        editOptionsList.add("Name");
//        editChoiceValue.getItems().addAll(editOptionsList);

    }
}
