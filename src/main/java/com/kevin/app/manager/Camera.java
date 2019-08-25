package com.kevin.app.manager;

import com.kevin.app.abstract_objects.PlayerObject;
import com.kevin.app.main.App;
import com.kevin.app.states.Game;

public class Camera {

    private boolean shake = false;
    private int shakeDuration = 60;
    private float x, y;

    private float speed = 0.05f;

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void tick(PlayerObject player) {

        // if (x + ((player.getX() - x) - App.WIDTH / 2) * speed >= 0
        // && x + ((player.getX() - x) - App.WIDTH / 2) * speed <=
        // (Game.mapConstraints[0] - App.WIDTH + 15)) {
        // x += ((player.getX() - x) - App.WIDTH / 2) * speed;
        // }
        // if (y + ((player.getY() - y) - App.HEIGHT / 2) * speed >= 0
        // && y + ((player.getY() - y) - App.HEIGHT / 2) * speed <=
        // (Game.mapConstraints[1] - (App.HEIGHT))) {
        // y += ((player.getY() - y) - App.HEIGHT / 2) * speed;
        // }
        x += ((player.getX() - x) - App.WIDTH / 2) * speed;
        y += ((player.getY() - y) - App.HEIGHT / 2) * speed;

        if (shake) {
            if (shakeDuration == 0) {
                x -= 5;
                shake = false;
                shakeDuration = 60;
                return;
            }
            if (shakeDuration == 60) {
                x += 5;
            } else if (shakeDuration % 2 == 1) {
                x -= 10;
            } else if (shakeDuration % 2 == 0) {
                x += 10;
            }
            shakeDuration--;
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

    public void setShake() {
        this.shake = true;
    }
}