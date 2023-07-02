package com.example.view.controllers;

import com.example.controller.CaptchaMethods;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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
