package com.kevin.app.manager;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Animation {
    private int speed;
    private int frames;

    private int index = 0;
    private int count = 0;
    private boolean isRunning = false;

    private BufferedImage[] images;
    private BufferedImage currentImg;

    public Animation(int speed, BufferedImage image[]) {

        this.speed = speed;

        frames = image.length;

        images = new BufferedImage[frames];

        for (int i = 0; i < frames; i++) {
            images[i] = image[i];
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void addFrame() {
    }

    public void runAnimation() {
        index++;

        if (index > speed) {
            index = 0;
            nextFrame();
        }

    }

    public void nextFrame() {

        if (count < frames) {
            currentImg = images[count];
            count++;
        }else {
            count = 0;
        }
    }

    public void drawAnimation(Graphics2D g, int x, int y) {
        g.drawImage(currentImg, x, y, null);
    }

    public void drawAnimation(Graphics2D g, int x, int y, int scaleX, int scaleY) {
        g.drawImage(currentImg, x, y, scaleX, scaleY, null);
    }
}