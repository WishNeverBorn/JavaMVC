package model;

/**
 * Класс модели для цели на поле, к которой двигается робот
 */
public class TargetEntity extends Entity {
    public TargetEntity(){
        super(300, 300);
    }
    public void updatePosition(double X, double Y){
        this.entityPoint.setLocation(X, Y);
    }
}
