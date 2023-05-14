package com.example.controller.Methods;

import com.example.model.Assets.Asset;
import com.example.view.KeepMenu;
import com.example.view.ShopMenu;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;

public class MarketMethods implements SelectBuildingMenuMethods{
    private static MarketMethods marketMethods;
    private final GameMenuMethods gameMenuMethods;
    private MarketMethods() {
        gameMenuMethods = GameMenuMethods.gameMenuMethods();
    }
    public static MarketMethods getMarketMethods() {
        return marketMethods == null ? marketMethods = new MarketMethods() : marketMethods;
    }
    @Override
    public void run(Scanner scanner) {
        ShopMenu.getShopMenu().run(scanner);
    }

    public boolean checkShopInvalidField(HashMap<String, String> fields) {
        for (String string : fields.keySet()) {
            if (!string.equals("-i") && !string.equals("-a"))
                return false;
        }
        return fields.size() == 2;
    }

    public HashMap<String, String> getFields(Matcher matcher) {
        HashMap<String, String> output = new HashMap<>();
        if (matcher.group("name") != null) {
            output.put("-i", matcher.group("name"));
        } else output.put("-1", matcher.group("name2"));
        if (matcher.group("amount") != null) {
            output.put("-a", matcher.group("amount"));
        } else output.put("-a", matcher.group("amount2"));
        return output;
    }

    public boolean hasEnoughMoney(Asset asset, int count) {
        return gameMenuMethods.getGame().getCurrentGovernance().getGold() >= count * asset.getBuyPrice();
    }

    public boolean canAddAssetToStorage(Asset asset, int count) {
        return gameMenuMethods.getGame().getCurrentGovernance().canAddAssetToStorage(asset, count);
    }

    public boolean hasEnoughAsset(Asset asset, int amount) {
        return gameMenuMethods.getGame().getCurrentGovernance().canRemoveAssetFromStorage(asset, amount);
    }
}
