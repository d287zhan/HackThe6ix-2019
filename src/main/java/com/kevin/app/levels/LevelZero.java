package com.kevin.app.levels;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.kevin.app.abstract_objects.Level;
import com.kevin.app.main.App;
import com.kevin.app.manager.Handler;
import com.kevin.app.objects.Player;
import com.kevin.app.states.Game;

public class LevelZero extends Level implements KeyListener {

    private boolean isBoss = false;
    private int[] textBreakPoints = new int[] { 6, 12 };
    private int textCount = 0;
    private String currentText[];

    public LevelZero(Handler handler) {
        super(handler, 0);
        loadLevelText();
        this.currentText = nextLine();
        this.textCount = 1;
        handleNextText();
    }

    public void handleNextText() {
        if (textCount < 6) {
            App.hud.showBlack = true;
        } else {
            App.hud.showBlack = false;
        }
        App.hud.showText = true;
        App.hud.textDisplay(currentText[0], currentText[1]);
    }

    public void nextLevel(Level level, Player player) {
        handler.removeAllBlocks();
        Level nextLevel = new LevelOne(handler);
        level = nextLevel;
        Game.mapConstraints = level.getMapConstraints();
        player.setPosition(100, 100);
    }

    @Override
    public void tick() {

    }

    public int currentLevel() {
        return level;
    }

    @Override
    public void render(Graphics2D g) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            textCount++;
            if (textCount <= 12) {
                currentText = nextLine();
                handleNextText();
            } else {
                App.hud.showText = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}