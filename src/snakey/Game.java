package snakey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class Game implements ActionListener, KeyListener {

    public static JFrame gameFrame;

    public static final int SCALE = 10;

    Music musicNormal = new Music();


    public static Game game;

    public Renderer renderer;

    public Timer timer = new Timer(10, this);

    int ticks = 0, score;

    public Wall walls;

    public Snake snake;

    public PowerUP powerUp;

    public boolean gameOver = false, paused;

    public Dimension dim;

    public Game() {

        dim = new Dimension(800, 800);
        walls = new Wall();
        gameOver = false;
        paused = false;
        score = 0;
        snake = new Snake();
    }

    void restartGame() {
        game.gameOver = false;
        game.paused = false;
        game.score = 0;
        snake = null;
        snake = new Snake();
        //this.run();
    }


    public void run() {
        powerUp = new PowerUP(this);
        gameFrame.setVisible(true);
        gameFrame.setSize(800, 800);
        gameFrame.setResizable(false);

        //gameFrame.setLocation(dim.width / 2 - gameFrame.getWidth() / 2, dim.height / 2 - gameFrame.getHeight() / 2);
        gameFrame.setLocationRelativeTo(null);
        /*if (renderer != null) {
            gameFrame.remove(renderer);
        }      */
        gameFrame.add(renderer = new Renderer());
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.addKeyListener(this);
        ticks = 0;
        gameOver = false;
        timer.start();

    }

    public static void main(String[] args) {
        gameFrame = new JFrame();
        game = new Game();
        game.run();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        renderer.repaint();
        ticks++;


        if (ticks % snake.speed == 0 && snake.head != null && !gameOver && !paused) {

            snake.lastDirection = snake.direction;

            snake.body.add(new Point(snake.head.x, snake.head.y));

            if (snake.direction == snake.DOWN) {
                if (snake.head.y + 3 < dim.height / SCALE) {
                    snake.head = new Point(snake.head.x, snake.head.y + 1);

                } else gameOver = true;
            }

            if (snake.direction == snake.UP) {
                if (snake.head.y - 1 >= 0)
                    snake.head = new Point(snake.head.x, snake.head.y - 1);
                else gameOver = true;
            }
            if (snake.direction == snake.LEFT) {
                if (snake.head.x - 1 >= 0)
                    snake.head = new Point(snake.head.x - 1, snake.head.y);
                else gameOver = true;
            }
            if (snake.direction == snake.RIGHT) {
                if (snake.head.x + 1.9 < (float) dim.width / SCALE)
                    snake.head = new Point(snake.head.x + 1, snake.head.y);
                else gameOver = true;
            }
            for (Point point : game.walls.cords) {
                if (snake.head.equals(point)) {
                    gameOver = true;
                }
            }
            for (Point point : snake.body) {
                if (point.x == snake.head.x && point.y == snake.head.y) gameOver = true;
            }


            snake.body.remove(0);

            if (game.powerUp.specialAvailable && snake.head.equals(game.powerUp.specialCord)) {
                snake.collectPowerUp(powerUp.specialPowerUpType);
                game.powerUp.specialAvailable = false;
            }


            if (snake.head.equals(game.powerUp.cord)) {

                game.extend();
                powerUp.remake();

            }


        }
    }


    public void extend() {
        game.score += 10;
        snake.body.add(new Point(snake.head.x, snake.head.y));
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!game.paused && !game.gameOver) {
            int i = e.getKeyCode();
            if (i == KeyEvent.VK_A && snake.direction != snake.RIGHT && snake.lastDirection != snake.RIGHT)
                snake.direction = snake.LEFT;
            if (i == KeyEvent.VK_W && snake.direction != snake.DOWN && snake.lastDirection != snake.DOWN)
                snake.direction = snake.UP;
            if (i == KeyEvent.VK_S && snake.direction != snake.UP && snake.lastDirection != snake.UP)
                snake.direction = snake.DOWN;
            if (i == KeyEvent.VK_D && snake.direction != snake.LEFT && snake.lastDirection != snake.LEFT)
                snake.direction = snake.RIGHT;
            if (i == KeyEvent.VK_M) {
                if (musicNormal.getMusicState()) {
                    musicNormal.stopMusic();
                } else {
                    musicNormal.playMusic();
                }
            }
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        int i = e.getKeyCode();
        if (i == KeyEvent.VK_SPACE) {
            if (game.gameOver) {
                game.restartGame();

            } else paused = !paused;
        }
    }
}