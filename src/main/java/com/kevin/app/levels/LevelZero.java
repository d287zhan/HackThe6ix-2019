package com.kevin.app.levels;

import java.awt.Graphics2D;

import com.kevin.app.abstract_objects.Level;
import com.kevin.app.manager.Handler;
import com.kevin.app.objects.Clue;

public class LevelZero extends Level {

    private boolean isBoss = false;

    public LevelZero(Handler handler) {
        super(handler, 0);
        loadLevelText();
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

}