package com.example.view;

import com.example.controller.Commands.TradeMenuCommands;
import com.example.controller.Methods.GameMenuMethods;
import com.example.controller.Methods.GlobalMethods;
import com.example.controller.Methods.TradeMenuMethods;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;

public class TradeMenu {
    private static TradeMenu tradeMenu;
    private final TradeMenuMethods tradeMenuMethods;
    private final GlobalMethods globalMethods;
    private final GameMenuMethods gameMenuMethods;

    private TradeMenu() {
        tradeMenuMethods = TradeMenuMethods.getTradeMenuMethods();
        globalMethods = GlobalMethods.getInstance();
        gameMenuMethods = GameMenuMethods.gameMenuMethods();
    }
    public TradeMenu getTradeMenu() {
        return tradeMenu == null ? tradeMenu = new TradeMenu() : tradeMenu;
    }
    public void run(Scanner scanner) {
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            if ((matcher = TradeMenuCommands.getMatcher(input, TradeMenuCommands.TRADE)).find()) {
                trade(matcher);
            } else if (TradeMenuCommands.getMatcher(input, TradeMenuCommands.TRADE_LIST).find()) {
                tradeList();
            } else if ((matcher = TradeMenuCommands.getMatcher(input, TradeMenuCommands.ACCEPT_TRADE)).find()) {
                tradeAccept(matcher);
            } else if (TradeMenuCommands.getMatcher(input, TradeMenuCommands.TRADE_HISTORY).find()) {
                tradeHistory();
            } else {
                globalMethods.invalidCommand();
            }
        }
    }

    private void trade(Matcher matcher) {
        HashMap<String, String> fields = gameMenuMethods.sortFields(globalMethods.commandSplit(matcher.group("fields")));
        if (!tradeMenuMethods.checkTradeInvalidField(fields)) {
            System.out.println("you inserted an invalid field");
            return;
        }
        if (!fields.get("-a").matches("\\d+") || !fields.get("-p").matches("\\d+")) {
            System.out.println("you have invalid input for a number");
            return;
        }
        String resourceType = fields.get("-t");
        int amount = Integer.parseInt(fields.get("-a"));
        int price = Integer.parseInt(fields.get("-p"));
        String message = fields.get("-m");
        //TODO trade
        System.out.println("trade successful");
    }

    private void tradeList() {
        //TODO show trade list
    }

    private void tradeAccept(Matcher matcher) {
        int id = tradeMenuMethods.getId(matcher);
        String message = tradeMenuMethods.getMessage(matcher);
//        if (!tradeIdValidation()) {
//            System.out.println("trade id is not valid");
//            return;
//        }
        //TODO trade id validation - doing trade -
        System.out.println("trade was successfully accepted");
    }

    private void tradeHistory() {
        //TODO show trade history
    }
}
