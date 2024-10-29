package org.example.csc311week9regex;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RegistrationForm extends Application {
    private TextField firstNameField, lastNameField, emailField, dobField, zipField;
    private Button addButton;
    private Label firstNameError, lastNameError, emailError, dobError, zipError;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Registration Form");

        // GridPane layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        // First Name
        Label firstNameLabel = new Label("First Name:");
        firstNameField = new TextField();
        firstNameError = new Label();
        firstNameError.setTextFill(Color.RED);
        grid.add(firstNameLabel, 0, 0);
        grid.add(firstNameField, 1, 0);
        grid.add(firstNameError, 1, 1);

        // Last Name
        Label lastNameLabel = new Label("Last Name:");
        lastNameField = new TextField();
        lastNameError = new Label();
        lastNameError.setTextFill(Color.RED);
        grid.add(lastNameLabel, 0, 2);
        grid.add(lastNameField, 1, 2);
        grid.add(lastNameError, 1, 3);

        // Email
        Label emailLabel = new Label("Email Address:");
        emailField = new TextField();
        emailError = new Label();
        emailError.setTextFill(Color.RED);
        grid.add(emailLabel, 0, 4);
        grid.add(emailField, 1, 4);
        grid.add(emailError, 1, 5);

        // Date of Birth
        Label dobLabel = new Label("Date of Birth (MM/DD/YYYY):");
        dobField = new TextField();
        dobError = new Label();
        dobError.setTextFill(Color.RED);
        grid.add(dobLabel, 0, 6);
        grid.add(dobField, 1, 6);
        grid.add(dobError, 1, 7);

        // Zip Code
        Label zipLabel = new Label("Zip Code:");
        zipField = new TextField();
        zipError = new Label();
        zipError.setTextFill(Color.RED);
        grid.add(zipLabel, 0, 8);
        grid.add(zipField, 1, 8);
        grid.add(zipError, 1, 9);

        // Add Button
        addButton = new Button("Add");
        addButton.setDisable(true);
        grid.add(addButton, 1, 10);

        // Validate each field and update button state
        validateFields();

        // Scene setup
        Scene scene = new Scene(grid, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

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

    private void validateFirstName() {
        String firstName = firstNameField.getText();
        if (firstName.length() < 2 || firstName.length() > 25) {
            firstNameError.setText("Must be 2-25 characters long.");
        } else {
            firstNameError.setText("");
        }
    }

    private void validateLastName() {
        String lastName = lastNameField.getText();
        if (lastName.length() < 2 || lastName.length() > 25) {
            lastNameError.setText("Must be 2-25 characters long.");
        } else {
            lastNameError.setText("");
        }
    }

    private void validateEmail() {
        String email = emailField.getText();
        if (!email.matches("^[\\w-.]+@farmingdale\\.edu$")) {
            emailError.setText("Must be a valid Farmingdale email.");
        } else {
            emailError.setText("");
        }
    }

    private void validateDob() {
        String dob = dobField.getText();
        if (!dob.matches("^(0[1-9]|1[0-2])/(0[1-9]|[12]\\d|3[01])/\\d{4}$")) {
            dobError.setText("Must be in MM/DD/YYYY format.");
        } else {
            dobError.setText("");
        }
    }

    private void validateZip() {
        String zip = zipField.getText();
        if (!zip.matches("\\d{5}")) {
            zipError.setText("Must be a 5-digit number.");
        } else {
            zipError.setText("");
        }
    }

    private void enableAddButton() {
        addButton.setDisable(
                firstNameError.getText().length() > 0 ||
                        lastNameError.getText().length() > 0 ||
                        emailError.getText().length() > 0 ||
                        dobError.getText().length() > 0 ||
                        zipError.getText().length() > 0
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}
