package com.kevin.app.states;

import java.awt.Color;
import java.awt.Graphics2D;

import com.kevin.app.main.App;
import com.kevin.app.main.GameState;

public class Menu extends State {

    public Menu() {

    }

    public void render(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, App.WIDTH, App.HEIGHT);
    }

    public void tick() {

    }

    public int getStateId() {
        return GameState.MENU;
    }
}