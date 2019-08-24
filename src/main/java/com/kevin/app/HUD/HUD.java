package com.kevin.app.HUD;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.kevin.app.main.App;

public class HUD {

    public boolean showText = false;
    public boolean renderBlack = false;
    public boolean showBlack = false;
    private static String textToDisplay;
    private static BufferedImage image;
    private int blackTimeout;

    public HUD() {

    }

    public void tick() {
        if (blackTimeout > 0) {
            blackTimeout--;
            renderBlack = true;
        } else {
            blackTimeout = 0;
            renderBlack = false;
        }
    }

    public void render(Graphics2D g) {
        if (renderBlack || showBlack) {
            g.setColor(Color.black);
            g.fillRect(0, 0, App.WIDTH, App.HEIGHT);
        }
        if (showText) {
            g.drawImage(App.getImageFromTextures("textbox"), 50, 650, 800, 200, null);

            g.setColor(Color.black);
            g.setFont(App.gameFont.deriveFont(20f));
            g.drawString(textToDisplay, 100, 725);
        }
    }

    public static void textDisplay(String person, String text) {
        textToDisplay = text;
        image = App.getImageFromTextures(person);
    }

    public void setBlackTimeOut(int value) {
        blackTimeout = value;
    }
}