package model;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Общий класс описывающий абстрактную модель, предоставляя для нее готовый набор параметров
 */
public abstract class Entity {
    protected Point2D entityPoint = new Point2D.Double();
    public Entity(double X, double Y){
        this.entityPoint.setLocation(X, Y);
    }
    public Point2D getEntityCoordinates(){return entityPoint;}
    public int round(double value)
    {
        return (int)(value + 0.5);
    }
}
