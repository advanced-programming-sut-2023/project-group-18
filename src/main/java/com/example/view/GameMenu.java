package com.example.view;

import com.example.controller.Commands.GameMenuCommands;
import com.example.controller.Methods.GameMenuMethods;
import com.example.controller.Methods.GlobalMethods;
import com.example.controller.Methods.MapMenuMethods;
import com.example.model.Buildings.BuildingType;
import com.example.model.Map.GameMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu {
    private static GameMenu gameMenu;
    private final GameMenuMethods gameMenuMethods;
    private final GlobalMethods globalMethods;

    private GameMenu() {
        gameMenuMethods = GameMenuMethods.gameMenuMethods();
        globalMethods = GlobalMethods.getInstance();
    }

    public static GameMenu getGameMenu() {
        return gameMenu == null ? gameMenu = new GameMenu() : gameMenu;
    }

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
            } else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.DROP_BUILDING)).find()) {
                dropBuilding(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.SELECT_BUILDING)).find()) {
                selectBuilding(matcher, scanner);
            } else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.CREATE_UNIT)).find()) {
                createUnit(matcher);
            } else if (GameMenuCommands.getMatcher(input, GameMenuCommands.REPAIR).find()) {
                repair();
            } else if (GameMenuCommands.getMatcher(input, GameMenuCommands.SELECT_UNIT).find()) {
                selectUnit();
            } else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MOVE_UNIT)).find()) {
                moveUnit(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.PATROL_UNIT)).find()) {
                patrolUnit(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.SET_STATE)).find()) {
                setState(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.ATTACK)).find()) {
                attack(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.AIR_ATTACK)).find()) {
                airAttack(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.POUR_OIL)).find()) {
                pourOil(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.DIG_TUNNEL)).find()) {
                digTunnel(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.BUILD)).find()) {
                buildEquipment(matcher);
            } else if (GameMenuCommands.getMatcher(input, GameMenuCommands.DISBAND_UNIT).find()) {
                disbandUnit();
            } else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.SET_TEXTURE)).find()) {
                setTexture(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.SET_TEXTURE_RECTANGLE)).find()) {
                setTextureRectangle(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.CLEAR)).find()) {
                clearUnits(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.DROP_ROCK)).find()) {
                dropRock(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.DROP_TREE)).find()) {
                dropTree(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.DROP_BUILDING)).find()) {
                dropBuilding(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.DROP_UNIT)).find()) {
                dropUnit(matcher);
            } else if (GameMenuCommands.getMatcher(input, GameMenuCommands.CURRENT_MENU).find()) {
                globalMethods.showCurrentMenu("game menu");
            } else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.SHOW_MAP)).find()) {
                showMap(matcher, scanner);
            } else {
                globalMethods.invalidCommand();
            }
        }
    }

    private void showPopularityFactors() {

    }

    private void showPopularity() {

    }

    private void showFoodList() {

    }

    private void foodRate(Matcher matcher) {
        int foodRate = Integer.parseInt(matcher.group("rateNumber"));
        if (foodRate < -2 || foodRate > 2) {
            System.out.println("food rate is invalid");
            return;
        }
    }

    private void showFoodRate() {

    }

    private void setTaxRate(Matcher matcher) {
        int taxRate = Integer.parseInt(matcher.group("rateNumber"));
        if (taxRate < -3 || taxRate > 8) {
            System.out.println("tax rate is invalid");
            return;
        }

    }

    private void showTaxRate() {
    }

    private void setFearRate(Matcher matcher) {
        int fearRate = Integer.parseInt(matcher.group("rateNumber"));
        if (fearRate < -5 || fearRate > 5) {
            System.out.println("fear rate is invalid");
            return;
        }
    }

    private void selectBuilding(Matcher matcher, Scanner scanner) {
        int xCoordinate = gameMenuMethods.getXCoordinate(matcher);
        int yCoordinate = gameMenuMethods.getYCoordinate(matcher);
        if (!gameMenuMethods.inRange(xCoordinate, yCoordinate)) {
            System.out.println("the coordination's are not in range");
            return;
        } else if (gameMenuMethods.isCellEmpty(xCoordinate, yCoordinate)) {
            System.out.println("there is no building in that cell!");
            return;
        } else if (!gameMenuMethods.isCurrentGovernanceOwner(xCoordinate, yCoordinate)) {
            System.out.println("you are not the owner of this building");
            return;
        }
        gameMenuMethods.selectBuilding(xCoordinate, yCoordinate, scanner);
    }

    private void createUnit(Matcher matcher) {
        int count = gameMenuMethods.getCount(matcher);
        String type = gameMenuMethods.getType(matcher);

    }

    private void repair() {

    }

    private void selectUnit() {
    }

    private void moveUnit(Matcher matcher) {

    }

    private void patrolUnit(Matcher matcher) {
        HashMap<String, String> fields = gameMenuMethods.sortFields(globalMethods.commandSplit(matcher.group("fields")));
        if (!gameMenuMethods.checkPatrolUnitInvalidField(fields)) {
            System.out.println("you inserted an invalid field");
        }
    }

    private void setState(Matcher matcher) {
        HashMap<String, String> fields = gameMenuMethods.sortFields(globalMethods.commandSplit(matcher.group("fields")));

    }

    private void attack(Matcher matcher) {

    }

    private void airAttack(Matcher matcher) {

    }

    private void pourOil(Matcher matcher) {

    }

    private void digTunnel(Matcher matcher) {

    }

    private void buildEquipment(Matcher matcher) {

    }

    private void disbandUnit() {

    }

    private void setTexture(Matcher matcher) {

    }

    private void setTextureRectangle(Matcher matcher) {

    }

    private void clearUnits(Matcher matcher) {

    }

    private void dropRock(Matcher matcher) {

    }

    private void dropTree(Matcher matcher) {

    }

    private void dropBuilding(Matcher matcher) {
        HashMap<String, String> fields = gameMenuMethods.sortFields(globalMethods.commandSplit(matcher.group("fields")));
        if (!gameMenuMethods.checkDropBuildingInvalidField(fields)) {
            System.out.println("you inserted an invalid field");
            return;
        } else if (!fields.get("-x").matches("\\d+") || !fields.get("-y").matches("\\d+")) {
            System.out.println("you didn't enter a number for coordinates!");
            return;
        }
        int x = Integer.parseInt(fields.get("-x"));
        int y = Integer.parseInt(fields.get("-y"));
        String buildingTypeName = fields.get("-t");
        BuildingType buildingType;
        if (!gameMenuMethods.areCoordinatesValid(x, y)) {
            System.out.println("your entered coordination's are not valid");
            return;
        } else if (!gameMenuMethods.isCellEmpty(x, y)) {
            System.out.println("the cell in this coordination is not empty!");
            return;
        } else if ((buildingType = gameMenuMethods.getBuildingType(buildingTypeName)) == null) {
            System.out.println("the building type is not valid");
            return;
        }
        if (!gameMenuMethods.isTextureCompatible(buildingType, x, y)) {
            System.out.println("the texture for this cell is not compatible with the building");
            return;
        } else if (!gameMenuMethods.haveEnoughResources(buildingType)) {
            System.out.println("you don't have enough resources to build this building");
            return;
        }
        gameMenuMethods.dropBuilding(buildingType, x, y);
        System.out.println("the building " + buildingTypeName + " in coordinates " + x + " and " + y + " was placed successfully");
    }

    private void dropUnit(Matcher matcher) {

    }

    private void showMap(Matcher matcher, Scanner scanner) {
        int x = gameMenuMethods.getXCoordinate(matcher);
        int y = gameMenuMethods.getYCoordinate(matcher);
        if (!gameMenuMethods.areCoordinatesValid(x, y)) {
            System.out.println("your entered coordination's are not valid");
            return;
        }
        MapMenuMethods.getInstance().run(scanner, x, y);
    }
}
