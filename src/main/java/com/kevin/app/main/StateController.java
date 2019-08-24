package com.kevin.app.main;

import java.awt.Graphics2D;

import com.kevin.app.manager.Handler;
import com.kevin.app.states.Game;
import com.kevin.app.states.Menu;
import com.kevin.app.states.State;

public class StateController {

    private App app;
    private Menu menu;
    private Game game;
    private State[] states = new State[] { menu, game };
    private static int currentState = -1;

    public StateController(int initialState, App programInstance) {
        currentState = initialState;
        this.app = programInstance;
        initializeState(initialState);
    }

    public void render(Graphics2D g) {
        if (currentState != -1) {
            if (currentState == GameState.MENU) {
                states[0].render(g);
            } else if (currentState == GameState.GAME) {
                states[1].render(g);
            }
        }
    }

    public void tick() {
        if (currentState != -1) {
            if (currentState == GameState.MENU) {
                states[0].tick();
            } else if (currentState == GameState.GAME) {
                states[1].tick();
            }
        }
    }

    // This may result in some null pointers when switching states, maybe consider
    // sleeping the main thread while executing this
    public void handleState(int oldState, int newState) {
        if (oldState != newState) {
            states[oldState] = null;
            currentState = newState;
            initializeState(newState);
        }
    }

    public void initializeState(int value) {
        switch (value) {
        case 0:
            states[0] = new Menu();
            break;
        case 1:
            states[1] = new Game(100, 100, this.app, new Handler());
            break;
        default:
            break;
        }
    }

    public void setCurrentState(int newState) {
        handleState(currentState, newState);
    }

    public static int getCurrentState() {
        return currentState;
    }
}