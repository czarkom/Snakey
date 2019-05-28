package snakey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class Snake implements ActionListener, KeyListener {

    public static JFrame gameFrame;

    int ticksRatio = 5;

    public static Snake snake;

    public Renderer renderer;

    public Timer timer = new Timer(10, this);

    public ArrayList<Point> body = new ArrayList<Point>();

    public int ticks = 0, direction = DOWN, score;

    public Point head;

    public PowerUP powerUp;

    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;

    public boolean gameOver = false, paused;

    public Dimension dim;

    public Snake() {

        dim = Toolkit.getDefaultToolkit().getScreenSize();

        gameOver = false;
        paused = false;
        score = 0;
        direction = DOWN;

        body.clear();

        head = new Point(0, 0);

        for (int i = 0; i < 10; i++) {
            body.add(new Point(head.x, head.y));
        }

    }


    public void run() {
        powerUp = new PowerUP(this);
        gameFrame.setVisible(true);
        gameFrame.setSize(800, 800);
        gameFrame.setResizable(false);

        //gameFrame.setLocation(dim.width / 2 - gameFrame.getWidth() / 2, dim.height / 2 - gameFrame.getHeight() / 2);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.add(renderer = new Renderer());
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.addKeyListener(this);

        timer.start();

    }

    public static void main(String[] args) {
        gameFrame = new JFrame();
        snake = new Snake();
        snake.run();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        renderer.repaint();
        ticks++;


        System.out.println("Direction: " + direction);
        System.out.println("Head X: " + head.x + ", Head Y: " + head.y);


        if (ticks % ticksRatio == 0 && head != null && !gameOver && !paused) {

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

            if (snake.head == snake.powerUp.cord) {
                System.out.println("ATTEMPT TO COLLECT");
                if (snake.powerUp.powerUpType == PowerUP.Type.POINT) {
                    snake.extend();
                    powerUp = new PowerUP(snake);
                } else {
                    snake.collectPowerUp(powerUp.powerUpType);
                    powerUp = new PowerUP(snake);
                }
            }
        }
    }

    public void extend() {
        snake.score += 10;
        System.out.println("Score:" + score);
        snake.body.add(new Point(head.x, head.y));
    }


    public void collectPowerUp(PowerUP.Type typeOfPowerUp) {
        if (typeOfPowerUp == PowerUP.Type.SLOW) {
            snake.ticksRatio = 8;
        } else {
            snake.ticksRatio = 3;
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
            if (gameOver) {
                snake = new Snake();
                snake.run();
            } else paused = !paused;

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
