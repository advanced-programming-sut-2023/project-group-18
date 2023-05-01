package com.example;

import com.example.model.CapthaCode;
import com.example.model.UsersData;
import com.example.view.LoginMenu;
import com.example.view.SignupMenu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginMenu loginMenu = LoginMenu.getInstance();
        loginMenu.run(scanner);
        UsersData.getUsersData().writeUsersInFile();
    }
}