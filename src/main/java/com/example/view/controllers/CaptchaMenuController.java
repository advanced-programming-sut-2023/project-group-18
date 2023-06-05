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

    public void setCaptcha(MouseEvent mouseEvent) {
        captcha.setImage(captchaMethods.getCaptcha());
    }

    public void submit(MouseEvent mouseEvent) {
        if (!captchaAnswer.getText().matches("\\d+")) {
            captchaError.setText("Enter a number!");
//            System.out.println("error");
        } else if (Integer.parseInt(captchaAnswer.getText()) != captchaMethods.getCaptchaAnswer()) {
//            System.out.println("captcha isn't correct");
            captchaError.setText("captcha isn't correct");
        } else {
            Popup popup = new Popup();
            Label label = new Label("Successful");
            label.setFont(new Font(20));
            popup.getContent().add(label);
            popup.show(Main.getStage());
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            // Call another method after one second
                            Platform.runLater(() -> {
                                popup.hide();
                                try {
                                    Main.goToMenu("MainMenu");
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        }
                    },
                    1000
            );
        }
    }
}
