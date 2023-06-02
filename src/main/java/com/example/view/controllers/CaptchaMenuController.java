package com.example.view.controllers;

import com.example.model.CapthaCode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CaptchaMenuController {
    @FXML
    private TextField captchaAnswer;
    @FXML
    private TextArea captcha;

    @FXML
    public void initialize() {
        captcha.setText(CapthaCode.generateCapthaCode());
    }

    public void setCaptcha(MouseEvent mouseEvent) {

    }
}
