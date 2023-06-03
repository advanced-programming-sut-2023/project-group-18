package com.example.view.controllers;

import com.example.controller.CaptchaMethods;
import com.example.model.CapthaCode;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class CaptchaMenuController {
    private final CaptchaMethods captchaMethods = CaptchaMethods.getInstance();
    @FXML
    private TextField captchaAnswer;
    @FXML
    private ImageView captcha;

    @FXML
    public void initialize() {
        captcha.setImage(captchaMethods.getCaptcha());
    }

    public void setCaptcha(MouseEvent mouseEvent) {
        captcha.setImage(captchaMethods.getCaptcha());
    }

    public void submit(MouseEvent mouseEvent) {
        if (!captchaAnswer.getText().matches("\\d+")) {
            System.out.println("error");
        } else if (Integer.parseInt(captchaAnswer.getText()) != captchaMethods.getCaptchaAnswer()) {
            System.out.println("captcha isn't correct");
        } else {
            System.out.println("good, good");
        }
    }
}
