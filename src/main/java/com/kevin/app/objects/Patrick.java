package com.kevin.app.objects;

import java.awt.Graphics2D;
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
                if (block.getBlockId().equals(BlockId.WallStraightBottom)) {
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

}