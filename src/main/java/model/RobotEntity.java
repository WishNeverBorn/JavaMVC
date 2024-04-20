package model;

import view.visualizers.GameVisualizer;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Класс модели робота, занимается рассчетами траектории движения робота относительно цели
 */
public class RobotEntity extends Entity implements Observable{
    private double robotDirection = 0;
    private static final double maxVelocity = 0.1;
    private static final double maxAngularVelocity = 0.000;
    private final ArrayList<GameVisualizer> observers = new ArrayList<>();
    public RobotEntity(){
        super(100, 100);
    }
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
            observer.update();
        }
    }
    public void directByKey(int keyCode){
        robotDirection = setDirection(keyCode);
    }

    //Использовать для нормализации double'ов
    private static double applyLimits(double value, double min, double max)
    {
        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
    }
    private double setDirection(int keyCode){
        double direction = 0.0;
        switch(keyCode){
            case KeyEvent.VK_W -> {
                direction = -Math.PI / 2;
            }
            case KeyEvent.VK_A -> {
                direction = Math.PI;
            }
            case KeyEvent.VK_S -> {
                direction = Math.PI / 2;
            }
            case KeyEvent.VK_D -> {
                direction = 0;
            }
        }
        return direction;
    }
    public void moveRobot(double duration)
    {
        double positionX = this.entityPoint.getX();
        double positionY = this.entityPoint.getY();

        double newX = positionX + maxVelocity / (Math.sin(robotDirection + maxAngularVelocity * duration) - Math.sin(robotDirection));
        if (!Double.isFinite(newX))
        {
            newX = positionX + maxVelocity * duration * Math.cos(robotDirection);
        }

        double newY = positionY - maxVelocity / (Math.cos(robotDirection + maxAngularVelocity * duration) - Math.cos(robotDirection));
        if (!Double.isFinite(newY))
        {
            newY = positionY + maxVelocity * duration * Math.sin(robotDirection);
        }

        this.entityPoint.setLocation(newX, newY);
        robotDirection = asNormalizedRadians(robotDirection + maxAngularVelocity * duration);
        notifyObservers();
    }
    private static double asNormalizedRadians(double angle)
    {
        while (angle < 0)
        {
            angle += 2*Math.PI;
        }
        while (angle >= 2*Math.PI)
        {
            angle -= 2*Math.PI;
        }
        return angle;
    }
    public double getRobotDirection() {return robotDirection; }
}
