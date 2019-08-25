package com.kevin.app.levels;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.kevin.app.abstract_objects.Level;
import com.kevin.app.main.App;
import com.kevin.app.manager.Handler;
import com.kevin.app.objects.Player;
import com.kevin.app.states.Game;

public class LevelTwo extends Level implements KeyListener {

	private int textCount = 0;
	private String currentText[];

	public LevelTwo(Handler handler, Player player) {
		super(handler, 2, player);
		loadLevelText();
		this.currentText = nextLine();
		this.textCount = 1;
		handleNextText();
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

	public void handleNextText() {
		App.hud.showText = true;
		App.hud.textDisplay(currentText[0], currentText[1]);
	}

	@Override
	public Level nextLevel(Level level, Player player) {
		handler.removeAllBlocks();
		handler.removeAllEnemies();
		Level nextLevel = new LevelThree(handler, player);
		Game.mapConstraints = nextLevel.getMapConstraints();
		player.hasKey = false;
		Game.app.removeKeyListener(this);
		return nextLevel;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_SPACE) {
			textCount++;
			if (textCount <= 4) {
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