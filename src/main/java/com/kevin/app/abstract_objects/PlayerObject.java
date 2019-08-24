package com.kevin.app.abstract_objects;

import com.kevin.app.ids.ObjectIds;
import com.kevin.app.ids.PlayerId;

public abstract class PlayerObject extends Object {

    protected PlayerId pid;
    protected float velX, velY;

    public PlayerObject(int x, int y, ObjectIds ObjectId, PlayerId PlayerId) {
        super(x, y, ObjectId);
        this.pid = PlayerId;
    }

    public float getVelX() {
        return this.velX;
    }

    public float getVelY() {
        return this.velY;
    }

    public void setVelX(int value) {
        this.velX = value;
    }

    public void setVelY(int value) {
        this.velY = value;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}