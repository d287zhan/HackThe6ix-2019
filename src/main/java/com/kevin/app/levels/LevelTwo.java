package com.kevin.app.levels;

import java.awt.Graphics2D;

import com.kevin.app.abstract_objects.Level;
import com.kevin.app.manager.Handler;
import com.kevin.app.objects.Clue;
import com.kevin.app.objects.Player;

public class LevelTwo extends Level {

	public LevelTwo(Handler handler) {
		super(handler, 2);
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
        return null;
	}

}