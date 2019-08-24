package com.kevin.app.levels;

import java.awt.Graphics2D;

import com.kevin.app.abstract_objects.Level;
import com.kevin.app.manager.Handler;
import com.kevin.app.objects.Player;
import com.kevin.app.states.Game;

public class LevelOne extends Level {

	private boolean isBoss = false;

	public LevelOne(Handler handler) {
		super(handler, 1);
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
        Level nextLevel = new LevelTwo(handler);
        Game.mapConstraints = nextLevel.getMapConstraints();
        player.setPosition(100, 100);
        return nextLevel;
	}

}