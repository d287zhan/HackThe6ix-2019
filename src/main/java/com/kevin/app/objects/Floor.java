package com.kevin.app.objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.kevin.app.abstract_objects.BlockObject;
import com.kevin.app.ids.BlockId;
import com.kevin.app.ids.ObjectIds;
import com.kevin.app.main.App;

public class Floor extends BlockObject {

    private BufferedImage image;

    public Floor(float x, float y, ObjectIds ObjectId, BlockId blockId) {
        super(x, y, ObjectId, blockId);
        Random rand = new Random();
        image = App.getImageFromTextures("floor"+(rand.nextInt(16)+1));
    }

    @Override
    public void render(Graphics2D g) {
        // g.setColor(Color.black);
        // g.fillRect((int) x, (int) y, 64, 64);

        g.drawImage(image, (int)x, (int)y, 64, 64, null);

        // g.setColor(Color.white);
        // g.drawRect((int) x, (int) y, 64, 64);
    }

    @Override
    public void tick() {

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}