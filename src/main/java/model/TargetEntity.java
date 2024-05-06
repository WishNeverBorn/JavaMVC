package model;

import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Класс модели для цели на поле, к которой двигается робот
 */
public class TargetEntity extends Entity {
    private final Random random = new Random();
    private Point2D windowSize = new Point2D.Double();
    public TargetEntity(){
        super(300, 300);
    }
    public void updatePosition(double X, double Y){
        this.entityPoint.setLocation(X, Y);
    }
    public void randomPosition(){
        double newX = random.nextDouble(10.0, windowSize.getX());
        double newY = random.nextDouble(10.0, windowSize.getY());
        updatePosition(newX, newY);
    }
    public void setWindowSize(Point2D newWindowSize){
        windowSize = newWindowSize;
    }
}
