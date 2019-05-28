package snakey;

import java.awt.*;
import java.util.Random;

public class PowerUP {
    enum Type {SPEED, SLOW, POINT}

    Type powerUpType;
    Random random = new Random();
    Point cord;
    Snake snakey;
    int counterForPowerUps;

    public PowerUP(Snake snake) {
        snakey = snake;
        cord = new Point(random.nextInt(snake.dim.width / snake.SCALE), random.nextInt(snake.dim.height / snake.SCALE));
        double tempNumberOfRand = random.nextDouble() % 10;
        if (tempNumberOfRand <= 8) {
            powerUpType = Type.POINT;
        } else if (tempNumberOfRand <= 9) {
            powerUpType = Type.SLOW;
        } else {
            powerUpType = Type.SPEED;
        }
        counterForPowerUps = 0;

    }

    public void remake() {
        cord = new Point(random.nextInt(snakey.dim.width / snakey.SCALE), random.nextInt(snakey.dim.height / snakey.SCALE));
        if (counterForPowerUps != 5) {
            double tempNumberOfRand = random.nextDouble() % 10;
            if (tempNumberOfRand <= 8) {
                powerUpType = Type.POINT;
                counterForPowerUps++;
            } else if (tempNumberOfRand <= 9) {
                powerUpType = Type.SLOW;
                counterForPowerUps = 0;
            } else {
                powerUpType = Type.SPEED;
                counterForPowerUps = 0;
            }
        } else {
            double tempNumberOfRand = random.nextDouble() % 10;

            if (tempNumberOfRand <= 5) {
                powerUpType = Type.SLOW;
                counterForPowerUps = 0;
            } else {
                powerUpType = Type.SPEED;
                counterForPowerUps = 0;
            }
        }
    }
}
