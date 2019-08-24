package com.kevin.app.abstract_objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.kevin.app.ids.ObjectIds;

public abstract class Object {
    protected float x, y;
    protected ObjectIds oid;

    public Object(float x, float y, ObjectIds ObjectId) {
        this.x = x;
        this.y = y;
        this.oid = ObjectId;
    }

    public abstract void render(Graphics2D g);

    public abstract void tick();

    public abstract Rectangle getBounds();

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ObjectIds getObjectId() {
        return this.oid;
    }
}