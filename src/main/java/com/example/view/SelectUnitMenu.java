package com.example.view;

import com.example.controller.Methods.GlobalMethods;

import java.util.Scanner;

public class SelectUnitMenu {
    private static SelectUnitMenu selectUnitMenu;
    private final GlobalMethods globalMethods;
    private SelectUnitMenu() {
        globalMethods = GlobalMethods.getInstance();
    }
    private static SelectUnitMenu selectUnitMenu() {
        return selectUnitMenu == null ? selectUnitMenu = new SelectUnitMenu() : selectUnitMenu;
    }
    
    public void run(Scanner scanner) {
    
    }
}
