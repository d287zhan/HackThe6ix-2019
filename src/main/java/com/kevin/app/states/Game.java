package com.kevin.app.states;

import java.awt.Graphics2D;

import com.kevin.app.HUD.HUD;
import com.kevin.app.abstract_objects.Level;
import com.kevin.app.ids.ObjectIds;
import com.kevin.app.ids.PlayerId;
import com.kevin.app.levels.LevelZero;
import com.kevin.app.main.App;
import com.kevin.app.main.GameState;
import com.kevin.app.manager.Camera;
import com.kevin.app.manager.Handler;
import com.kevin.app.manager.KeyInputHandler;
import com.kevin.app.objects.Player;

public class Game extends State {

    private static Player player;
    private App app;
    private Camera camera;
    public static Level level;
    private static Handler handler;
    public static int[] mapConstraints;

    public Game(int player_x, int player_y, App app, Handler handler) {
        this.handler = handler;
        this.app = app;
        this.level = new LevelZero(handler);
        mapConstraints = this.level.getMapConstraints();
        initializePlayer(player_x, player_y);
        camera = new Camera(0, 0);
        app.addKeyListener(new KeyInputHandler(player));
        app.addKeyListener((LevelZero) level);
    }

    public void render(Graphics2D g) {
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
        level.nextLevel(level, player);
    }
}