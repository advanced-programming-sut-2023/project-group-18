package com.example.model.people;

import com.example.view.Main;

import javafx.scene.image.Image;

public class UnitImages {
    private final String name;
    private final Image icon;
    private final Image standing;
    private final Image[] move;
    private final Image[] attack;

    protected UnitImages(String name) {
        this.name = name;
        icon = new Image(Main.class.getResource("/images/units/" + name + "/icon.png").toExternalForm());
        standing = new Image(Main.class.getResource("/images/units/" + name + "/standing.png").toExternalForm());
        move = new Image[4];
        attack = new Image[2];
        for (int i = 1; i < 5; i++)
            move[i - 1] = new Image(Main.class.getResource("/images/units/" + name + "/move" + i + ".png").toExternalForm());
        for (int i = 1; i < 3; i++)
            attack[i - 1] = new Image(Main.class.getResource("/images/units/" + name + "/attack" + i + ".png").toExternalForm());
    }

    public String getName() {
        return name;
    }

    public Image getIcon() {
        return icon;
    }

    public Image getStanding() {
        return standing;
    }

    public Image[] getMove() {
        return move;
    }

    public Image[] getAttack() {
        return attack;
    }

}
