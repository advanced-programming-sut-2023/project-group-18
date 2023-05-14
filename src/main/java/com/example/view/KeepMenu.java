package com.example.view;

import com.example.controller.Commands.GameMenuCommands;
import com.example.controller.Methods.GameMenuMethods;
import com.example.controller.Methods.GlobalMethods;

import java.util.Scanner;
import java.util.regex.Matcher;

public class KeepMenu implements SelectBuildingMenu {
    private static KeepMenu keepMenu;
    private final GameMenuMethods gameMenuMethods;
    private final GlobalMethods globalMethods;

    public KeepMenu() {
        this.gameMenuMethods = GameMenuMethods.gameMenuMethods();
        this.globalMethods = GlobalMethods.getInstance();
    }

    public static KeepMenu getKeepMenu() {
        return keepMenu == null ? keepMenu = new KeepMenu() : keepMenu;
    }
    @Override
    public void run(Scanner scanner) {
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            if (GameMenuCommands.getMatcher(input, GameMenuCommands.SHOW_POPULARITY_FACTORS).find()) {
                showPopularityFactors();
            } else if (GameMenuCommands.getMatcher(input, GameMenuCommands.SHOW_POPULARITY).find()) {
                showPopularity();
            } else if (GameMenuCommands.getMatcher(input, GameMenuCommands.SHOW_FOOD_LIST).find()) {
                showFoodList();
            } else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.FOOD_RATE)).find()) {
                foodRate(matcher);
            } else if (GameMenuCommands.getMatcher(input, GameMenuCommands.SHOW_FOOD_RATE).find()) {
                showFoodRate();
            } else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.TAX_RATE)).find()) {
                setTaxRate(matcher);
            } else if (GameMenuCommands.getMatcher(input, GameMenuCommands.SHOW_TAX_RATE).find()) {
                showTaxRate();
            } else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.FEAR_RATE)).find()) {
                setFearRate(matcher);
            } else if (GameMenuCommands.getMatcher(input, GameMenuCommands.EXIT).find()) {
                break;
            } else
                globalMethods.invalidCommand();
        }
    }

    private void showPopularityFactors() {
        System.out.println(gameMenuMethods.getGame().getCurrentGovernance().showPopularityFactors());
    }

    private void showPopularity() {
        System.out.println(gameMenuMethods.getGame().getCurrentGovernance().showPopularity());
    }

    private void showFoodList() {
        System.out.println(gameMenuMethods.getGame().getCurrentGovernance().showFoodList());
    }

    private void foodRate(Matcher matcher) {
        int foodRate = Integer.parseInt(matcher.group("rateNumber"));
        if (foodRate < -2 || foodRate > 2) {
            System.out.println("food rate is invalid");
            return;
        }
    }

    private void showFoodRate() {
        System.out.println(gameMenuMethods.getGame().getCurrentGovernance().getPopularityFactors().getFoodRate());
    }

    private void setTaxRate(Matcher matcher) {
        int taxRate = Integer.parseInt(matcher.group("rateNumber"));
        if (taxRate < -3 || taxRate > 8) {
            System.out.println("tax rate is invalid");
            return;
        }
        gameMenuMethods.getGame().getCurrentGovernance().getPopularityFactors().setTaxRate(taxRate);
    }

    private void showTaxRate() {
        System.out.println(gameMenuMethods.getGame().getCurrentGovernance().getPopularityFactors().getTaxRate());
    }

    private void setFearRate(Matcher matcher) {
        int fearRate = Integer.parseInt(matcher.group("rateNumber"));
        if (fearRate < -5 || fearRate > 5) {
            System.out.println("fear rate is invalid");
            return;
        }
        gameMenuMethods.getGame().getCurrentGovernance().setFearRate(fearRate);
        System.out.println("fear rate was set successfully");
    }

}
