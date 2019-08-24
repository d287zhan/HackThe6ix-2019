package com.kevin.app.abstract_objects;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

import com.kevin.app.main.App;
import com.kevin.app.manager.BufferedImageLoader;
import com.kevin.app.manager.Handler;
import com.kevin.app.objects.Player;

public abstract class Level {
    protected int level;
    protected int[] mapConstraints;
    protected Handler handler;
    protected String clue;
    protected Queue<String> text = new LinkedList();
    protected Queue<String> textOwner = new LinkedList();

    public Level(Handler handler, int level) {
        this.handler = handler;
        this.level = level;
        loadLevelClue();
        mapConstraints = loadLevel(clue);
    }

    protected void loadLevelClue() {
        InputStream stream = this.getClass().getResourceAsStream("/clues/level" + level + ".txt");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
            clue = br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void loadLevelText() {
        InputStream stream = this.getClass().getResourceAsStream("/text/level" + level + ".txt");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] temp = line.split(":");
                String person = temp[0];
                String displayText = temp[1];
                textOwner.add(person);
                text.add(displayText);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] nextLine() {
        if (text.isEmpty())
            return null;
        return new String[] { textOwner.poll(), text.poll() };
    }

    public int[] getMapConstraints() {
        return mapConstraints;
    }

    public int getLevel(){
        return this.level;
    }

    protected int[] loadLevel(String clue) {
        BufferedImageLoader loader = new BufferedImageLoader();
        int[] mapConstraints = App.loadImageLevel(loader.loadImage("/levels/level" + level + ".png"), this.handler,
                clue);
        return mapConstraints;
    }

    public abstract void tick();

    public abstract void render(Graphics2D g);

    public abstract Level nextLevel(Level level, Player player);

}