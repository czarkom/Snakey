package snakey;

import java.awt.*;
import java.util.Random;

public class PowerUP {
    enum Type {SPEED, SLOW, POINT}

    Type powerUpType;
    Type specialPowerUpType;
    Random random = new Random();
    Point cord;
    Point specialCord;
    Game game;
    int counterForPowerUps;
    boolean specialAvailable;


    public PowerUP(Game _game) {
        specialAvailable = false;
        game = _game;
        cord = new Point(random.nextInt((game.dim.width / game.SCALE) - 3) + 1, random.nextInt((game.dim.height / game.SCALE) - 3) + 1);
        double tempNumberOfRand = random.nextDouble() % 10;
        powerUpType = Type.POINT;

        if (tempNumberOfRand <= 0.8) {

            specialAvailable = true;

            double tempNumberOfRandSpecial = random.nextDouble();

            specialCord = new Point(random.nextInt((game.dim.width / game.SCALE) - 3) + 1, random.nextInt((game.dim.height / game.SCALE) - 3) + 1);

            if (specialCord.equals(cord)) {

                specialCord = new Point(random.nextInt((game.dim.width / game.SCALE) - 3) + 1, random.nextInt((game.dim.height / game.SCALE) - 3) + 1);
            }

            counterForPowerUps = 0;

            if (tempNumberOfRandSpecial >= 0.5) {
                specialPowerUpType = Type.SPEED;
            } else {
                specialPowerUpType = Type.SLOW;
            }

        }

    }

    public void remake() {

        if (counterForPowerUps != 5) {
            specialAvailable = false;
            double tempNumberOfRand = random.nextDouble();

            powerUpType = Type.POINT;
            cord = new Point(random.nextInt((game.dim.width / game.SCALE) - 3) + 1, random.nextInt((game.dim.height / game.SCALE) - 3) + 1);
            counterForPowerUps++;

            if (tempNumberOfRand <= 0.8) {

                specialAvailable = true;

                double tempNumberOfRandSpecial = random.nextDouble();

                specialCord = new Point(random.nextInt((game.dim.width / game.SCALE) - 3) + 1, random.nextInt((game.dim.height / game.SCALE) - 3) + 1);

                while (specialCord.equals(cord)) {

                    specialCord = new Point(random.nextInt((game.dim.width / game.SCALE) - 3) + 1, random.nextInt((game.dim.height / game.SCALE) - 3) + 1);
                }
                counterForPowerUps = 0;
                if (tempNumberOfRandSpecial >= 0.5) {
                    specialPowerUpType = Type.SPEED;
                } else {
                    specialPowerUpType = Type.SLOW;
                }

            }
        } else {
            specialAvailable = true;
            double tempNumberOfRand = random.nextDouble();
            if (tempNumberOfRand <= 0.5) {
                specialPowerUpType = Type.SLOW;
                while (specialCord.equals(cord)) {

                    specialCord = new Point(random.nextInt((game.dim.width / game.SCALE) - 3) + 1, random.nextInt((game.dim.height / game.SCALE) - 3) + 1);
                }
                counterForPowerUps = 0;
            } else {
                specialPowerUpType = Type.SPEED;
                while (specialCord.equals(cord)) {

                    specialCord = new Point(random.nextInt((game.dim.width / game.SCALE) - 3) + 1, random.nextInt((game.dim.height / game.SCALE) - 3) + 1);
                }
                counterForPowerUps = 0;
            }
        }
    }
}
