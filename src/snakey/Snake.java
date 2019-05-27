package snakey;

import javax.swing.*;
import java.awt.*;

public class Snake {

    public JFrame gameFrame;

    public static Snake snake;
    //public Toolkit toolkit;

    public Snake(){
        //toolkit = Toolkit.getDefaultToolkit();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        gameFrame = new JFrame("Snakey");
        gameFrame.setVisible(true);
        gameFrame.setSize(800,800);
        gameFrame.setLocation(dim.width/2 - gameFrame.getWidth()/2, dim.height/2 - gameFrame.getHeight()/2);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    public static void main(String[] args){
        snake = new Snake();
    }
}
