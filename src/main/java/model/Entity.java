package model;

/**
 * Общий класс описывающий абстрактную модель, предоставляя для нее готовый набор параметров
 */
public abstract class Entity {
    protected double positionX;
    protected double positionY;
    public Entity(double X, double Y){
        positionX = X;
        positionY = Y;
    }
    public double[] getEntityCoordinates(){
        return new double[]{positionX, positionY};
    }
    public int round(double value)
    {
        return (int)(value + 0.5);
    }
}
