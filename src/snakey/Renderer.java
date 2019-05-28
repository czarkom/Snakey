package snakey;

import javax.swing.*;
import java.awt.*;

public class Renderer extends JPanel {

      static int curColor = 0;
      static int counter = 0; //czemu static?


    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);
        g.setColor(new Color(curColor));
        g.fillRect(0,0,800,800);
        Snake snake = Snake.snake;
        g.setColor(Color.YELLOW);

        for (Point point : snake.body){
            g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE,
                    Snake.SCALE, Snake.SCALE);
        }

        g.fillRect(snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE,
                Snake.SCALE, Snake.SCALE);

        g.setColor(Color.RED);

        g.fillRect(snake.powerUp.cord.x * Snake.SCALE, snake.powerUp.cord.y * Snake.SCALE,
                Snake.SCALE, Snake.SCALE);

        if(counter < 30){
            curColor = counter;
            counter++;
        }
        if(counter == 30){
            curColor--;
            if (curColor == 0) counter = 0;
        }

        System.out.println("Colors: " + curColor + "," + counter);

    }
}
