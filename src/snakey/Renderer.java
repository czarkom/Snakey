package snakey;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Renderer extends JPanel {

    static int curColor = 0;
    static int counter = 0;
    BufferedImage SBImage, SHImage, SHRImage, SHLImage, SHUImage, SHDImage, SBLRImage, SBUDImage;
    boolean wasPoweredUp = false;


    private void loadImages() {
        try {
            SBLRImage = ImageIO.read(new File("visuals/skin.png"));
            SBUDImage = ImageIO.read(new File("visuals/skinUD.png"));
            SHRImage = ImageIO.read(new File("visuals/headRight.png"));
            SHLImage = ImageIO.read(new File("visuals/headLeft.png"));
            SHUImage = ImageIO.read(new File("visuals/headUp.png"));
            SHDImage = ImageIO.read(new File("visuals/headDown.png"));
        } catch (
                IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        loadImages();
        super.paintComponent(g);
        g.setColor(new Color(curColor));
        g.fillRect(0, 0, 800, 800);
        Game game = Game.game;


        if (game.snake.direction == Snake.UP) {
            SHImage = SHUImage;
            SBImage = SBUDImage;
        } else if (game.snake.direction == Snake.DOWN) {
            SHImage = SHDImage;
            SBImage = SBUDImage;

        } else if (game.snake.direction == Snake.LEFT) {
            SHImage = SHLImage;
            SBImage = SBLRImage;

        } else if (game.snake.direction == Snake.RIGHT) {
            SHImage = SHRImage;
            SBImage = SBLRImage;
        }

        for (Point point : game.snake.body) {
            g.drawImage(SBImage, point.x * Game.SCALE, point.y * Game.SCALE, Game.SCALE, Game.SCALE, null);

        }

        g.drawImage(SHImage, game.snake.head.x * Game.SCALE, game.snake.head.y * Game.SCALE, Game.SCALE, Game.SCALE, null);


        g.setColor(Color.RED);

        g.fillRect(game.powerUp.cord.x * Game.SCALE, game.powerUp.cord.y * Game.SCALE,
                Game.SCALE, Game.SCALE);
        if (game.powerUp.specialAvailable) {
            if (game.powerUp.specialPowerUpType == PowerUP.Type.SPEED) {
                g.setColor(Color.CYAN);

            } else if (game.powerUp.specialPowerUpType == PowerUP.Type.SLOW) {
                g.setColor(Color.WHITE);
            }

            g.fillRect(game.powerUp.specialCord.x * Game.SCALE, game.powerUp.specialCord.y * Game.SCALE,
                    Game.SCALE, Game.SCALE);
        }
        if (game.snake.speed == 5) {
            if(wasPoweredUp){
                counter = counter%30;
                wasPoweredUp = false;
            }
            if (counter < 30) {

                curColor = counter;
                counter++;
            }
            if (counter == 30) {
                curColor--;
                if (curColor == 0) counter = 0;
            }
        }
        else {

            if (counter < 120) {
                curColor = counter;
                counter++;
            }
            if (counter == 120) {
                curColor--;
                if (curColor == 0) counter = 0;
            }
            wasPoweredUp = true;

        }

        // System.out.println("Colors: " + curColor + "," + counter);

    }
}
