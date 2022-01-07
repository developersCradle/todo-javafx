package application;


import datamodel.Todo;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

//T‰m‰ kun lis‰t‰‰n uusi dialogi
public class TodoController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField notesField;

    public Todo getNewTodo() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String phoneNumber = phoneNumberField.getText();
        String notes = notesField.getText();

        Todo todo = new Todo();
        return todo;
    }

//    public void editContact(Todo contact) {
//        firstNameField.setText(contact.getFirstName());
//        lastNameField.setText(contact.getLastName());
//        phoneNumberField.setText(contact.getPhoneNumber());
//        notesField.setText(contact.getNotes());
//    }

//    public void updateContact(Todo contact) {
//        contact.setFirstName(firstNameField.getText());
//        contact.setLastName(lastNameField.getText());
//        contact.setPhoneNumber(phoneNumberField.getText());
//        contact.setNotes(notesField.getText());
//    }

}















