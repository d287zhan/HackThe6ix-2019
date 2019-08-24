package com.kevin.app.manager;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.kevin.app.HUD.HUD;
import com.kevin.app.main.App;
import com.kevin.app.main.GameState;
import com.kevin.app.main.StateController;
import com.kevin.app.objects.Player;

// If key is not released and window loses focus, the character will continue to move indefinetely in the pressed direction
public class KeyInputHandler extends KeyAdapter {

    private Player player;
    private int playerSpeed = 5;
    private boolean keyDown[] = new boolean[4];
    private int keyValues[] = { KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D };

    public KeyInputHandler(Player player) {
        this.player = player;

        for (int i = 0; i < keyDown.length; i++) {
            keyDown[i] = false;
        }
    }

    public void keyPressed(KeyEvent event) {
        if (StateController.getCurrentState() != GameState.GAME)
            return;

        int key = event.getKeyCode();

        if(HUD.isDead){
            if(key == KeyEvent.VK_SPACE){
                player.setPosition(Player.respawnPosition[0], Player.respawnPosition[1]);
                HUD.isDead = false;
                App.hud.showBlack = false;
            }
        }

        if(key == KeyEvent.VK_O){
            player.hasKey = true;
        }

        if (key == KeyEvent.VK_NUMPAD1) {
            playerSpeed = (playerSpeed - 2 > 0) ? playerSpeed - 2 : 0;
        } else if (key == KeyEvent.VK_NUMPAD3 || key == KeyEvent.VK_PLUS) {
            playerSpeed += 2;
        }

        for (int i = 0; i < keyValues.length; i++) {
            if (key == keyValues[i]) {
                keyDown[i] = true;
                switch (i) {
                case 0:
                    player.setVelX(0);
                    player.setVelY(-playerSpeed);
                    player.setDirection("up");
                    break;
                case 1:
                    player.setVelY(0);
                    player.setVelX(-playerSpeed);
                    player.setDirection("left");
                    break;
                case 2:
                    player.setVelX(0);
                    player.setVelY(playerSpeed);
                    player.setDirection("down");
                    break;
                case 3:
                    player.setVelY(0);
                    player.setVelX(playerSpeed);
                    player.setDirection("right");
                    break;

                }
            }
        }
    }

    public void keyReleased(KeyEvent event) {
        if (StateController.getCurrentState() != GameState.GAME)
            return;

        int key = event.getKeyCode();

        for (int i = 0; i < keyValues.length; i++) {
            if (key == keyValues[i]) {
                keyDown[i] = false;
            }

            if (!keyDown[1] && !keyDown[3]) {
                player.setVelX(0);
            }

            if (!keyDown[0] && !keyDown[2]) {
                player.setVelY(0);
            }
        }
    }

}