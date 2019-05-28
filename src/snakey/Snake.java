package snakey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import static com.sun.webkit.graphics.GraphicsDecoder.SCALE;

public class Snake implements ActionListener {

    public JFrame gameFrame;

    public static Snake snake;
    public Renderer renderer;

    public Timer timer = new Timer(20, this);

    public ArrayList<Point> body = new ArrayList<Point>();

    public int ticks = 0, direction = DOWN, score, taillength = 10;

    public Point head, powerup;

    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT =3, SCALE = 10;

    public  boolean over = false;

    public Random random;

    public Dimension dim;

    public Snake(){
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        gameFrame = new JFrame("Snakey");
        gameFrame.setVisible(true);
        gameFrame.setSize(800,800);
        gameFrame.setLocation(dim.width/2 - gameFrame.getWidth()/2, dim.height/2 - gameFrame.getHeight()/2);
        gameFrame.add(renderer = new Renderer());
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        head = new Point(0,0);

        random = new Random();
        powerup = new Point(dim.width / SCALE, dim.height / SCALE);


        for (int i = 0; i < taillength; i++){
            body.add(new Point(head.x, head.y));
        }
        timer.start();
    }



    public static void main(String[] args){
        snake = new Snake();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        renderer.repaint();
        ticks++;



        if(ticks % 10 == 0 && head != null && over != true){

            body.add(new Point(head.x, head.y));
            if (direction == DOWN)
            {
                if (head.y + 1 < dim.height / SCALE)
                head = new Point(head.x, head.y + 1);
                else over = true;
            }

            if (direction == UP)
            {
                if(head.y - 1 > 0)
                head = new Point(head.x, head.y - 1);
                else over = true;
            }
            if (direction == LEFT)
            {
                if (head.x - 1 >0)
                head = new Point(head.x - 1, head.y);
                else  over = true;
            }
            if (direction == RIGHT)
            {
                if (head.x + 1 < dim.width / SCALE)
                head = new Point(head.x + 1, head.y);
                else over = true;
            }

            //head = body.get(body.size() - 1);

            /**for ( int i = 0; i < taillength; i++)
            {
                body.remove(0);
            }*/



            if (powerup != null){
                if (head.x == powerup.x && head.y == powerup.y)
                {
                    score += 10;
                    taillength++;
                    powerup.setLocation(dim.width / SCALE, dim.height / SCALE);
                }
            }
        }
    }
}
