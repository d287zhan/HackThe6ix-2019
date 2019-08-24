package com.kevin.app.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.kevin.app.HUD.HUD;
import com.kevin.app.abstract_objects.BlockObject;
import com.kevin.app.abstract_objects.PlayerObject;
import com.kevin.app.ids.BlockId;
import com.kevin.app.ids.ObjectIds;
import com.kevin.app.ids.PlayerId;
import com.kevin.app.main.App;
import com.kevin.app.manager.Animation;
import com.kevin.app.manager.Handler;
import com.kevin.app.states.Game;

public class Player extends PlayerObject {

    private String direction = "down";
    private boolean walking = false;
    private int playerWidth = 57;
    private int playerHeight = 87;
    private Handler handler;
    private boolean isHUDOpen = false;
    private boolean showClue = false;
    private String clueString = "";

    private BufferedImage walking_down[] = new BufferedImage[2];
    private BufferedImage walking_up[] = new BufferedImage[2];
    private BufferedImage walking_left[] = new BufferedImage[2];
    private BufferedImage walking_right[] = new BufferedImage[2];
    private HashMap<String, Animation> pAnimations = new HashMap<>();
    private HashMap<String, BufferedImage> stillPlayer = new HashMap<>();

    public static boolean hasKey = false;
    public static int deaths = 0;
    public static int[] respawnPosition;

    public Player(int x, int y, ObjectIds ObjectId, PlayerId PlayerId, Handler handler) {
        super(x, y, ObjectId, PlayerId);
        this.handler = handler;
        loadPlayerImages();
        initAnimations();
    }

    public void initAnimations() {
        pAnimations.put("down", new Animation(10, walking_down));
        pAnimations.put("left", new Animation(10, walking_left));
        pAnimations.put("right", new Animation(10, walking_right));
        pAnimations.put("up", new Animation(10, walking_up));

        // Used to prevent that glitchy loading of animations for the first time
        for (int i = 0; i < 10; i++) {
            pAnimations.get("down").runAnimation();
            pAnimations.get("left").runAnimation();
            pAnimations.get("right").runAnimation();
            pAnimations.get("up").runAnimation();
        }
    }

    @Override
    public void tick() {
        if (velX != 0 || velY != 0) {
            walking = true;
        } else {
            walking = false;
        }

        if (x + velX < 0) {
            x = 0;
        } else if (x + playerWidth + velX > Game.mapConstraints[0]) {
            x = Game.mapConstraints[0] - playerWidth;
        }

        if (y + velY < 0) {
            y = 0;
        } else if (y + playerHeight + velY > Game.mapConstraints[1]) {
            y = Game.mapConstraints[0] - playerHeight;
        }

        if(!App.hud.showBlack && !App.hud.showText && !HUD.isDead){
            x += velX;
            y += velY;
        }

        if (walking) {
            pAnimations.get(direction).runAnimation();
        }
        collision();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, playerWidth, playerHeight);
    }

    public Rectangle getFootBounds() {
        if (direction.equals("up") || direction.equals("down")) {
            return new Rectangle((int) x + 12, (int) y + 72, playerWidth - 24, 9);
        } else if (direction.equals("right")) {
            return new Rectangle((int) x + 15, (int) y + 72, playerWidth - 30, 9);
        } else if (direction.equals("left")) {
            return new Rectangle((int) x + 9, (int) y + 72, playerWidth - 30, 9);
        }

        return null;
    }

    public void collision() {

        for (int i = 0; i < handler.sBlocks.size(); i++) {
            BlockObject block = handler.sBlocks.get(i);
            if (block.getBlockId().equals(BlockId.Stairs) && block.getBounds().intersects(this.getBounds())) {
                Game.handleLevelChange();
            }else if(block.getBlockId().equals(BlockId.Orange) && block.getBounds().intersects(this.getBounds())){
                // deaths++;
                // HUD.isDead = true;
                // App.hud.showBlack = true;
            }
        }

        boolean allStepped = true;
        for (int i = 0; i < handler.blocks.size(); i++) {
            BlockObject block = handler.blocks.get(i);
            if (block.getBlockId().equals(BlockId.ColorTile) && block.getBounds().intersects(this.getFootBounds())) {
                ColorOnStepTile c = (ColorOnStepTile) block;
                c.setStepped(true);
            } else if ((block.getBlockId().equals(BlockId.Wall) || block.getBlockId().equals(BlockId.Door)
                    || block.getBlockId().equals(BlockId.DoorPillar) || block.getBlockId().equals(BlockId.Clue))
                    && block.getBounds().intersects(this.getFootBounds()) && velY < 0) {
                y = block.getY() - 13;
            } else if (block.getBlockId().equals(BlockId.WallStraight)
                    && block.getBounds().intersects(this.getBounds())) {
                x = block.getX() + 64;
            } else if (block.getBlockId().equals(BlockId.WallStraightRight)
                    && block.getBounds().intersects(this.getBounds())) {
                // x = block.getX() - 68;
            } else if (block.getBlockId().equals(BlockId.WallStraightBottom)
                    && block.getBounds().intersects(this.getFootBounds()) && velY > 0) {
                y = block.getY() - playerHeight - 6;
            }

            if (block.getBlockId().equals(BlockId.Clue)) {
                if (block.getBounds().intersects(this.getFootBounds())) {
                    this.showClue = true;
                    clueString = ((Clue) block).getClue();
                } else {
                    this.showClue = false;
                }
            }

            if((block.getBlockId().equals(BlockId.Water) || block.getBlockId().equals(BlockId.FakeKey)) && 
                block.getBounds().intersects(this.getFootBounds())){
                deaths++;
                HUD.isDead = true;
                App.hud.showBlack = true;
            }

            if(block.getBlockId().equals(BlockId.Key) && block.getBounds().intersects(this.getFootBounds())){
                hasKey = true;
                handler.removeBlock(block);
            }

            if (block.getBlockId().equals(BlockId.Door) && !((Wall)block).closed && block.getBounds().intersects(this.getFootBounds())) {
                Game.handleLevelChange();
            }

            if(Game.level.getLevel() == 3 && block.getBlockId().equals(BlockId.ColorTile) && !((ColorOnStepTile)block).stepped){
                allStepped = false;
            }
        }

        if(allStepped && Game.level.getLevel() == 3){
            hasKey = true;
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.black);

        if (!walking) {
            g.drawImage(stillPlayer.get(direction), (int) x, (int) y, 57, 87, null);
        } else {
            pAnimations.get(direction).drawAnimation(g, (int) x, (int) y, 57, 87);
        }
        if (this.showClue) {
            g.drawImage(App.getImageFromTextures("empty_clue"), 95, 50, 750, 750, null);
            g.setFont(App.gameFont.deriveFont(20f));
            System.out.println(Game.level.getLevel());
            if(Game.level.getLevel() == 1){
                g.drawString(clueString, 230, 440);
            }
        }
        // g.setColor(Color.red);
        // g.draw(getBounds());
    }

    public void loadPlayerImages() {
        stillPlayer.put("down", App.getImageFromTextures("player_down_still"));
        stillPlayer.put("up", App.getImageFromTextures("player_up_still"));
        stillPlayer.put("left", App.getImageFromTextures("player_left_still"));
        stillPlayer.put("right", App.getImageFromTextures("player_right_still"));

        walking_down[0] = App.getImageFromTextures("player_down_walking_1");
        walking_down[1] = App.getImageFromTextures("player_down_walking_2");

        walking_up[0] = App.getImageFromTextures("player_up_walking_1");
        walking_up[1] = App.getImageFromTextures("player_up_walking_2");

        walking_left[0] = App.getImageFromTextures("player_left_still");
        walking_left[1] = App.getImageFromTextures("player_left_walking_1");

        walking_right[0] = App.getImageFromTextures("player_right_still");
        walking_right[1] = App.getImageFromTextures("player_right_walking_1");
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

}