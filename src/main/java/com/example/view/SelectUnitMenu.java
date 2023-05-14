package com.example.view;

import com.example.controller.Commands.GameMenuCommands;
import com.example.controller.Methods.GameMenuMethods;
import com.example.controller.Methods.GlobalMethods;
import com.example.model.Map.Directions;
import com.example.model.People.State;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;

public class SelectUnitMenu {
    private static SelectUnitMenu selectUnitMenu;
    private final GlobalMethods globalMethods;
    private final GameMenuMethods gameMenuMethods;

    private SelectUnitMenu() {
        globalMethods = GlobalMethods.getInstance();
        gameMenuMethods = GameMenuMethods.gameMenuMethods();
    }

    public static SelectUnitMenu selectUnitMenu() {
        return selectUnitMenu == null ? selectUnitMenu = new SelectUnitMenu() : selectUnitMenu;
    }
    
    public void run(Scanner scanner) {
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MOVE_UNIT)).find()) {
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
            } else if (GameMenuCommands.getMatcher(input, GameMenuCommands.EXIT).find()) {
                break;
            } else if (GameMenuCommands.getMatcher(input, GameMenuCommands.CANCEL_PATROL).find()) {
                cancelPatrol();
            } else globalMethods.invalidCommand();
        }
    }
    
    private void moveUnit(Matcher matcher) {
        int x = gameMenuMethods.getXCoordinate(matcher);
        int y = gameMenuMethods.getYCoordinate(matcher);
        if (!gameMenuMethods.inRange(x,y)) {
            System.out.println("your input coordinates are not valid");
            return;
        }
        gameMenuMethods.move(x,y);
        System.out.println("selected units moved successfully");
    }
    
    private void patrolUnit(Matcher matcher) {
        HashMap<String, String> fields = gameMenuMethods.sortFields(globalMethods.commandSplit(matcher.group("fields")));
        if (!gameMenuMethods.checkPatrolUnitInvalidField(fields)) {
            System.out.println("you inserted an invalid field");
            return;
        }
        int x1 = Integer.parseInt(fields.get("-x1"));
        int y1 = Integer.parseInt(fields.get("-y1"));
        int x2 = Integer.parseInt(fields.get("-x2"));
        int y2 = Integer.parseInt(fields.get("-y2"));
        if (!gameMenuMethods.inRange(x1, y1) || !gameMenuMethods.inRange(x2, y2)) {
            System.out.println("coordinates are not valid");
            return;
        }
        // gameMenuMethods.patrol(x1, y1, x2, y2);
        System.out.println("patrol unit successful");
    }
    
    
    private void setState(Matcher matcher) {
        String stateName = matcher.group("state");
        if (!State.isStateNameCorrect(stateName)) {
            System.out.println("state name is not correct");
            return;
        }
        gameMenuMethods.getGame().getSelectedUnit().setState(State.getStateByName(stateName));
        System.out.println("state name was set successfully");
    }
    
    private void attack(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("xCoordinate"));
        int y = Integer.parseInt(matcher.group("yCoordinate"));
        if (!gameMenuMethods.inRange(x, y)) {
            System.out.println("coordinates are not valid");
            return;
        }
        gameMenuMethods.attack(x, y);
        System.out.println("attack was successful");
    }
    
    private void airAttack(Matcher matcher) {
        int x = gameMenuMethods.getXCoordinate(matcher);
        int y = gameMenuMethods.getYCoordinate(matcher);
        if (!gameMenuMethods.inRange(x, y)) {
            System.out.println("coordinates are not valid");
            return;
        }
       gameMenuMethods.airAttack(x, y);
        System.out.println("air attack was successful");
    }
    
    private void pourOil(Matcher matcher) {
        String direcion = matcher.group("direction");
        if (Directions.getDirectionByName(direcion) == null) {
            System.out.println("the input direction is invalid");
            return;
        }
//        gameMenuMethods.pourOil(direcion);
        System.out.println("pour oil was operated successfully");
    }
    
    private void digTunnel(Matcher matcher) {
//        gameMenuMethods.digTunnel()
    }
    
    private void buildEquipment(Matcher matcher) {
        // String equipmentName = gameMenuMethods.getEquipmentName(matcher.group("equipmentName"));
//        gameMenuMethods.buildAttackTool();
    }
    
    private void disbandUnit() {
       gameMenuMethods.disbandUnit();
    }
    
    private void cancelPatrol() {
        gameMenuMethods.cancelPatrol();
    }
    
}
