package com.kevin.app.levels;

import java.awt.Graphics2D;

import com.kevin.app.abstract_objects.Level;
import com.kevin.app.manager.Handler;
import com.kevin.app.objects.Player;
import com.kevin.app.states.Game;

public class LevelFive extends Level {

    public LevelFive(Handler handler, Player player) {
        super(handler, 5, player);
        loadLevelText();
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics2D g) {

    }

    public int currentLevel() {
        return level;
    }

    @Override
    public Level nextLevel(Level level, Player player) {
        handler.removeAllBlocks();
        handler.removeAllEnemies();
        Level nextLevel = new LevelFour(handler, player);
        Game.mapConstraints = nextLevel.getMapConstraints();
        player.hasKey = false;
        return nextLevel;
    }

}