package com.kevin.app.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import com.kevin.app.HUD.HUD;
import com.kevin.app.ids.BlockId;
import com.kevin.app.ids.ObjectIds;
import com.kevin.app.manager.Handler;
import com.kevin.app.manager.Texture;
import com.kevin.app.objects.Clue;
import com.kevin.app.objects.ColorOnStepTile;
import com.kevin.app.objects.Fire;
import com.kevin.app.objects.Floor;
import com.kevin.app.objects.Stairs;
import com.kevin.app.objects.Wall;

public class App extends Canvas implements Runnable {

    private static final long serialVersionUID = -9152863979561461947L;
    static final long serialVersionID = 1L;

    public static final int WIDTH = 900;
    public static final int HEIGHT = 900;
    public static Font gameFont;
    public static final String TITLE = "Empty";
    private static final int NUM_OF_BUFFERS = 3;

    private static App app;
    public static HUD hud;
    private static Texture tex;
    private StateController controller;

    private Thread thread;
    private boolean running = false;

    public static void main(String[] args) {
        tex = new Texture();
        app = new App();
        hud = new HUD();
        loadFonts();
        new Window(TITLE, WIDTH, HEIGHT, app);
    }

    public static void loadFonts() {
        InputStream stream = App.class.getResourceAsStream("/fonts/8-BIT WONDER.TTF");
        try {
            gameFont = Font.createFont(Font.TRUETYPE_FONT, stream);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        controller = new StateController(GameState.GAME, app);
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(NUM_OF_BUFFERS);
            return;
        }
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();

        controller.render(g);

        g.dispose();
        bs.show();

    }

    public void tick() {
        controller.tick();
    }

    public synchronized void start() {
        if (running)
            return;

        running = true;
        thread = new Thread(this, "Main Thread");
        thread.start();
    }

    public synchronized void stop() {
        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        long timer2 = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                try {
                    tick();
                    render();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                updates++;
                frames++;
                delta--;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // render();
            // frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                // System.out.println(frames+" "+updates);
                timer += 1000;
                // fps = frames;
                frames = 0;
                updates = 0;

            }

        }
        stop();
    }

    public static int[] loadImageLevel(BufferedImage image, Handler handler, String clue) {
        int w = image.getWidth();
        int h = image.getHeight();

        System.out.println("Width: " + w);
        System.out.println("Height: " + h);
        for (int xx = 0; xx < h; xx++) {
            for (int yy = 0; yy < w; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                // Now check the color values 0-255
                // Example: if color is black
                if (red == 0 && green == 0 && blue == 0) {
                    handler.addBlocks(new Floor(xx * 64, yy * 64, ObjectIds.Block, BlockId.Floor));
                } else if (red == 255 && green == 255 && blue == 254) {
                    handler.addBlocks(new Wall(xx * 64, yy * 64, ObjectIds.Block, BlockId.WallTop));
                } else if (red == 255 && green == 255 && blue == 253) {
                    handler.addBlocks(new Wall(xx * 64, yy * 64, ObjectIds.Block, BlockId.Wall));
                } else if (red == 255 && green == 255 && blue == 252) {
                    handler.addBlocks(new Wall(xx * 64, yy * 64, ObjectIds.Block, BlockId.WallCurveLeft));
                } else if (red == 255 && green == 255 && blue == 251) {
                    handler.addBlocks(new Wall(xx * 64, yy * 64, ObjectIds.Block, BlockId.WallStraight));
                } else if (red == 255 && green == 255 && blue == 250) {
                    handler.addBlocks(new Wall(xx * 64, yy * 64, ObjectIds.Block, BlockId.WallCurveLeftBottom));
                } else if (red == 255 && green == 255 && blue == 249) {
                    handler.addBlocks(new Wall(xx * 64, yy * 64, ObjectIds.Block, BlockId.WallStraightBottom));
                } else if (red == 255 && green == 255 && blue == 248) {
                    handler.addBlocks(new Wall(xx * 64, yy * 64, ObjectIds.Block, BlockId.WallStraightRight));
                } else if (red == 255 && green == 255 && blue == 247) {
                    handler.addBlocks(new Wall(xx * 64, yy * 64, ObjectIds.Block, BlockId.WallCurveBottomRight));
                } else if (red == 255 && green == 255 && blue == 246) {
                    handler.addBlocks(new Wall(xx * 64, yy * 64, ObjectIds.Block, BlockId.WallCurveTopRight));
                } else if (red == 255 && green == 255 && blue == 245) {
                    handler.addBlocks(new Wall(xx * 64, yy * 64, ObjectIds.Block, BlockId.Door));
                } else if (red == 255 && green == 255 && blue == 244) {
                    handler.addBlocks(new Wall(xx * 64, yy * 64, ObjectIds.Block, BlockId.DoorPillar));
                } else if (red == 255 && green == 255 && blue == 243) {
                    handler.addBlocks(new Wall(xx * 64, yy * 64, ObjectIds.Block, BlockId.DoorPillarTop));
                } else if (red == 255 && green == 255 && blue == 230) {
                    handler.addBlocks(new Clue(xx * 64, yy * 64, ObjectIds.Block, BlockId.Clue, clue));
                } else if (red == 255 && green == 0 && blue == 1) {
                    handler.addBlocks(new Floor(xx * 64, yy * 64, ObjectIds.Block, BlockId.Floor));
                    handler.addSpecialBlocks(new Fire(xx * 64, yy * 64, ObjectIds.Block, BlockId.Fire));
                } else if (red == 255 && green == 0 && blue == 2) {
                    handler.addBlocks(new Floor(xx * 64, yy * 64, ObjectIds.Block, BlockId.Floor));
                    handler.addBlocks(new Floor(xx * 64, (yy + 1) * 64, ObjectIds.Block, BlockId.Floor));
                    handler.addSpecialBlocks(new Stairs(xx * 64, yy * 64, ObjectIds.Block, BlockId.Stairs));
                } else {
                    handler.addBlocks(new ColorOnStepTile(xx * 64, yy * 64, ObjectIds.Block, BlockId.ColorTile,
                            new Color(red, green, blue)));
                }
            }
        }

        return new int[] { w * 64, h * 64 };
    }

    public static boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else
                return false;
        } else
            return false;
    }

    public static BufferedImage getImageFromTextures(String key) {
        return tex.sprites.get(key);
    }

}