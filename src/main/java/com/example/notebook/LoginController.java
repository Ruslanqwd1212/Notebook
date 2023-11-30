package com.example.notebook;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class LoginController {
    @FXML
    PasswordField passwordField;
    @FXML
    Label passwordLabel;
    @FXML
    Button buttonSignIn;
    @FXML
    TextField loginField;

    Random random = new Random();
    Stage stage = new Stage();
    int generatedCode = random.nextInt(1000);
    String phoneNumber = "123";

    public void initialize(){
        loginField.setOnKeyPressed(this::handleKeyPress);
        passwordField.setOnKeyPressed(this::handleKeyPress);
    }

    private void handleKeyPress(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case ENTER -> {
                try {
                    onButtonClick();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case ESCAPE -> System.exit(1);
        }
    }

    @FXML
    public void onButtonClick() throws IOException, RuntimeException {
        try {
            passwordLabel.setDisable(false);
            passwordField.setDisable(false);
            buttonSignIn.setText("Sign in");

            Alert messageBox = new Alert(Alert.AlertType.INFORMATION);
            messageBox.setTitle("Confirmation code");
            messageBox.setHeaderText(null);
            messageBox.setContentText("Generated Code: " + generatedCode);
            messageBox.show();

            int password = Integer.parseInt(passwordField.getText());

            if (password == generatedCode && loginField.getText().equals(phoneNumber)){
                System.out.println("Вошёл в систему!");

                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("notebook-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setResizable(false);
                stage.setTitle("Notebook");
                stage.setScene(scene);
                stage.show();

                messageBox.close();
                Stage closeLoginWindow = (Stage) loginField.getScene().getWindow();
                closeLoginWindow.close();
            } else {
                messageBox.setAlertType(Alert.AlertType.ERROR);

                if (password != generatedCode){
                    System.out.println("Ошибка! Неправильный код подтверждения!");
                    messageBox.setContentText("Ошибка! Неправильный код подтверждения!");
                } else {
                    System.out.println("Ошибка! Неправильный логин!");
                    messageBox.setContentText("Ошибка! Неправильный логин!");
                }
            }
        } catch (RuntimeException e){
            System.out.println("Runtime Exception!");
        }
    }
}