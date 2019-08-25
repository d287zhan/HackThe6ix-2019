package com.kevin.app.objects;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.kevin.app.HUD.HUD;
import com.kevin.app.abstract_objects.BlockObject;
import com.kevin.app.abstract_objects.EnemyObject;
import com.kevin.app.ids.BlockId;
import com.kevin.app.ids.EnemyId;
import com.kevin.app.ids.ObjectIds;
import com.kevin.app.main.App;
import com.kevin.app.states.Game;

public class Patrick extends EnemyObject {

    private int size = 500;
    private BufferedImage image;
    private int recharge = 300;
    private float velX, velY;
    private int speed = 12;
    private int numOfCharges = 6;

    public Patrick(float x, float y, ObjectIds ObjectId, EnemyId enemyId) {
        super(x, y, ObjectId, enemyId);
        image = App.getImageFromTextures("Patrick");
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, (int) x, (int) y, size, size, null);
    }

    @Override
    public void tick() {
        if (HUD.isDead) {
            recharge = 300;
        }
        if (!App.hud.showText && !HUD.isDead) {
            if (recharge > 0) {
                recharge--;
                if (recharge == 0) {
                    int lockOnTargetX = (int) Game.player.getX();
                    int lockOnTargetY = (int) Game.player.getY();

                    float diffX = (lockOnTargetX - x);
                    float diffY = (lockOnTargetY - y);

                    velX = (float) (speed * (diffX / Math.abs(diffY)));
                    velY = (float) (speed) * (diffY / Math.abs(diffY));
                    // double direction = Math.sqrt(Math.pow(lockOnTargetX - x, 2) +
                    // Math.pow(lockOnTargetY - y, 2));
                    speed += 1;
                    numOfCharges--;
                    recharge = 300;
                }
            }

            collision();

            if (numOfCharges == 0) {
                Game.handler.addBlocks(new Block((int) x, (int) y, ObjectIds.Block, BlockId.Key));
                Game.handler.removeEnemy(this);
            }
            x += velX;
            y += velY;
        }
    }

    public void collision() {
        if (x + velX < 0) {
            x = 32;
            velX = 0;
            y += velY;
            velY = 0;
            Game.camera.setShake();
            return;
        }

        if (x + velX > Game.mapConstraints[0]) {
            x = Game.mapConstraints[0] - 32 - size;
            velX = 0;
            y += velY;
            velY = 0;
            Game.camera.setShake();
            return;
        }

        for (int i = 0; i < Game.handler.blocks.size(); i++) {
            BlockObject block = Game.handler.blocks.get(i);
            if (block.getBounds() != null && block.getBounds().intersects(getBounds())) {
                if (block.getBlockId().equals(BlockId.WallStraightBottom)
                        || block.getBlockId().equals(BlockId.DoorExit)) {
                    if (x + velX > block.getBounds().getX()) {
                        velX = 0;
                        velY = 0;
                        x = (float) block.getBounds().getX();
                        y = (float) block.getBounds().getY() - 32 - size;
                        Game.camera.setShake();
                    }
                } else if (block.getBlockId().equals(BlockId.Wall) || block.getBlockId().equals(BlockId.Door)) {
                    if (x + velX > block.getBounds().getX()) {
                        velX = 0;
                        velY = 0;
                        x = (float) block.getBounds().getX();
                        y = (float) block.getBounds().getY() + 96;
                        Game.camera.setShake();
                    }
                }
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, size, size);
    }

    public Polygon getAccurateBounds() {
        Polygon poly = new Polygon();
        int x = (int) this.x;
        int y = (int) this.y;
        int xs[] = { x, x + 27, x + 27, x + 54, x + 55, x + 83, x + 83, x + 110, x + 110, x + 138, x + 138, x + 166,
                x + 166, x + 194, x + 194, x + 222, x + 222, x + 250, x + 250, x + 277, x + 277, x + 360, x + 360,
                x + 388, x + 388, x + 360, x + 360, x + 388, x + 388, x + 416, x + 416, x + 443, x + 443, x + 471,
                x + 471, x + 499, x + 499, x + 471, x + 471, x + 443, x + 443, x + 416, x + 416, x + 388, x + 388,
                x + 305, x + 305, x + 193, x + 193, x + 110, x + 110, x + 82, x + 82, x + 54, x + 54, x + 26, x + 26,
                x };
        int ys[] = { (y + 322), (y + 322), y + 304, y + 304, y + 286, y + 286, y + 268, y + 268, y + 250, y + 250,
                y + 160, y + 160, y + 107, y + 107, y + 71, y + 71, y + 35, y + 35, y + 17, y + 17, y, y, y + 17,
                y + 17, y + 70, y + 70, y + 232, y + 232, y + 250, y + 250, y + 268, y + 268, y + 286, y + 286, y + 304,
                y + 304, y + 374, y + 374, y + 392, y + 392, y + 428, y + 428, y + 464, y + 464, y + 499, y + 499,
                y + 464, y + 464, y + 499, y + 499, y + 464, y + 464, y + 428, y + 428, y + 393, y + 393, y + 375,
                y + 375 };
        int numOfPoints = xs.length;
        return new Polygon(xs, ys, numOfPoints);
    }

}