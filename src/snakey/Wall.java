package snakey;

import java.awt.*;
import java.util.ArrayList;

public class Wall {
    public ArrayList<Point> cords = new ArrayList<>();


    public Wall() {
        for (int i = 0; i < 80; i++) {
            for (int j = 0; j < 80; j++) {
                if (i == 0 || i == 79 || j == 0 || j == 77) {
                    cords.add(new Point(i, j));
                }
            }
        }
    }

}
