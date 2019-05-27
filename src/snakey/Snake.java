package snakey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Snake implements ActionListener {

    public JFrame gameFrame;

    public static Snake snake;
    public Renderer renderer;

    public Timer timer = new Timer(20, this);

    public Snake(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        gameFrame = new JFrame("Snakey");
        gameFrame.setVisible(true);
        gameFrame.setSize(800,800);
        gameFrame.setLocation(dim.width/2 - gameFrame.getWidth()/2, dim.height/2 - gameFrame.getHeight()/2);
        gameFrame.add(renderer = new Renderer());
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        timer.start();
    }



    public static void main(String[] args){
        snake = new Snake();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        renderer.repaint();
    }
}
