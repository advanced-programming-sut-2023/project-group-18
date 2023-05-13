package com.example.view;

import com.example.controller.Commands.TradeMenuCommands;
import com.example.controller.Methods.GlobalMethods;
import com.example.controller.Methods.TradeMenuMethods;

import java.util.Scanner;
import java.util.regex.Matcher;

public class TradeMenu {
    private static TradeMenu tradeMenu;
    private final TradeMenuMethods tradeMenuMethods;
    private final GlobalMethods globalMethods;

    private TradeMenu() {
        tradeMenuMethods = TradeMenuMethods.getTradeMenuMethods();
        globalMethods = GlobalMethods.getInstance();
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
//                trade(matcher);
            } else if (TradeMenuCommands.getMatcher(input, TradeMenuCommands.TRADE_LIST).find()) {
//                tradeList(matcher);
            } else if ((matcher = TradeMenuCommands.getMatcher(input, TradeMenuCommands.ACCEPT_TRADE)).find()) {
                tradeAccept(matcher);
            } else if (TradeMenuCommands.getMatcher(input, TradeMenuCommands.TRADE_HISTORY).find()) {
                tradeHistory();
            } else {
                globalMethods.invalidCommand();
            }
        }
    }

    private void tradeAccept(Matcher matcher) {

    }

    private void tradeHistory() {

    }
}
