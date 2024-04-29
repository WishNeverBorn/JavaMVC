package model;

/**
 * Класс модели для цели на поле, к которой двигается робот
 */
public class TargetEntity extends Entity {
    public TargetEntity(){
        super(300, 300);
    }
    public void updatePosition(){

        int minX = 20;
        int minY = 20;
        int maxX = 580;
        int maxY = 580;
        int X = (int) (Math.random() * ++maxX) + minX;
        int Y = (int) (Math.random() * ++maxY) + minY;
        this.entityPoint.setLocation(X, Y);
        notifyObservers();
    }
}
