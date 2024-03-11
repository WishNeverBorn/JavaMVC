package model;
import java.util.Observable;

/**
 * Класс модели, занимается всеми рассчетами на основе действий пользователя
 */
public class GameModel {
    private static volatile double robotPositionX = 100;
    private static volatile double robotPositionY = 100;
    private double robotDirection = 0;
    private static final double maxVelocity = 0.1;
    private static final double maxAngularVelocity = 0.001;

    public GameModel(){}

    public void updatePosition(double duration, int[] targetCoordinate){
        int targetX = targetCoordinate[0];
        int targetY = targetCoordinate[1];

        double distance = distance(robotPositionX, robotPositionY,
                targetX, targetY);

        if (distance < 0.5)
        {
            return;
        }

        double angleToTarget = angleTo(robotPositionX, robotPositionY, targetX, targetY);
        double angularVelocity = 0;

        if(robotDirection - angleToTarget > Math.PI){
            robotDirection -= 2*Math.PI;
        }
        else if(robotDirection - angleToTarget < -Math.PI){
            robotDirection += 2*Math.PI;
        }


        if (angleToTarget > robotDirection)
        {
            angularVelocity = maxAngularVelocity;
        }
        else{
            angularVelocity = -maxAngularVelocity;
        }

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

        double newX = robotPositionX + velocity / angularVelocity *
                (Math.sin(robotDirection + angularVelocity * duration) - Math.sin(robotDirection));
        if (!Double.isFinite(newX))
        {
            newX = robotPositionX + velocity * duration * Math.cos(robotDirection);
        }

        double newY = robotPositionY - velocity / angularVelocity *
                (Math.cos(robotDirection + angularVelocity * duration) - Math.cos(robotDirection));
        if (!Double.isFinite(newY))
        {
            newY = robotPositionY + velocity * duration * Math.sin(robotDirection);
        }

        robotPositionX = newX;
        robotPositionY = newY;
        robotDirection = asNormalizedRadians(robotDirection + angularVelocity * duration);
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

    public int round(double value)
    {
        return (int)(value + 0.5);
    }
    public double[] getRobotPosition(){
        double[] coords = {robotPositionX, robotPositionY}; return coords;}
    public double getRobotDirection() {return robotDirection; }
}
