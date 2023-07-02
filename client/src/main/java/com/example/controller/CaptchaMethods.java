package com.example.controller;

import javafx.scene.image.Image;

import java.io.File;
import java.util.Random;

public class CaptchaMethods {
    private int captchaAnswer;
    private static CaptchaMethods captchaMethods;
    public static CaptchaMethods getInstance() {
        return captchaMethods == null ? captchaMethods = new CaptchaMethods() : captchaMethods;
    }

    public Image getCaptcha() {
        File dir = new File("./src/main/resources/captcha");
        File[] files = dir.listFiles();
        Random rand = new Random();
        File file = files[rand.nextInt(files.length)];
        captchaAnswer = Integer.parseInt(file.getName().substring(0, file.getName().length() - 4));
        return new Image(file.toURI().toString());
    }

    public int getCaptchaAnswer() {
        return captchaAnswer;
    }
}
