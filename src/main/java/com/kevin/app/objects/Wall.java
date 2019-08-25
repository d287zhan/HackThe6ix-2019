package com.kevin.app.objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.kevin.app.abstract_objects.BlockObject;
import com.kevin.app.ids.BlockId;
import com.kevin.app.ids.ObjectIds;
import com.kevin.app.main.App;

public class Wall extends BlockObject {

    BufferedImage image;
    public boolean closed = true;

    public Wall(float x, float y, ObjectIds ObjectId, BlockId blockId) {
        super(x, y, ObjectId, blockId);

        if (blockId.equals(BlockId.Wall) || blockId.equals(BlockId.FakeHorizontalWall)) {
            image = App.getImageFromTextures("wall_body");
        } else if (blockId.equals(BlockId.WallTop)) {
            image = App.getImageFromTextures("wall_top");
        } else if (blockId.equals(BlockId.WallCurveLeft)) {
            image = App.getImageFromTextures("wall_curve_left");
        } else if (blockId.equals(BlockId.WallStraight) || blockId.equals(BlockId.FakeVerticalWallRight)) {
            image = App.getImageFromTextures("wall_straight");
        } else if (blockId.equals(BlockId.WallCurveLeftBottom)) {
            image = App.getImageFromTextures("wall_curve_bottom_left");
        } else if (blockId.equals(BlockId.WallStraightBottom)) {
            image = App.getImageFromTextures("wall_straight_bottom");
        } else if (blockId.equals(BlockId.WallCurveBottomRight)) {
            image = App.getImageFromTextures("wall_curve_bottom_right");
        } else if (blockId.equals(BlockId.WallCurveTopRight)) {
            image = App.getImageFromTextures("wall_curve_top_right");
        } else if (blockId.equals(BlockId.WallStraightRight)) {
            image = App.getImageFromTextures("wall_straight_right");
        } else if (blockId.equals(BlockId.Door)) {
            image = App.getImageFromTextures("door_closed");
        } else if (blockId.equals(BlockId.DoorPillar)) {
            image = App.getImageFromTextures("door_pillar_bottom");
        } else if (blockId.equals(BlockId.DoorPillarTop)) {
            image = App.getImageFromTextures("door_pillar_top");
        } else if (blockId.equals(BlockId.StairsPrevUp)) {
            image = App.getImageFromTextures("stairs_after_up");
        } else if (blockId.equals(BlockId.DoorExit)) {
            image = App.getImageFromTextures("door_exit");
        }
    }

    @Override
    public void render(Graphics2D g) {

        g.drawImage(image, (int) x, (int) y, 64, 64, null);
    }

    @Override
    public void tick() {
        if (bid.equals(BlockId.Door) && !closed) {
            image = App.getImageFromTextures("door_open");
        }

        if (Player.hasKey && bid.equals(BlockId.Door)) {
            closed = false;
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 64, 64);
    }

}