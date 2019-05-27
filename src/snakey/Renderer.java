package snakey;

import javax.swing.*;
import java.awt.*;

public class Renderer extends JPanel {

    public  static int curColor = 0;
    public static int counter = 0;


    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(new Color(curColor));
        g.fillRect(0,0,800,800);
        if(counter < 250){
            curColor = counter;
            counter++;
        }
        if(counter == 250){
            curColor--;
            if (curColor == 0) counter = 0;
        }

        System.out.println(curColor + "," + counter);

    }
}
