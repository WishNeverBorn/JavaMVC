package model;

/**
 * Класс модели для цели на поле, к которой двигается робот
 */
public class TargetEntity extends Entity {
    public TargetEntity(){
        super(300, 300);
    }
    public void updatePosition(){

        int minX = 10;
        int minY = 10;
        int maxX = 390;
        int maxY = 390;
        int X = (int) (Math.random() * ++maxX) + minX;
        int Y = (int) (Math.random() * ++maxY) + minY;
        this.entityPoint.setLocation(X, Y);
        notifyObservers();
    }
}
