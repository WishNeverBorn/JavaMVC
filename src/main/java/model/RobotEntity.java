package model;

import view.visualizers.GameVisualizer;

import java.util.ArrayList;

/**
 * Класс модели, занимается рассчетами траектории движения робота относительно цели
 * TODO:
 */
public class RobotEntity extends Entity implements Observable{
    private double robotDirection = 0;
    private static final double maxVelocity = 0.1;
    private static final double maxAngularVelocity = 0.001;
    private ArrayList<GameVisualizer> observers = new ArrayList<>();

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

    public void updatePosition(double duration, double[] targetCoordinate){
        double targetX = targetCoordinate[0];
        double targetY = targetCoordinate[1];

        double distance = distance(positionX, positionY, targetX, targetY);

        if (distance < 0.5)
        {
            return;
        }

        double angleToTarget = angleTo(positionX, positionY, targetX, targetY);
        double angularVelocity = 0;

        //Заменить на тернарный оператор
        if(robotDirection - angleToTarget > Math.PI){
            robotDirection -= 2*Math.PI;
        }
        else if(robotDirection - angleToTarget < -Math.PI){
            robotDirection += 2*Math.PI;
        }

        angularVelocity = (angleToTarget > robotDirection) ? maxAngularVelocity : -maxAngularVelocity;

        moveRobot(maxVelocity, angularVelocity, duration);
    }

    private static double distance(double x1, double y1, double x2, double y2)
    {
        double diffX = x1 - x2;
        double diffY = y1 - y2;

        return Math.sqrt(diffX * diffX + diffY * diffY);
    }

    private static double angleTo(double fromX, double fromY, double toX, double toY)
    {
        double diffX = toX - fromX;
        double diffY = toY - fromY;
        return asNormalizedRadians(Math.atan2(diffY, diffX));
    }


    private static double applyLimits(double value, double min, double max)
    {
        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
    }

    private void moveRobot(double velocity, double angularVelocity, double duration)
    {
        velocity = applyLimits(velocity, 0, maxVelocity);
        angularVelocity = applyLimits(angularVelocity, -maxAngularVelocity, maxAngularVelocity);

        double newX = positionX + velocity / angularVelocity *
                (Math.sin(robotDirection + angularVelocity * duration) - Math.sin(robotDirection));
        if (!Double.isFinite(newX))
        {
            newX = positionX + velocity * duration * Math.cos(robotDirection);
        }

        double newY = positionY - velocity / angularVelocity *
                (Math.cos(robotDirection + angularVelocity * duration) - Math.cos(robotDirection));
        if (!Double.isFinite(newY))
        {
            newY = positionY + velocity * duration * Math.sin(robotDirection);
        }

        positionX = newX;
        positionY = newY;
        robotDirection = asNormalizedRadians(robotDirection + angularVelocity * duration);

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
