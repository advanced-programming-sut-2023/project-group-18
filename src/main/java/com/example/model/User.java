package com.example.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User implements PasswordRecoveryQuestions {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String slogan;
    private int highscore;
    private final int recoveryQuestionNumber;
    private final String recoveryAnswer;

    protected User(String username, String password, String nickname, String email, String slogan, int recoveryQuestionNumber, String recoveryAnswer) {
        this.username = username;
        setPassword(password);
        this.nickname = nickname;
        this.email = email;
        this.slogan = slogan;
        this.recoveryQuestionNumber = recoveryQuestionNumber;
        this.recoveryAnswer = SHA256Cryptographic(recoveryAnswer);
        this.highscore = 0;
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

    public String getRecoveryQuestion() {
        return PASSWORD_RECOVERY_QUESTIONS[recoveryQuestionNumber];
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = SHA256Cryptographic(password);
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
        return this.password.equals(SHA256Cryptographic(password));
    }

    public boolean isRecoveryAnswerCorrect(String recoveryAnswer) {
        return this.recoveryAnswer.equals(SHA256Cryptographic(recoveryAnswer));
    }


    private String SHA256Cryptographic(String input) {
        MessageDigest digest;
        final String algorithmName = "SHA-256";
        try {
            digest = MessageDigest.getInstance(algorithmName);
            byte[] encodedhash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return new String(encodedhash, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("There is no algorithm with name: " + algorithmName);
        }
        return null;
    }

}
