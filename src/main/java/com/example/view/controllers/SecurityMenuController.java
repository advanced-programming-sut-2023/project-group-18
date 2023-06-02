package com.example.view.controllers;

import com.example.controller.SecurityMethods;
import com.example.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import static com.example.controller.responses.FieldResponses.EMPTY_FIELD;

public class SecurityMenuController {
    private final SecurityMethods securityMethods = SecurityMethods.getInstance();
    @FXML
    private Label securityQuestionError;
    @FXML
    private TextField securityAnswer;
    @FXML
    private ChoiceBox securityChoiceBox;

    public void submitQuestion(MouseEvent mouseEvent) throws IOException {
        if (securityAnswer.getText().equals("")) {
            securityQuestionError.setText(EMPTY_FIELD);
        } else if (securityChoiceBox.getSelectionModel().isEmpty()) {
            securityQuestionError.setText("you should select a question!");
        } else {
            securityMethods.addUser(securityChoiceBox.getSelectionModel().getSelectedIndex(),securityAnswer.getText());
            Main.goToMenu("CaptchaMenu");
        }
    }
}