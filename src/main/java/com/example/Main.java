package com.example;

import com.example.model.UsersData;
import com.example.view.LoginMenu;
import com.example.view.ProfileMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsersData usersData = UsersData.getUsersData();
        if (usersData.getStayLoggedInUser() == null) {
            LoginMenu loginMenu = LoginMenu.getInstance();
            loginMenu.run(scanner);
        } else {
            usersData.setLoggedInUser(usersData.getStayLoggedInUser());
            ProfileMenu profileMenu = ProfileMenu.getInstance();
            profileMenu.run(scanner);
        }
        UsersData.getUsersData().writeUsersInFile();
    }
}