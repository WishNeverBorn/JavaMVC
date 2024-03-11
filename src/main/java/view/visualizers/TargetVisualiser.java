package view.visualizers;
import java.awt.*;
import java.awt.geom.AffineTransform;

import static view.visualizers.GameVisualizer.drawOval;
import static view.visualizers.GameVisualizer.fillOval;

public class TargetVisualiser {
    private int targetPositionX = 200;
    private int targetPositionY = 200;

    public void setTargetPosition(Point p)
    {
        targetPositionX = p.x;
        targetPositionY = p.y;
    }

    public void drawTarget(Graphics2D g)
    {
        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0);
        g.setTransform(t);
        g.setColor(Color.GREEN);
        fillOval(g, targetPositionX, targetPositionY, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, targetPositionX, targetPositionY, 5, 5);
    }

    public int[] getTargetPosition(){
        return new int[] {targetPositionX, targetPositionY};
    }
}
