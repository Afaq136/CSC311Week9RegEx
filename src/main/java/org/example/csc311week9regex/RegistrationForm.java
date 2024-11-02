package org.example.csc311week9regex;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * RegistrationForm is a JavaFX application that displays a registration form
 * with input fields for first name, last name, email, date of birth, and zip code.
 * The form includes validation for each input and enables an "Add" button only
 * when all fields are valid.
 */
public class RegistrationForm extends Application {
    private TextField firstNameField, lastNameField, emailField, dobField, zipField;
    private Button addButton;
    private Label firstNameError, lastNameError, emailError, dobError, zipError;

    /**
     * The start method sets up the UI components and layout for the registration form.
     *
     * @param primaryStage the main stage for this JavaFX application
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Registration Form");

        // GridPane layout for organizing UI elements in a grid
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));

        // Registration Form Title
        Label titleLabel = new Label("Registration Form");
        titleLabel.setFont(new Font("Arial", 20));
        grid.add(titleLabel, 0, 0, 2, 1);

        // Default Profile Picture
        String profileImagePath = "file:/C:/Users/mahsa/Desktop/Fall 2024/CSC311-AP/CSC311Week9RegEx/profile.png";
        Image profileImage = new Image(profileImagePath);
        ImageView profileImageView = new ImageView(profileImage);
        profileImageView.setFitWidth(100);
        profileImageView.setFitHeight(100);
        grid.add(profileImageView, 0, 1, 2, 1);

        // First Name Field with Validation
        Label firstNameLabel = new Label("First Name:");
        firstNameField = new TextField();
        firstNameError = new Label();
        firstNameError.setTextFill(Color.RED);
        grid.add(firstNameLabel, 0, 2);
        grid.add(firstNameField, 1, 2);
        grid.add(firstNameError, 1, 3);

        // Last Name Field with Validation
        Label lastNameLabel = new Label("Last Name:");
        lastNameField = new TextField();
        lastNameError = new Label();
        lastNameError.setTextFill(Color.RED);
        grid.add(lastNameLabel, 0, 4);
        grid.add(lastNameField, 1, 4);
        grid.add(lastNameError, 1, 5);

        // Email Field with Validation
        Label emailLabel = new Label("Email Address:");
        emailField = new TextField();
        emailError = new Label();
        emailError.setTextFill(Color.RED);
        grid.add(emailLabel, 0, 6);
        grid.add(emailField, 1, 6);
        grid.add(emailError, 1, 7);

        // Date of Birth Field with Validation
        Label dobLabel = new Label("Date of Birth (MM/DD/YYYY):");
        dobField = new TextField();
        dobError = new Label();
        dobError.setTextFill(Color.RED);
        grid.add(dobLabel, 0, 8);
        grid.add(dobField, 1, 8);
        grid.add(dobError, 1, 9);

        // Zip Code Field with Validation
        Label zipLabel = new Label("Zip Code:");
        zipField = new TextField();
        zipError = new Label();
        zipError.setTextFill(Color.RED);
        grid.add(zipLabel, 0, 10);
        grid.add(zipField, 1, 10);
        grid.add(zipError, 1, 11);

        // Add Button
        addButton = new Button("Add");
        addButton.setDisable(true); // Initially disable the button
        addButton.setStyle("-fx-background-color: #2465d8; -fx-text-fill: white;");
        addButton.setOnAction(event -> navigateToNewUI(primaryStage));
        grid.add(addButton, 1, 12);

        // Validate each field when focus changes
        validateFields();

        // Explicitly call enableAddButton to check initial state of the fields
        enableAddButton();

        // Scene setup with grid layout
        Scene scene = new Scene(grid, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Sets up listeners to validate each input field when the focus changes.
     */
    private void validateFields() {
        firstNameField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) validateFirstName();
            enableAddButton();
        });

        lastNameField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) validateLastName();
            enableAddButton();
        });

        emailField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) validateEmail();
            enableAddButton();
        });

        dobField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) validateDob();
            enableAddButton();
        });

        zipField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) validateZip();
            enableAddButton();
        });
    }

    /**
     * Validates the first name field, displaying an error message if it doesn't meet
     * the length requirements of 2-25 characters.
     */
    private void validateFirstName() {
        String firstName = firstNameField.getText();
        if (firstName.length() < 2 || firstName.length() > 25) {
            firstNameError.setText("Must be 2-25 characters long.");
        } else {
            firstNameError.setText("");
        }
    }

    /**
     * Validates the last name field, displaying an error message if it doesn't meet
     * the length requirements of 2-25 characters.
     */
    private void validateLastName() {
        String lastName = lastNameField.getText();
        if (lastName.length() < 2 || lastName.length() > 25) {
            lastNameError.setText("Must be 2-25 characters long.");
        } else {
            lastNameError.setText("");
        }
    }

    /**
     * Validates the email field to ensure it follows the required Farmingdale email format.
     */
    private void validateEmail() {
        String email = emailField.getText();
        if (!email.matches("^[\\w-.]+@farmingdale\\.edu$")) {
            emailError.setText("Must be a valid Farmingdale email.");
        } else {
            emailError.setText("");
        }
    }

    /**
     * Validates the date of birth field to ensure it follows the MM/DD/YYYY format.
     */
    private void validateDob() {
        String dob = dobField.getText();
        if (!dob.matches("^(0[1-9]|1[0-2])/(0[1-9]|[12]\\d|3[01])/\\d{4}$")) {
            dobError.setText("Must be in MM/DD/YYYY format.");
        } else {
            dobError.setText("");
        }
    }

    /**
     * Validates the zip code field to ensure it is a 5-digit number.
     */
    private void validateZip() {
        String zip = zipField.getText();
        if (!zip.matches("\\d{5}")) {
            zipError.setText("Must be a 5-digit number.");
        } else {
            zipError.setText("");
        }
    }

    /**
     * Enables or disables the "Add" button based on the presence of any error messages.
     */
    private void enableAddButton() {
        addButton.setDisable(
                firstNameError.getText().length() > 0 ||
                        lastNameError.getText().length() > 0 ||
                        emailError.getText().length() > 0 ||
                        dobError.getText().length() > 0 ||
                        zipError.getText().length() > 0
        );
    }

    /**
     * Navigates to a new UI scene displaying a success message upon successful registration.
     *
     * @param primaryStage the main stage for this JavaFX application
     */
    private void navigateToNewUI(Stage primaryStage) {
        Label successLabel = new Label("Registration Successful!");
        successLabel.setFont(new Font("Arial", 20));
        GridPane newGrid = new GridPane();
        newGrid.setAlignment(Pos.CENTER);
        newGrid.setPadding(new Insets(20));
        newGrid.add(successLabel, 0, 0);

        Scene newScene = new Scene(newGrid, 300, 200);
        primaryStage.setScene(newScene);
    }

    /**
     * Main method to launch the JavaFX application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
