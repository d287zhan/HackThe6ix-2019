package com.kevin.app.states;

import java.awt.Color;
import java.awt.Graphics2D;

import com.kevin.app.abstract_objects.Level;
import com.kevin.app.ids.ObjectIds;
import com.kevin.app.ids.PlayerId;
import com.kevin.app.levels.LevelFour;
import com.kevin.app.levels.LevelTwo;
import com.kevin.app.levels.LevelZero;
import com.kevin.app.main.App;
import com.kevin.app.main.GameState;
import com.kevin.app.manager.Camera;
import com.kevin.app.manager.Handler;
import com.kevin.app.manager.KeyInputHandler;
import com.kevin.app.objects.Player;

public class Game extends State {

    public static Player player;
    public static App app;
    public static Camera camera;
    public static Level level;
    public static Handler handler;
    public static int[] mapConstraints;

    public Game(int player_x, int player_y, App app, Handler handler) {
        this.handler = handler;
        this.app = app;
        initializePlayer(player_x, player_y);
        this.level = new LevelZero(handler, player);
        mapConstraints = this.level.getMapConstraints();
        App.hud.setPlayer(player);
        camera = new Camera(0, 0);
        app.addKeyListener(new KeyInputHandler(player));
        app.addKeyListener((LevelZero) level);
    }

    public void render(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, App.WIDTH, App.HEIGHT);
        g.translate(-camera.getX(), -camera.getY());
        handler.render(g);
        player.render(g);
        g.translate(camera.getX(), camera.getY());
        App.hud.render(g);
    }

    public void tick() {
        camera.tick(player);
        handler.tick();
        player.tick();
        App.hud.tick();
    }

    public void initializePlayer(int player_x, int player_y) {
        player = new Player(player_x, player_y, ObjectIds.Player, PlayerId.Human, handler);
    }

    public int getStateId() {
        return GameState.GAME;
    }

    public static void handleLevelChange() {
        App.hud.renderBlack = true;
        App.hud.setBlackTimeOut(10);
        level = level.nextLevel(level, player);
        if (level.getLevel() == 2) {
            app.addKeyListener((LevelTwo) level);
        } else if (level.getLevel() == 4) {
            app.addKeyListener((LevelFour) level);
        }
    }
}