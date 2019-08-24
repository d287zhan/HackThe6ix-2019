package com.kevin.app.objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.kevin.app.abstract_objects.BlockObject;
import com.kevin.app.ids.BlockId;
import com.kevin.app.ids.ObjectIds;
import com.kevin.app.main.App;

public class Clue extends BlockObject {

    String clue;
    private BufferedImage background, glyph;

    public Clue(float x, float y, ObjectIds ObjectId, BlockId blockId, String clue) {
        super(x, y, ObjectId, blockId);
        this.clue = clue;
        background = App.getImageFromTextures("wall_body");
        glyph = App.getImageFromTextures("clue");
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(background, (int) x, (int) y, 64, 64, null);
        g.drawImage(glyph, (int) x + 8, (int) y + 8, null);
    }

    @Override
    public void tick() {

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 64, 64);
    }

    public String getClue() {
        return this.clue;
    }

}