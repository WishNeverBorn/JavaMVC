package model;

import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Класс модели для цели на поле, к которой двигается робот
 */
public class TargetEntity extends Entity {
    private final Random random = new Random();
    public TargetEntity(){
        super(300, 300);
    }
    public void updatePosition(double X, double Y){
        this.entityPoint.setLocation(X, Y);
    }
    public void randomPosition(Point2D edgePoint){
        double newX = random.nextDouble(10.0, edgePoint.getX());
        double newY = random.nextDouble(10.0, edgePoint.getY());
        updatePosition(newX, newY);
    }
}
