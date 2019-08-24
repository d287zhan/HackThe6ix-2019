package com.kevin.app.manager;

import java.awt.Graphics2D;
import java.util.LinkedList;

import com.kevin.app.abstract_objects.BlockObject;
import com.kevin.app.abstract_objects.EnemyObject;
import com.kevin.app.abstract_objects.PlayerObject;

public class Handler {

    public LinkedList<EnemyObject> enemies = new LinkedList<>();
    public LinkedList<BlockObject> blocks = new LinkedList<>();
    public LinkedList<BlockObject> sBlocks = new LinkedList<>();

    public Handler() {

    }

    public void tick() {
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).tick();
        }

        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).tick();
        }

        for (int i = 0; i < sBlocks.size(); i++) {
            sBlocks.get(i).tick();
        }
    }

    public void render(Graphics2D g) {
        
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).render(g);
        }
        
        for (int i = 0; i < sBlocks.size(); i++) {
            sBlocks.get(i).render(g);
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).render(g);
        }
    }

    public void addBlocks(BlockObject block) {
        blocks.add(block);
    }

    public void removeBlock(BlockObject block) {
        blocks.remove(block);
    }

    public void addEnemy(EnemyObject enemy) {
        enemies.add(enemy);
    }

    public void removeEnemy(EnemyObject enemy) {
        enemies.remove(enemy);
    }

    public void removeSpecialBlock(BlockObject block){
        sBlocks.remove(block);
    }
    
    public void removeAllSpecialBlocks(){
        sBlocks.clear();
    }

    public void removeAllBlocks() {
        blocks.clear();
        sBlocks.clear();
    }

    public void removeAllEnemies() {
        enemies.clear();
    }

    public void addSpecialBlocks(BlockObject block) {
        sBlocks.add(block);
    }

    public void removeAll() {
        removeAllBlocks();
        removeAllEnemies();
    }

}