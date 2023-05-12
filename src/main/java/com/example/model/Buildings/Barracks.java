package com.example.model.Buildings;

import com.example.model.Governance;
import com.example.model.Map.Cell;
import com.example.model.People.SoldierType;

public class Barracks extends Building{
    public Barracks(BuildingType buildingType, Governance governance, Cell cell) {
        super(buildingType, governance, cell);
    }

    public boolean canMakeSoldier(SoldierType soldier){
        if (governance.getRemainingNonMilitary() > 0)
            if (governance.getGold() > soldier.getCost())
                if (governance.canRemoveAssetFromStorage(soldier.getWeapon(),1))
                    return governance.canRemoveAssetFromStorage(soldier.getArmor(),1);
        return false;
    }

    public void makeSoldier(SoldierType soldier){
        governance.addSoldier(this.cell,soldier);
        governance.removeAssetFromStorage(soldier.getWeapon(),1);
        governance.removeAssetFromStorage(soldier.getArmor(),1);
        governance.addGold(-soldier.getCost());
        governance.removeRemainingCharacter();
    }
}
