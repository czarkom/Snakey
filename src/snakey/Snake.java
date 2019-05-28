package snakey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;


public class Snake implements ActionListener, KeyListener {

    public JFrame gameFrame;

    public static Snake snake;

    public Renderer renderer;

    public Timer timer = new Timer(10, this);

    public ArrayList<Point> body = new ArrayList<Point>();

    public int ticks = 0, direction = DOWN, score;

    public Point head, powerUp;

    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;

    public boolean gameOver = false, paused;

    public Random random;

    public Dimension dim;

    public Snake() {
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        gameFrame = new JFrame("Snakey");
        gameFrame.setVisible(true);
        gameFrame.setSize(800, 800);
        gameFrame.setResizable(false);

        gameFrame.setLocation(dim.width / 2 - gameFrame.getWidth() / 2, dim.height / 2 - gameFrame.getHeight() / 2);
        gameFrame.add(renderer = new Renderer());
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.addKeyListener(this);

    }


    public void run() {

        gameOver = false;
        paused = false;
        score = 0;
        direction = DOWN;

        body.clear();

        head = new Point(0, 0);

        random = new Random();
        powerUp = new Point(random.nextInt(dim.width / SCALE), random.nextInt(dim.height / SCALE));


        for (int i = 0; i < 10; i++) {
            body.add(new Point(head.x, head.y));
        }

        timer.start();

    }

    public static void main(String[] args) {
        snake = new Snake();
        snake.run();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        renderer.repaint();
        ticks++;


        System.out.println("Direction: " + direction);
        System.out.println("Head X: " + head.x + ", Head Y: " + head.y);


        if (ticks % 10 == 0 && head != null && !gameOver && !paused) {

            body.add(new Point(head.x, head.y));
            if (direction == DOWN) {
                if (head.y + 1 < dim.height / SCALE)
                    head = new Point(head.x, head.y + 1);
                else gameOver = true;
            }

            if (direction == UP) {
                if (head.y - 1 >= 0)
                    head = new Point(head.x, head.y - 1);
                else gameOver = true;
            }
            if (direction == LEFT) {
                if (head.x - 1 >= 0)
                    head = new Point(head.x - 1, head.y);
                else gameOver = true;
            }
            if (direction == RIGHT) {
                if (head.x + 1 < dim.width / SCALE)
                    head = new Point(head.x + 1, head.y);
                else gameOver = true;
            }

            for (Point point : snake.body) {
                if (point.x == head.x && point.y == head.y) gameOver = true;
            }


            body.remove(0);


            if (powerUp != null) {
                if (head.x == powerUp.x && head.y == powerUp.y) {
                    score += 10;
                    System.out.println("Score:" + score);
                    powerUp.setLocation(random.nextInt(dim.width / SCALE), random.nextInt(dim.height / SCALE));
                    body.add(new Point(head.x, head.y));

                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        if (i == KeyEvent.VK_A && direction != RIGHT) direction = LEFT;
        if (i == KeyEvent.VK_W && direction != DOWN) direction = UP;
        if (i == KeyEvent.VK_S && direction != UP) direction = DOWN;
        if (i == KeyEvent.VK_D && direction != LEFT) direction = RIGHT;
        if (i == KeyEvent.VK_SPACE)
            if (gameOver)
                run();
            else paused = !paused;

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
