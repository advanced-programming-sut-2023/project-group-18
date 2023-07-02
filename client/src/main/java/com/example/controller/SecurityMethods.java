package com.example.controller;

import com.example.model.Request;

public class SecurityMethods {
    private String tempUsername;
    private String tempPassword;
    private String tempEmail;
    private String tempNickname;
    private String tempSlogan;
    private static SecurityMethods securityMethods;
    private final NetworkController networkController;

    private SecurityMethods() {
        networkController = NetworkController.getInstance();
    }

    public static SecurityMethods getInstance() {
        return securityMethods == null ? securityMethods = new SecurityMethods() : securityMethods;
    }

    public void setTempUser(String tempUsername, String tempPassword, String tempEmail, String tempNickname, String tempSlogan) {
        this.tempUsername = tempUsername;
        this.tempPassword = tempPassword;
        this.tempEmail = tempEmail;
        this.tempNickname = tempNickname;
        this.tempSlogan = tempSlogan;
    }

    public String getTempUsername() {
        return tempUsername;
    }

    public String getTempPassword() {
        return tempPassword;
    }

    public String getTempEmail() {
        return tempEmail;
    }

    public String getTempNickname() {
        return tempNickname;
    }

    public String getTempSlogan() {
        return tempSlogan;
    }

    // TODO: need to use server
    public void addUser(int securityQuestionNumber, String securityAnswer) {
        networkController.transferData(new Request("LoginController", "addUser",
                tempUsername, tempPassword, tempNickname, tempEmail,
                tempSlogan, securityQuestionNumber, securityAnswer));
        
    }

    // TODO: need to use server
    public void login() {
        networkController.transferData(new Request("LoginController", "login", tempUsername));
    }
}
