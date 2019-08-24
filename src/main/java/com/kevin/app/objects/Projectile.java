package com.kevin.app.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.kevin.app.abstract_objects.BlockObject;
import com.kevin.app.ids.BlockId;
import com.kevin.app.ids.ObjectIds;
import com.kevin.app.main.App;
import com.kevin.app.states.Game;

public class Projectile extends BlockObject {

    float velX, velY;   
    public boolean bounced = false; 
    public Projectile(float x, float y, ObjectIds ObjectId, BlockId blockId, float velX, float velY) {
        super(x, y, ObjectId, blockId);
        this.velX = velX;
        this.velY = velY;
    }

	@Override
    public void render(Graphics2D g) {
        g.setColor(Color.orange);
        g.fillOval((int)x, (int)y, 32, 32);
    }

    @Override
    public void tick() {

        x += velX;
        y += velY;
		if (y <= 0 || y >= Game.mapConstraints[1] - 64){
            velY *= -1;
            bounced = true;
        }
        if (x <= 0 || x >= Game.mapConstraints[0] - 16){
            velX *= -1;
             bounced = true;
        }   

        
    }

    @Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
    
}