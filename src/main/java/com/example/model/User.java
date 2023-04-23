package com.example.model;

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
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.slogan = slogan;
        this.recoveryQuestionNumber = recoveryQuestionNumber;
        this.recoveryAnswer = recoveryAnswer;
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
        // TODO: sha-256
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
        // TODO: sha-256
        return this.password.equals(password);
    }

    public boolean isRecoveryAnswerCorrect(String recoveryAnswer) {
        // TODO: sha-256
        return this.recoveryAnswer.equals(recoveryAnswer);
    }


    private String SHA256Cryptographic(String input) {
        // TODO: sha-256
        return null;
    }

}
