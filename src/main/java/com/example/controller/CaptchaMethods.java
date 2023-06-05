package com.example.controller;

import com.example.model.UsersData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.Random;

public class CaptchaMethods {
    private int captchaAnswer;
    private static CaptchaMethods captchaMethods;
    private final UsersData usersData = UsersData.getUsersData();
    public static CaptchaMethods getInstance() {
        return captchaMethods == null ? captchaMethods = new CaptchaMethods() : captchaMethods;
    }

    public Image getCaptcha() {
        File dir = new File("./src/main/resources/captcha");
        File[] files = dir.listFiles();
        Random rand = new Random();
//        System.out.println("testing\n\n\n\n\n\n\n\n\n\n....");
//        System.out.println(files.length);
        File file = files[rand.nextInt(files.length)];
//        System.out.println(file.getName().substring(0, file.getName().length() - 4));
        captchaAnswer = Integer.parseInt(file.getName().substring(0, file.getName().length() - 4));
        return new Image(file.toURI().toString());
    }

    public int getCaptchaAnswer() {
        return captchaAnswer;
    }
}
