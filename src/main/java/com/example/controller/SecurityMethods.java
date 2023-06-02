package com.example.controller;

import com.example.model.User;
import com.example.model.UsersData;

public class SecurityMethods {
    private String tempUsername;
    private String tempPassword;
    private String tempEmail;
    private String tempNickname;
    private String tempSlogan;
    private final UsersData usersData = UsersData.getUsersData();
    private static SecurityMethods securityMethods;
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

    public void addUser(int securityQuestionNumber, String securityAnswer) {
        UsersData.getUsersData().addUser(tempUsername, tempPassword, tempNickname, tempEmail,
                tempSlogan, securityQuestionNumber, securityAnswer);
    }
}
