package com.kevin.app.objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.kevin.app.abstract_objects.BlockObject;
import com.kevin.app.ids.BlockId;
import com.kevin.app.ids.ObjectIds;
import com.kevin.app.main.App;
import com.kevin.app.manager.Animation;

public class Water extends BlockObject {

    private Animation animation;

    public Water(float x, float y, ObjectIds ObjectId, BlockId blockId) {
        super(x, y, ObjectId, blockId);

        animation = new Animation(20,
                new BufferedImage[] { App.getImageFromTextures("water1"), App.getImageFromTextures("water2"),
                        App.getImageFromTextures("water3"), App.getImageFromTextures("water4"),
                        App.getImageFromTextures("water5"), App.getImageFromTextures("water6"),
                        App.getImageFromTextures("water7"), App.getImageFromTextures("water8"),
                        App.getImageFromTextures("water9"), App.getImageFromTextures("water10"),
                        App.getImageFromTextures("water11"), App.getImageFromTextures("water12"),
                        App.getImageFromTextures("water13"), App.getImageFromTextures("water14"),
                        App.getImageFromTextures("water15"), App.getImageFromTextures("water16"), });
    }

    @Override
    public void render(Graphics2D g) {
        animation.drawAnimation(g, (int) x, (int) y, 64, 64);
    }

    @Override
    public void tick() {
        animation.runAnimation();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 64, 64);
    }

}