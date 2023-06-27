package com.example.model.map;

import com.example.view.images.TextureImages;

import javafx.scene.image.Image;

public class Texture {
    private TextureImages textureImages;
    private Image image;

    public Texture(TextureImages textureImages) {
        setTextureImages(textureImages);
    }

    public TextureImages getTextureImages() {
        return textureImages;
    }

    public Image getImage() {
        return image;
    }

    public void setTextureImages(TextureImages textureImages) {
        this.textureImages = textureImages;
        this.image = textureImages.getRandomImage();
    }


    /*GROUND ("ground", BLACK_BACKGROUND_BRIGHT, true),
    GRAVEL ("gravel", YELLOW_BACKGROUND, true),
    SLAB ("slab", WHITE_BACKGROUND, false),
    STONE ("stone", RED_BACKGROUND_BRIGHT, true),
    IRON ("iron", WHITE_BACKGROUND_BRIGHT, true),
    LAWN ("lawn", GREEN_BACKGROUND,true),
    RARE_GRASSLAND ("rare grassland", YELLOW_BACKGROUND_BRIGHT, true),
    OVERGROWN_GRASSLAND ("overgrown grassland", GREEN_BACKGROUND_BRIGHT, true),
    PETROLEUM ("petroleum", PURPLE_BACKGROUND, true),
    PLAIN ("plain", BLUE_BACKGROUND, true),
    SHALLOW_WATER ("shallow water", BLUE_BACKGROUND,false),
    RIVER ("river", BLUE_BACKGROUND, false),
    POOL ("pool", BLUE_BACKGROUND, false),
    BEACH ("beach", BLUE_BACKGROUND, false),
    SEA ("sea", BLUE_BACKGROUND, false),
    HOLE("hole", BLUE_BACKGROUND, false),
    ROCK ("rock", BLACK_BACKGROUND, false)
    ;
    private static final List<Texture> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public final String name;
    public final String color;
    private final boolean isReachable;

    Texture(String name, String color, boolean isReachable) {
        this.name = name;
        this.color = color;
        this.isReachable = isReachable;
    }

    public static Texture getTextureByName(String name) {
        for (Texture values : Texture.values())
            if (values.name.equals(name)) return values;
        return null;
    }

    public static Texture getARondomTexture() {
        if (RANDOM.nextInt(4) == 0) return GROUND;
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public boolean isReachable() {
        return isReachable;
    }
*/
}