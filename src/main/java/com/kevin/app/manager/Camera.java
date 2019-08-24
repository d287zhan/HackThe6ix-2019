package com.kevin.app.manager;

import com.kevin.app.abstract_objects.PlayerObject;
import com.kevin.app.main.App;
import com.kevin.app.states.Game;

public class Camera {

    private float x, y;

    private float speed = 0.05f;

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void tick(PlayerObject player) {

        if (x + ((player.getX() - x) - App.WIDTH / 2) * speed >= 0
                && x + ((player.getX() - x) - App.WIDTH / 2) * speed <= (Game.mapConstraints[0] - App.WIDTH)) {
            x += ((player.getX() - x) - App.WIDTH / 2) * speed;
        }
        if (y + ((player.getY() - y) - App.HEIGHT / 2) * speed >= 0
                && y + ((player.getY() - y) - App.HEIGHT / 2) * speed <= (Game.mapConstraints[1] - (App.HEIGHT))) {
            y += ((player.getY() - y) - App.HEIGHT / 2) * speed;
        }

        // if ((x + ((player.getX() - x) - App.WIDTH / 2) * speed) <= 0) {
        // x = 0;
        // } else if ((x + ((player.getX() - x) - App.WIDTH / 2) * speed) >
        // Game.mapConstraints[0]) {
        // x = Game.mapConstraints[0];
        // } else {
        // x += ((player.getX() - x) - App.WIDTH / 2) * speed;
        // }

        // if ((y + ((player.getY() - y) - App.HEIGHT / 2) * speed) <= 0) {
        // y = 0;
        // } else {
        // y += ((player.getY() - y) - App.HEIGHT / 2) * speed;
        // }
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}