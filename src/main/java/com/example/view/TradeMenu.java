package com.example.view;

import com.example.controller.Commands.GameMenuCommands;
import com.example.controller.Commands.TradeMenuCommands;
import com.example.controller.Methods.GameMenuMethods;
import com.example.controller.Methods.GlobalMethods;
import com.example.controller.Methods.TradeMenuMethods;
import com.example.model.Governance;
import com.example.model.Assets.Asset;

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

    public static TradeMenu getTradeMenu() {
        return tradeMenu == null ? tradeMenu = new TradeMenu() : tradeMenu;
    }

    public void run(Scanner scanner) {
        String input;
        Matcher matcher;
        System.out.println(gameMenuMethods.getGame().getCurrentGovernance().showNotifications());
        while (true) {
            input = scanner.nextLine();
            if ((matcher = TradeMenuCommands.getMatcher(input, TradeMenuCommands.TRADE)).find()) {
                trade(matcher, scanner);
            } else if (TradeMenuCommands.getMatcher(input, TradeMenuCommands.TRADE_LIST).find()) {
                tradeList();
            } else if (GameMenuCommands.getMatcher(input, GameMenuCommands.EXIT).find()) {
                break;
            } else if ((matcher = TradeMenuCommands.getMatcher(input, TradeMenuCommands.ACCEPT_TRADE)).find()) {
                tradeAccept(matcher);
            } else if (TradeMenuCommands.getMatcher(input, TradeMenuCommands.TRADE_HISTORY).find()) {
                tradeHistory();
            } else {
                globalMethods.invalidCommand();
            }
        }
    }

    void trade(Matcher matcher, Scanner scanner) {
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
        Asset asset;
        if ((asset = Asset.getAssetByName(resourceType)) == null) {
            System.out.println("Invalid asset name");
            return;
        }
        int amount = Integer.parseInt(fields.get("-a"));
        if (!gameMenuMethods.getGame().getCurrentGovernance().canRemoveAssetFromStorage(asset, amount)){
            System.out.println("you don't have enough asset");
            return;
        }
        System.out.println(gameMenuMethods.showGovernancesInTrade());
        String index = scanner.nextLine();
        int indexNumber;
        Governance governance;
        if (!index.matches("\\d") || (indexNumber = Integer.parseInt(index)) == 0 ||
             (indexNumber > gameMenuMethods.getGame().getGovernances().size()) ||
             (governance = gameMenuMethods.getGame().getGovernances().get(indexNumber - 1)) == 
             gameMenuMethods.getGame().getCurrentGovernance()) {
            System.out.println("You enter an invalid index");
            return;
        }
        int price = Integer.parseInt(fields.get("-p"));
        String message = fields.get("-m");
        gameMenuMethods.getGame().getCurrentGovernance().requestTrade(governance, asset, amount, price, message);
        System.out.println("trade successful");
    }

    public void tradeList() {
        System.out.println(gameMenuMethods.getGame().getCurrentGovernance().showTradeList());
    }

    public void tradeAccept(Matcher matcher) {
        int id = tradeMenuMethods.getId(matcher);
        String message = tradeMenuMethods.getMessage(matcher);
        if (!gameMenuMethods.getGame().getCurrentGovernance().canAcceptTrade(id)) {
            System.out.println("you can't accept this trade!");
            return;
        }
        gameMenuMethods.getGame().getCurrentGovernance().acceptTrade(id, message);
        System.out.println("trade was successfully accepted");
    }

    public void tradeHistory() {
        System.out.println(gameMenuMethods.getGame().getCurrentGovernance().showTradeHistory());
    }

}
