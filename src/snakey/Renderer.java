package snakey;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Renderer extends JPanel {

    static int curColor = 0;
    static int counter = 0; //czemu static?
    BufferedImage SBImage, SHRImage;

    private void loadImages() {
        try {
            SBImage = ImageIO.read(new File("visuals/skin.png"));
            SHRImage = ImageIO.read(new File("visuals/headRight.png"));
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
        Snake snake = Snake.snake;;

        for (Point point : snake.body) {
            g.drawImage(SBImage, point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE, null);
        }

        g.drawImage(SHRImage, snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE, Snake.SCALE, Snake.SCALE, null);

        if (snake.powerUp.powerUpType == PowerUP.Type.SPEED) {
            g.setColor(Color.CYAN);
        } else if (snake.powerUp.powerUpType == PowerUP.Type.SLOW) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.RED);
        }
        g.fillRect(snake.powerUp.cord.x * Snake.SCALE, snake.powerUp.cord.y * Snake.SCALE,
                Snake.SCALE, Snake.SCALE);

        if (counter < 30) {
            curColor = counter;
            counter++;
        }
        if (counter == 30) {
            curColor--;
            if (curColor == 0) counter = 0;
        }

        // System.out.println("Colors: " + curColor + "," + counter);

    }
}
