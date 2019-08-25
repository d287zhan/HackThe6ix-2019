package com.kevin.app.objects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.kevin.app.abstract_objects.BlockObject;
import com.kevin.app.ids.BlockId;
import com.kevin.app.ids.ObjectIds;
import com.kevin.app.main.App;

public class ColorOnStepTile extends BlockObject {

    public boolean stepped = false;
    private Color onStepColor;
    private float colorComposite = 0.0f;
    private BufferedImage floor;

    public ColorOnStepTile(float x, float y, ObjectIds ObjectId, BlockId blockId, Color color) {
        super(x, y, ObjectId, blockId);
        this.onStepColor = color;
        Random rand = new Random();
        // floor = App.getImageFromTextures("floor"+(rand.nextInt(16)+1));
        floor = App.getImageFromTextures("floor_wood");
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(floor, (int) x, (int) y, 64, 64, null);
        if (stepped) {
            g.setColor(onStepColor);
            g.setComposite(AlphaComposite.SrcOver.derive(colorComposite));
            g.fillRect((int) x, (int) y, 64, 64);
            g.setComposite(AlphaComposite.SrcOver);
        }
        g.setColor(Color.white);
        g.drawRect((int) x, (int) y, 64, 64);
    }

    @Override
    public void tick() {
        if (stepped && colorComposite < 0.95f) {
            colorComposite += 0.05f;
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 64, 64);
    }

    public void setStepped(boolean stepped) {
        this.stepped = stepped;
    }
}