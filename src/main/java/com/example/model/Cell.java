package com.example.model;

public class Cell implements ConsoleColors {
    private Object object;
    private Texture texture;
    private final int xCordinate;
    private final int yCordinate;

    protected Cell(int xCordinate, int yCordinate) {
        this.xCordinate = xCordinate;
        this.yCordinate = yCordinate;
        texture = Texture.getARondomTexture();
    }

    public Object getObject() {
        return object;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getxCordinate() {
        return xCordinate;
    }

    public int getyCordinate() {
        return yCordinate;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public String showDetails() {
        // TODO: have to complete
        return "Texture: " + texture.name
            + "\n"
                ;
    }

    @Override
    public String toString() {
        return texture.color + "      " + RESET;
    }

}
