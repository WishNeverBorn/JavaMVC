package model;

import view.visualizers.GameVisualizer;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Общий класс описывающий абстрактную модель, предоставляя для нее готовый набор параметров
 */
public abstract class Entity implements Observable{
    protected Point2D entityPoint = new Point2D.Double();
    public Entity(double X, double Y){
        this.entityPoint.setLocation(X, Y);
    }
    public Point2D getEntityCoordinates(){return entityPoint;}
    public int round(double value)
    {
        return (int)(value + 0.5);
    }
    private final ArrayList<GameVisualizer> observers = new ArrayList<>();
    @Override
    public void addObserver(GameVisualizer observer) {
        observers.add(observer);
    }
    @Override
    public void removeObserver(GameVisualizer observer) {
        observers.remove(observer);
    }
    @Override
    public void notifyObservers() {
        for (GameVisualizer observer : observers) {
            observer.update(this);
        }
    }
}
