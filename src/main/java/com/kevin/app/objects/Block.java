package com.kevin.app.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.kevin.app.abstract_objects.BlockObject;
import com.kevin.app.ids.BlockId;
import com.kevin.app.ids.ObjectIds;
import com.kevin.app.main.App;

public class Block extends BlockObject {

    BufferedImage image;

    public Block(float x, float y, ObjectIds ObjectId, BlockId blockId) {
        super(x, y, ObjectId, blockId);
        if (blockId.equals(BlockId.Key) || blockId.equals(BlockId.FakeKey)) {
            image = App.getImageFromTextures("key");
        }
    }

    @Override
    public void render(Graphics2D g) {
        if (bid.equals(BlockId.FakeKey) || bid.equals(BlockId.Key)) {
            g.drawImage(image, (int) x + 16, (int) y + 16, 32, 32, null);
        }
    }

    @Override
    public void tick() {

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 64, 64);
    }

}