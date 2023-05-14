package com.example.view;

import com.example.controller.Commands.ShopMenuCommands;
import com.example.controller.Methods.GameMenuMethods;
import com.example.controller.Methods.GlobalMethods;
import com.example.controller.Methods.MarketMethods;
import com.example.model.Assets.Asset;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ShopMenu {
    private static ShopMenu shopMenu;
    private final GameMenuMethods gameMenuMethods;
    private final GlobalMethods globalMethods;
    private final MarketMethods marketMethods;

    private ShopMenu() {
        gameMenuMethods = GameMenuMethods.gameMenuMethods();
        globalMethods = GlobalMethods.getInstance();
        marketMethods = MarketMethods.getMarketMethods();
    }
    
    public static ShopMenu getShopMenu() {
        return shopMenu == null ? shopMenu = new ShopMenu() : shopMenu;
    }

    public void run(Scanner scanner) {
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            if (ShopMenuCommands.getMatcher(input, ShopMenuCommands.SHOW_PRICE_LIST).find()) {
                showPriceList();
            } else if ((matcher = ShopMenuCommands.getMatcher(input, ShopMenuCommands.BUY)).find()) {
                shop(matcher, true);
            } else if ((matcher = ShopMenuCommands.getMatcher(input, ShopMenuCommands.SELL)).find()) {
                shop(matcher, false);
            } else if(ShopMenuCommands.getMatcher(input, ShopMenuCommands.EXIT).find()) {
                break;
            } else globalMethods.invalidCommand();
        }
    }

    private void showPriceList() {
        System.out.println(gameMenuMethods.getGame().getCurrentGovernance().showPriceList());
    }

    private void shop(Matcher matcher, boolean flag) {
        HashMap<String, String> fields = marketMethods.getFields(matcher);
        int amount = Integer.parseInt(fields.get("-a"));
        String name = fields.get("-i");
        if (Asset.getAssetByName(name) == null) {
            System.out.println("item with this name doesn't exist");
            return;
        }
        Asset asset = Asset.getAssetByName(name);
        if (flag) {
            if (!marketMethods.hasEnoughMoney(asset, amount)) {
                System.out.println("you don't have enough money to buy the item");
                return;
            } else if (!marketMethods.canAddAssetToStorage(asset, amount)) {
                System.out.println("you don't have enough storage to buy this asset");
                return;
            }
            gameMenuMethods.getGame().getCurrentGovernance().buyItem(asset, amount);
            System.out.println("purchase was successful");
        } else {
            if (!marketMethods.hasEnoughAsset(asset, amount)) {
                System.out.println("you don't have enough of this asset to sell");
                return;
            }
            gameMenuMethods.getGame().getCurrentGovernance().sellItem(asset, amount);
            System.out.println("sell was successful");
        }
    }

}
