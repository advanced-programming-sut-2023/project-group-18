package com.example.client.view.controllers;

import com.example.client.view.Client;
import com.example.server.controller.SecurityMethods;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.example.server.controller.responses.FieldResponses.EMPTY_FIELD;

public class SecurityMenuController {
    private final SecurityMethods securityMethods = SecurityMethods.getInstance();
    @FXML
    private Label securityQuestionError;
    @FXML
    private TextField securityAnswer;
    @FXML
    private ChoiceBox securityChoiceBox;

    public void submitQuestion() throws IOException {
        if (securityAnswer.getText().equals("")) {
            securityQuestionError.setText(EMPTY_FIELD);
        } else if (securityChoiceBox.getSelectionModel().isEmpty()) {
            securityQuestionError.setText("you should select a question!");
        } else {
            securityMethods.addUser(securityChoiceBox.getSelectionModel().getSelectedIndex(),securityAnswer.getText());
            securityMethods.login();
            Client.goToMenu("CaptchaMenu");
        }
    }
}