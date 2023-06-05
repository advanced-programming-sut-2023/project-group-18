package com.example.view.controllers;

import com.example.controller.CaptchaMethods;
import com.example.model.CapthaCode;
import com.example.view.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Popup;

import java.io.IOException;

public class CaptchaMenuController {
    private final CaptchaMethods captchaMethods = CaptchaMethods.getInstance();
    @FXML
    private Label captchaError;
    @FXML
    private TextField captchaAnswer;
    @FXML
    private ImageView captcha;

    @FXML
    public void initialize() {
        captcha.setImage(captchaMethods.getCaptcha());
    }

    public void setCaptcha() {
        captcha.setImage(captchaMethods.getCaptcha());
    }

    public void submit() {
        if (!captchaAnswer.getText().matches("\\d+")) {
            captchaError.setText("Enter a number!");
        } else if (Integer.parseInt(captchaAnswer.getText()) != captchaMethods.getCaptchaAnswer()) {
            captchaError.setText("captcha isn't correct");
        } else {
            SignupMenuController.showLabeledPopup("Successful", "MainMenu");
        }
    }
}
