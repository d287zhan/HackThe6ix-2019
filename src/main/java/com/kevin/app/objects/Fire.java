package com.kevin.app.objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.kevin.app.abstract_objects.BlockObject;
import com.kevin.app.ids.BlockId;
import com.kevin.app.ids.ObjectIds;
import com.kevin.app.main.App;
import com.kevin.app.manager.Animation;

public class Fire extends BlockObject {

    private Animation animation;

    public Fire(float x, float y, ObjectIds ObjectId, BlockId blockId) {
        super(x, y, ObjectId, blockId);
        animation = new Animation(4,
                new BufferedImage[] { App.getImageFromTextures("fire1"), App.getImageFromTextures("fire2"),
                        App.getImageFromTextures("fire3"), App.getImageFromTextures("fire4"),
                        App.getImageFromTextures("fire5"), App.getImageFromTextures("fire6"),
                        App.getImageFromTextures("fire7"), App.getImageFromTextures("fire8") });
    }

    @Override
    public void render(Graphics2D g) {
        animation.drawAnimation(g, (int) x, (int) y);
    }

    @Override
    public void tick() {
        animation.runAnimation();
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

}