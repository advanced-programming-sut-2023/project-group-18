package com.example.view;

import com.example.controller.Commands.GameMenuCommands;
import com.example.controller.Methods.GameMenuMethods;
import com.example.controller.Methods.GlobalMethods;
import com.example.controller.Methods.MapMenuMethods;
import com.example.model.Buildings.BuildingType;
import com.example.model.Buildings.Tree;
import com.example.model.Buildings.TreeType;
import com.example.model.Map.Directions;
import com.example.model.Map.Texture;

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
        System.out.println("It's " + gameMenuMethods.getGame().getCurrentGovernance().getOwner().getUsername() + " turn!");
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            if (GameMenuCommands.getMatcher(input, GameMenuCommands.NEXT_TURN).find()) {
                break;
            } else if (GameMenuCommands.getMatcher(input, GameMenuCommands.SHOW_TURN).find()) {
                showTurn();
            } else if (GameMenuCommands.getMatcher(input, GameMenuCommands.SHOW_ASSET).find()) {
                showAssets();
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
            } else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.SET_TEXTURE)).find()) {
                setTexture(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.SET_TEXTURE_RECTANGLE)).find()) {
                setTextureRectangle(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.CLEAR)).find()) {
                clearTexture(matcher);
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
        // int count = gameMenuMethods.getCount(matcher);
        // String type = gameMenuMethods.getType(matcher);

    }

    private void repair() {

    }

    private void selectUnit() {
    }


    private void setTexture(Matcher matcher) {
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
        String textureName = fields.get("-t");
        Texture texture;
        if (!gameMenuMethods.areCoordinatesValid(x, y)) {
            System.out.println("your entered coordination's are not valid");
            return;
        } else if (!gameMenuMethods.isCellEmpty(x, y)) {
            System.out.println("the cell in this coordination is not empty!");
            return;
        } else if ((texture = Texture.getTextureByName(textureName)) == null) {
            System.out.println("the texture type is not valid");
            return;
        }
        gameMenuMethods.getGame().getGameMap().getCellByLocation(x, y).setTexture(texture);
    }

    private void setTextureRectangle(Matcher matcher) {

    }

    private void clearTexture(Matcher matcher) {
        int x = gameMenuMethods.getXCoordinate(matcher);
        int y = gameMenuMethods.getYCoordinate(matcher);
        if (!gameMenuMethods.inRange(x, y)) {
            System.out.println("the coordinates are not valid");
            return;
        }
        //TODO clear block
        System.out.println("cell was cleared successfully");
    }

    private void dropRock(Matcher matcher) {
        HashMap<String, String> fields = gameMenuMethods.sortFields(globalMethods.commandSplit(matcher.group("fields")));
        if (!gameMenuMethods.checkDropRockInvalidField(fields)) {
            System.out.println("you inserted an invalid field");
            return;
        } else if (!fields.get("-x").matches("\\d+") || !fields.get("-y").matches("\\d+")) {
            System.out.println("you didn't enter a number for coordinates!");
            return;
        }
        int x = Integer.parseInt(fields.get("-x"));
        int y = Integer.parseInt(fields.get("-y"));
        if (!gameMenuMethods.inRange(x, y)) {
            System.out.println("the coordinates are not valid");
            return;
        }
        String direction = fields.get("-d");
       if (Directions.getDirectionByName(direction) == null) {
           System.out.println("the input direction is invalid");
           return;
       }
        //TODO drop rock
        System.out.println("rock was dropped successfully");
    }

    private void dropTree(Matcher matcher) {
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
        String type = fields.get("-t");
        TreeType treeType;
        if (!gameMenuMethods.inRange(x, y)) {
            System.out.println("the coordinates are not valid");
            return;
        } else if (!gameMenuMethods.isTextureCompatible(BuildingType.TREE, x, y)) {
            System.out.println("the texture of this place is not valid to place a tree");
            return;
        } else if ((treeType = TreeType.getTreeTypeByName(type)) == null) {
            System.out.println("Invalid tree type");
            return;
        }
        gameMenuMethods.getGame().addTree(new Tree(gameMenuMethods.getGame().getGameMap().getCellByLocation(x, y), treeType));
        System.out.println("tree was placed successfully");
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
        if ((buildingType = gameMenuMethods.getBuildingType(buildingTypeName)) == null) {
            System.out.println("the building type is not valid");
            return;
        } else if (!gameMenuMethods.isNotEdge(buildingType, x, y)) {
            System.out.println("your entered coordination's are not valid");
            return;
        } else if (!gameMenuMethods.isAreaEmpty(buildingType, x, y)) {
            System.out.println("the cell in this coordination is not empty!");
            return;
        } else if (!gameMenuMethods.isTextureCompatible(buildingType, x, y)) {
            System.out.println("the texture for this cell is not compatible with the building");
            return;
        } else if (!gameMenuMethods.haveEnoughResources(buildingType)) {
            System.out.println("you don't have enough resources to build this building");
            return;
        } else if (!gameMenuMethods.isStockpileOK(buildingType, x, y)) {
            System.out.println("you have to drop stockpiles next to each other");
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

    private void showAssets() {
        System.out.println(gameMenuMethods.showAssets());
    }

    private void showTurn() {
        System.out.println(gameMenuMethods.getGame().showTurn());
    }

}
