package com.kevin.app.HUD;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import com.kevin.app.main.App;
import com.kevin.app.objects.Player;
import com.kevin.app.states.Game;

public class HUD {

    public boolean showText = false;
    public boolean renderBlack = false;
    public boolean showBlack = false;
    private static String textToDisplay;
    public static boolean isDead = false;
    private static BufferedImage image;
    private int blackTimeout;
    private Player player;

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

        // if (Game.level.getLevel() == 5) {
        // Point2D center = new Point2D.Float(player.getX() + 57 / 2, player.getY() + 87
        // / 2);
        // float dist[] = { 0.0f, 1.0f };
        // Color[] colors = { Color.white, Color.black };
        // RadialGradientPaint p = new RadialGradientPaint(center, 120, dist, colors);

        // g.setPaint(p);
        // g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        // g.fillRect(0, 0, App.WIDTH, App.HEIGHT);
        // }

        g.drawImage(App.getImageFromTextures("skull"), 750, 10, 64, 64, null);
        g.setFont(App.gameFont.deriveFont(20f));
        g.setColor(Color.white);
        g.drawString("" + player.deaths, 820, 50);

        if (renderBlack || showBlack) {
            g.setColor(Color.black);
            g.fillRect(0, 0, App.WIDTH, App.HEIGHT);
        }

        if (isDead) {
            g.setColor(Color.white);
            g.setFont(App.gameFont.deriveFont(20f));
            g.drawString("YOU ARE DEAD", 320, 370);
        }

        if (player.showClue) {
            g.drawImage(App.getImageFromTextures("empty_clue"), 95, 50, 750, 750, null);
            g.setFont(App.gameFont.deriveFont(20f));
            if (Game.level.getLevel() == 1) {
                g.drawString(player.clueString, 230, 440);
            } else if (Game.level.getLevel() == 3) {
                g.drawString(player.clueString, 230, 440);
            } else if (Game.level.getLevel() == 5) {
                g.drawString(player.clueString, 230, 440);
            }
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

    public void setPlayer(Player player) {
        this.player = player;
    }
}