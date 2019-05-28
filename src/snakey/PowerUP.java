package snakey;
import java.awt.*;
import java.util.Random;

public class PowerUP {
    enum Type {SPEED,SLOW,POINT}
    Type powerUpType;
    Random random = new Random();
    Point cord;

    public PowerUP(Snake snake){
        cord = new Point(random.nextInt(snake.dim.width / snake.SCALE), random.nextInt(snake.dim.height / snake.SCALE));
        int tempNumberOfRand = random.nextInt()%10;
        if(tempNumberOfRand <= 8){
            powerUpType = Type.POINT;
        }
        else if(tempNumberOfRand <=9 ){
            powerUpType = Type.SLOW;
        }
        else{
            powerUpType = Type.SPEED;
        }
    }
}
