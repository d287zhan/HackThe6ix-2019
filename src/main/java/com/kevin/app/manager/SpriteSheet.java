package com.kevin.app.manager;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    public SpriteSheet(BufferedImage image) {
        this.image = image;
    }

    private BufferedImage image;

    public BufferedImage grabImage(int x, int y, int width, int height) {
        BufferedImage img = image.getSubimage(x, y, width, height);
        return img;
    }

    public BufferedImage grabLinearImage(int col, int row, int width, int height) {
        BufferedImage img = image.getSubimage((col * width) - width, (row * height) - height, width, height);
        return img;
    }
}