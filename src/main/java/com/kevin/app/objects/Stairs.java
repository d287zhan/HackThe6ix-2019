package com.kevin.app.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.kevin.app.abstract_objects.BlockObject;
import com.kevin.app.ids.BlockId;
import com.kevin.app.ids.ObjectIds;
import com.kevin.app.main.App;

public class Stairs extends BlockObject {

    private BufferedImage image;

    public Stairs(float x, float y, ObjectIds ObjectId, BlockId blockId) {
        super(x, y, ObjectId, blockId);
        image = App.getImageFromTextures("stairs_up");
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, (int) x - 64, (int) y, 128, 128, null);
        g.setColor(Color.red);
        g.draw(getBounds());
    }

    @Override
    public void tick() {

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x - 20, (int) y, 128, 128);
    }

}