package com.kevin.app.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.kevin.app.abstract_objects.EnemyObject;
import com.kevin.app.ids.EnemyId;
import com.kevin.app.ids.ObjectIds;

public class Pirate extends EnemyObject {

    public Pirate(float x, float y, ObjectIds ObjectId, EnemyId enemyId) {
        super(x, y, ObjectId, enemyId);
        // TODO Auto-generated constructor stub
    }

	@Override
    public void render(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect((int)x, (int)y, 50, 100);
    }

    @Override
    public void tick() {

    }

    @Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 50, 100);
	}

}