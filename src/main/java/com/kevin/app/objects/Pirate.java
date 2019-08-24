package com.kevin.app.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.kevin.app.HUD.HUD;
import com.kevin.app.abstract_objects.BlockObject;
import com.kevin.app.abstract_objects.EnemyObject;
import com.kevin.app.ids.BlockId;
import com.kevin.app.ids.EnemyId;
import com.kevin.app.ids.ObjectIds;
import com.kevin.app.main.App;
import com.kevin.app.states.Game;

public class Pirate extends EnemyObject {

    public int shootingTimer = 120;

    private int health = 100;

    private BufferedImage image;

    private float velX = 4f;
    Random rand;
    public Pirate(float x, float y, ObjectIds ObjectId, EnemyId enemyId) {
        super(x, y, ObjectId, enemyId);
        rand = new Random();
        image = App.getImageFromTextures("pirate_boss");
    }

	@Override
    public void render(Graphics2D g) {
        g.drawImage(image, (int)x, (int)y, 200, 200, null);

        g.setColor(Color.green);
        g.fillRect((int)x, (int)y - 20, (int)(100 * (health*1.0/100)), 15);
    }

    @Override
    public void tick() {
        if(!HUD.isDead){    
            if(shootingTimer > 0){
                shootingTimer--;
            }else if(shootingTimer == 0){
                Game.handler.addSpecialBlocks(new Projectile((int)x, (int)y, ObjectIds.Block, BlockId.Orange, rand.nextInt(6)+4, rand.nextInt(6)+4));
                Game.handler.addSpecialBlocks(new Projectile((int)x, (int)y, ObjectIds.Block, BlockId.Orange, rand.nextInt(6)+4, rand.nextInt(6)+4));
                shootingTimer = 120;
            }
    
            if(x + velX > Game.mapConstraints[0] || x + velX < 0){
                velX *= -1;
            }else{
                x += velX;
            }
        }else{
            shootingTimer = 120;
            Game.handler.removeAllSpecialBlocks();
        }

        for(int i = 0; i < Game.handler.sBlocks.size(); i++){
            BlockObject block = Game.handler.sBlocks.get(i);

            if(block.getBlockId().equals(BlockId.Orange) && ((Projectile)block).bounced && block.getBounds().intersects(getBounds())){
                health--;
                Game.handler.removeSpecialBlock(block);
                if(health <= 0){
                    Game.handler.addBlocks(new Block((int)x, (int)y, ObjectIds.Block, BlockId.Key));
                    Game.handler.removeEnemy(this);
                }
            }
        }
    }

    @Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 100, 200);
	}

}