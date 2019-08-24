package com.kevin.app.objects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.kevin.app.abstract_objects.BlockObject;
import com.kevin.app.ids.BlockId;
import com.kevin.app.ids.ObjectIds;

public class ColorOnStepTile extends BlockObject {

    private boolean stepped = false;
    private Color onStepColor;
    private float colorComposite = 0.0f;

    public ColorOnStepTile(float x, float y, ObjectIds ObjectId, BlockId blockId, Color color) {
        super(x, y, ObjectId, blockId);
        this.onStepColor = color;
    }

	@Override
    public void render(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect((int)x, (int)y, 64, 64);
        if(stepped){
            g.setColor(onStepColor);
            g.setComposite(AlphaComposite.SrcOver.derive(colorComposite));
            g.fillRect((int)x, (int)y, 64, 64);
            g.setComposite(AlphaComposite.SrcOver);
        }
        g.setColor(Color.white);
        g.drawRect((int)x, (int)y, 64, 64);
    }

    @Override
    public void tick() {
        if(stepped && colorComposite < 0.99f){
            colorComposite+=0.01f;
        }
    }

    @Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 64, 64);
    }
    
    public void setStepped(boolean stepped){
        this.stepped = stepped;
    }
}