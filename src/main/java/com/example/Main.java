package com.example;

import com.example.model.CapthaCode;
import com.example.model.UsersData;
import com.example.view.LoginMenu;
import com.example.view.ProfileMenu;
import com.example.view.SignupMenu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsersData usersData = UsersData.getUsersData();
        if (usersData.getLoggedInUser() == null) {
            LoginMenu loginMenu = LoginMenu.getInstance();
            loginMenu.run(scanner);
        } else {
            ProfileMenu profileMenu = ProfileMenu.getInstance();
            profileMenu.run(scanner);
        }
        UsersData.getUsersData().writeUsersInFile();
    }
}