package com.example.model.buildings;

import com.example.model.Governance;
import com.example.model.map.Tile;
import com.example.model.people.SoldierType;

public class Barracks extends Building{
    public Barracks(BuildingType buildingType, Governance governance, Tile tile) {
        super(buildingType, governance, tile);
    }

    public boolean canMakeSoldier(SoldierType soldier){
        if (governance.getRemainingNonMilitary() > 0)
            if (governance.getGold() > soldier.getCost())
                if (governance.canRemoveAssetFromStorage(soldier.getWeapon(),1))
                    return governance.canRemoveAssetFromStorage(soldier.getArmor(),1);
        return false;
    }

    public void makeSoldier(SoldierType soldier){
        // governance.addSoldier(this.cell,soldier);
        governance.removeAssetFromStorage(soldier.getWeapon(),1);
        governance.removeAssetFromStorage(soldier.getArmor(),1);
        governance.addGold(-soldier.getCost());
        governance.removeRemainingCharacter();
    }

    @Override
    public boolean isReachable() {
        return false;
    }
}
