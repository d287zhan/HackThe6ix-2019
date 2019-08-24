package com.kevin.app.main;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Window {

    public Window(String title, int width, int height, App game) {
        JFrame frame = new JFrame(title);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);
        
        frame.add(game);
        
        frame.setVisible(true);
        frame.requestFocus();
        game.start();
    }
}