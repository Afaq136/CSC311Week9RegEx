 package org.example.csc311week9regex;

 import javafx.application.Application;
 import javafx.geometry.Insets;
 import javafx.scene.Scene;
 import javafx.scene.control.*;
 import javafx.scene.input.KeyEvent;
 import javafx.scene.layout.GridPane;
 import javafx.stage.Stage;
 import javafx.scene.layout.VBox;
 import javafx.scene.text.Text;

 import java.time.LocalDate;
 import java.time.format.DateTimeFormatter;
 import java.time.format.DateTimeParseException;

 public class Main extends Application {

     private TextField firstNameField, lastNameField, emailField, zipCodeField;
     private DatePicker dobPicker;
     private Button addButton;
     private Label firstNameError, lastNameError, emailError, dobError, zipCodeError;

     @Override
     public void start(Stage primaryStage) {
         primaryStage.setTitle("Registration Form");

         // Initialize fields
         firstNameField = new TextField();
         lastNameField = new TextField();
         emailField = new TextField();
         zipCodeField = new TextField();
         dobPicker = new DatePicker();
         addButton = new Button("Add");
         addButton.setDisable(true);

         // Error Labels
         firstNameError = new Label();
         lastNameError = new Label();
         emailError = new Label();
         dobError = new Label();
         zipCodeError = new Label();

         // Form Layout
         GridPane formLayout = new GridPane();
         formLayout.setPadding(new Insets(10));
         formLayout.setVgap(8);
         formLayout.setHgap(10);

         formLayout.add(new Label("First Name:"), 0, 0);
         formLayout.add(firstNameField, 1, 0);
         formLayout.add(firstNameError, 2, 0);

         formLayout.add(new Label("Last Name:"), 0, 1);
         formLayout.add(lastNameField, 1, 1);
         formLayout.add(lastNameError, 2, 1);

         formLayout.add(new Label("Email:"), 0, 2);
         formLayout.add(emailField, 1, 2);
         formLayout.add(emailError, 2, 2);

         formLayout.add(new Label("Date of Birth:"), 0, 3);
         formLayout.add(dobPicker, 1, 3);
         formLayout.add(dobError, 2, 3);

         formLayout.add(new Label("Zip Code:"), 0, 4);
         formLayout.add(zipCodeField, 1, 4);
         formLayout.add(zipCodeError, 2, 4);

         formLayout.add(addButton, 1, 5);

         // Input Validation
         firstNameField.focusedProperty().addListener((obs, oldVal, newVal) -> validateName(firstNameField, firstNameError));
         lastNameField.focusedProperty().addListener((obs, oldVal, newVal) -> validateName(lastNameField, lastNameError));
         emailField.focusedProperty().addListener((obs, oldVal, newVal) -> validateEmail());
         dobPicker.focusedProperty().addListener((obs, oldVal, newVal) -> validateDateOfBirth());
         zipCodeField.focusedProperty().addListener((obs, oldVal, newVal) -> validateZipCode());

         addButton.setOnAction(e -> showSuccessScreen(primaryStage));

         VBox root = new VBox(10);
         root.setPadding(new Insets(20));
         root.getChildren().add(formLayout);

         primaryStage.setScene(new Scene(root, 400, 350));
         primaryStage.show();
     }

     /**
      * Validates the name input fields using a regex for length.
      *
      * @param nameField the text field to validate.
      * @param errorLabel the label to display validation errors.
      */
     private void validateName(TextField nameField, Label errorLabel) {
         String name = nameField.getText();
         if (name.matches("^[A-Za-z]{2,25}$")) {
             errorLabel.setText("");
         } else {
             errorLabel.setText("Name must be 2-25 letters.");
         }
         checkFormValidity();
     }

     private void validateEmail() {
         String email = emailField.getText();
         if (email.matches("^[\\w.-]+@farmingdale\\.edu$")) {
             emailError.setText("");
         } else {
             emailError.setText("Must be a @farmingdale.edu email.");
         }
         checkFormValidity();
     }

     private void validateDateOfBirth() {
         LocalDate dob = dobPicker.getValue();
         if (dob != null && dob.isBefore(LocalDate.now())) {
             dobError.setText("");
         } else {
             dobError.setText("Invalid date of birth.");
         }
         checkFormValidity();
     }

     private void validateZipCode() {
         String zip = zipCodeField.getText();
         if (zip.matches("^[0-9]{5}$")) {
             zipCodeError.setText("");
         } else {
             zipCodeError.setText("Zip must be 5 digits.");
         }
         checkFormValidity();
     }

     private void checkFormValidity() {
         addButton.setDisable(firstNameError.getText().length() > 0 ||
                 lastNameError.getText().length() > 0 ||
                 emailError.getText().length() > 0 ||
                 dobError.getText().length() > 0 ||
                 zipCodeError.getText().length() > 0 ||
                 firstNameField.getText().isEmpty() ||
                 lastNameField.getText().isEmpty() ||
                 emailField.getText().isEmpty() ||
                 dobPicker.getValue() == null ||
                 zipCodeField.getText().isEmpty());
     }

     private void showSuccessScreen(Stage primaryStage) {
         Label successMessage = new Label("Registration Successful!");
         VBox successLayout = new VBox(20);
         successLayout.setPadding(new Insets(20));
         successLayout.getChildren().add(successMessage);

         primaryStage.setScene(new Scene(successLayout, 300, 200));
     }

     public static void main(String[] args) {
         launch(args);
     }
 }
