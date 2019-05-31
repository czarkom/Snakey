package snakey;

import java.util.ArrayList;
import java.awt.*;


public class Snake {

    public Point head;
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    int direction;
    public ArrayList<Point> body = new ArrayList<Point>();
    int speed;

    public Snake() {
        this.direction = DOWN;
        this.body.clear();
        this.head = null;
        this.head = new Point(0, 0);
        this.speed = 5;
        for (int i = 0; i < 10; i++) {
            this.body.add(new Point(this.head.x, this.head.y));
        }


    }
    public void collectPowerUp(PowerUP.Type typeOfPowerUp) {
        if (typeOfPowerUp == PowerUP.Type.SLOW && this.speed == 5) {
            this.speed = 10;
        } else if (this.speed == 5) {
            this.speed = 3;
        } else {
            this.speed = 5;
        }


    }
}