package com.example.model;

import java.util.ArrayList;

public class User {
    private static final ArrayList<User> USERS;
    private static final ArrayList<String> PASSWORD_RECOVERY_QUESTIONS;
    private final int recoveryQuestionNumber;
    private final String recoveryAnswer;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String slogan;
    private int highscore;

    static {
        USERS = new ArrayList<>();
        PASSWORD_RECOVERY_QUESTIONS = new ArrayList<>();
        PASSWORD_RECOVERY_QUESTIONS.add("What is my father's name?");
        PASSWORD_RECOVERY_QUESTIONS.add("What was my first pet's name?");
        PASSWORD_RECOVERY_QUESTIONS.add("What is my mother's last name?");
    }

    public User(String username, String password, String nickname, String email, String slogan, int recoveryQuestionNumber, String recoveryAnswer) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.slogan = slogan;
        this.recoveryQuestionNumber = recoveryQuestionNumber;
        this.recoveryAnswer = recoveryAnswer;
        this.highscore = 0;
        USERS.add(this);
    }

    public static ArrayList<User> getUsers() {
        return USERS;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getSlogan() {
        return slogan;
    }

    public int getHighscore() {
        return highscore;
    }

    public int getRecoveryQuestionNumber() {
        return recoveryQuestionNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }


    public boolean isPasswordCorrect(String password) {
        return this.password.equals(password);
    }

    public boolean isRecoveryAnswerCorrect(String recoveryAnswer) {
        return this.recoveryAnswer.equals(recoveryAnswer);
    }


}
