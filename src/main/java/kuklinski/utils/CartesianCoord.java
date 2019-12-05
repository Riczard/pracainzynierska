package kuklinski.utils;

import javafx.geometry.Rectangle2D;
import kuklinski.App;

public class CartesianCoord {

    private static Rectangle2D screenSize = App.getScreen();

    public static double getX(double x) {
        return screenSize.getWidth()/2 + x;
    }

    public static double getY(double y) {
        return screenSize.getHeight()/2 + y;
    }
}
