package com.example.model.map;

import com.example.view.images.TextureImages;

import javafx.scene.image.Image;

public class Texture {
    private TextureImages textureImages;
    private Image image;

    public Texture(TextureImages textureImages) {
        setTextureImages(textureImages);
    }

    public Texture(int symbol) {
        this(TextureImages.values()[symbol]);
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

}