package com.kevin.app.manager;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.kevin.app.main.App;

public class Texture {

    SpriteSheet player_sheet, floor_sheet, wall_sheet, fire_sheet,extra_sheet,patrick;
    private BufferedImage player_image, floor_image, wall_image, textbox_image, fire_image, extra_image,patrick_image;

    public HashMap<String, BufferedImage> sprites = new HashMap<>();

    public Texture() {

        BufferedImageLoader loader = new BufferedImageLoader();

        try {
            player_image = loader.loadImage("/sprites/player.png");
            floor_image = loader.loadImage("/sprites/floor.jpg");
            wall_image = loader.loadImage("/sprites/wall.png");
            textbox_image = loader.loadImage("/sprites/textbox.png");
            fire_image = loader.loadImage("/sprites/fire.png");
            extra_image = loader.loadImage("/sprites/other_stuff.png");
            patrick_image = loader.loadImage("/sprites/Patrick.png");

        } catch (Exception e) {
            e.printStackTrace();
        }

        player_sheet = new SpriteSheet(player_image);
        floor_sheet = new SpriteSheet(floor_image);
        wall_sheet = new SpriteSheet(wall_image);
        fire_sheet = new SpriteSheet(fire_image);
        extra_sheet = new SpriteSheet(extra_image);
        patrick = new SpriteSheet(patrick_image);
        getTextures();

    }

    public void getTextures() {
        sprites.put("player_down_walking_2", player_sheet.grabImage(126, 0, 38, 58));
        sprites.put("player_down_still", player_sheet.grabImage(84, 0, 38, 58));
        sprites.put("player_down_walking_1", player_sheet.grabImage(42, 0, 38, 58));

        sprites.put("player_up_walking_2", player_sheet.grabImage(126, 62, 38, 58));
        sprites.put("player_up_still", player_sheet.grabImage(84, 62, 38, 58));
        sprites.put("player_up_walking_1", player_sheet.grabImage(42, 62, 38, 58));

        sprites.put("player_left_walking_1", player_sheet.grabImage(4, 0, 38, 58));
        sprites.put("player_left_still", player_sheet.grabImage(4, 62, 38, 58));

        sprites.put("player_right_walking_1", player_sheet.grabImage(168, 0, 38, 58));
        sprites.put("player_right_still", player_sheet.grabImage(168, 62, 38, 58));

        sprites.put("floor1", floor_sheet.grabImage(0, 0, 128, 128));
        sprites.put("floor2", floor_sheet.grabImage(0, 128, 128, 128));
        sprites.put("floor3", floor_sheet.grabImage(0, 256, 128, 128));
        sprites.put("floor4", floor_sheet.grabImage(0, 384, 128, 128));
        sprites.put("floor5", floor_sheet.grabImage(128, 0, 128, 128));
        sprites.put("floor6", floor_sheet.grabImage(128, 128, 128, 128));
        sprites.put("floor7", floor_sheet.grabImage(128, 256, 128, 128));
        sprites.put("floor8", floor_sheet.grabImage(128, 384, 128, 128));
        sprites.put("floor9", floor_sheet.grabImage(256, 0, 128, 128));
        sprites.put("floor10", floor_sheet.grabImage(256, 128, 128, 128));
        sprites.put("floor11", floor_sheet.grabImage(256, 256, 128, 128));
        sprites.put("floor12", floor_sheet.grabImage(256, 384, 128, 128));
        sprites.put("floor13", floor_sheet.grabImage(384, 0, 128, 128));
        sprites.put("floor14", floor_sheet.grabImage(384, 128, 128, 128));
        sprites.put("floor15", floor_sheet.grabImage(384, 256, 128, 128));
        sprites.put("floor16", floor_sheet.grabImage(384, 384, 128, 128));

        sprites.put("wall_body", wall_sheet.grabImage(0, 0, 32, 32));
        sprites.put("wall_top", wall_sheet.grabImage(32, 0, 32, 32));
        sprites.put("wall_curve_left", wall_sheet.grabImage(0, 32, 13, 28));
        sprites.put("wall_straight", wall_sheet.grabImage(14, 32, 14, 32));
        sprites.put("wall_straight_right", wall_sheet.grabImage(14, 64, 14, 32));
        sprites.put("wall_curve_bottom_left", wall_sheet.grabImage(28, 32, 14, 14));
        sprites.put("wall_straight_bottom", wall_sheet.grabImage(28, 46, 32, 14));
        sprites.put("wall_curve_top_right", wall_sheet.grabImage(0, 60, 14, 28));
        sprites.put("wall_curve_bottom_right", wall_sheet.grabImage(42, 32, 14, 14));

        sprites.put("door_open", wall_sheet.grabImage(64, 0, 32, 32));
        sprites.put("door_closed", wall_sheet.grabImage(64, 32, 32, 32));
        sprites.put("door_pillar_top", wall_sheet.grabImage(96, 0, 32, 28));
        sprites.put("door_pillar_bottom", wall_sheet.grabImage(96, 29, 32, 32));

        sprites.put("clue", wall_sheet.grabImage(64, 64, 32, 32));
        sprites.put("empty_clue", wall_sheet.grabImage(96, 64, 32, 32));

        sprites.put("textbox", textbox_image);

        sprites.put("fire1", fire_sheet.grabImage(0, 0, 99, 163));
        sprites.put("fire2", fire_sheet.grabImage(99, 0, 98, 163));
        sprites.put("fire3", fire_sheet.grabImage(197, 0, 98, 163));
        sprites.put("fire4", fire_sheet.grabImage(295, 0, 99, 163));
        sprites.put("fire5", fire_sheet.grabImage(394, 0, 100, 163));
        sprites.put("fire6", fire_sheet.grabImage(494, 0, 99, 163));
        sprites.put("fire7", fire_sheet.grabImage(593, 0, 99, 163));
        sprites.put("fire8", fire_sheet.grabImage(692, 0, 99, 163));

        sprites.put("stairs_up", wall_sheet.grabImage(128, 0, 95, 143));

        //Characters
        sprites.put("shady_dealer" , wall_sheet.grabImage(1,182,47,48));
        sprites.put("pirate_boss" , wall_sheet.grabImage(4,397,26,26));

        //mug_shot
        sprites.put("mug_shot" , wall_sheet.grabImage(301,0,43,37));

        sprites.put("water" , extra_sheet.grabImage(0,0,258,258));

        sprites.put("death_counter" , extra_sheet.grabImage(3,345,39,48));

        sprites.put("stairs_after_up", App.flipImageHorizontally(wall_sheet.grabImage(28, 61, 32, 31)));

        sprites.put("key",extra_sheet.grabImage(409,9,153,139));

        sprites.put("Patrick",patrick.grabImage(209,141,320,624));

    }
}