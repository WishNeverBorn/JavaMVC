package view.visualizers;

import model.Entity;
import model.RobotEntity;
import model.TargetEntity;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import javax.swing.*;

/**
 * Класс визуализатор на данный момент некорректный
 * Совмещает функционал контроллера и визуализатора
 * TODO:
 *  Возможно методы которые выводят уведомление о паузе игры лучше увести в код игрового окна
 */
public class GameVisualizer extends JPanel implements Observer
{
    private final RobotEntity robot;
    private final TargetEntity target;
    public GameVisualizer(RobotEntity robot, TargetEntity target)
    {
        this.robot = robot;
        this.target = target;
        setDoubleBuffered(true);
    }
    @Override
    public void update(Entity typeEntity) {
        repaint();
    }
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        drawRobot(g2d);
        drawTarget(g2d);
    }
    public static void fillOval(Graphics g, int centerX, int centerY, int diam1, int diam2)
    {
        g.fillOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }
    public static void drawOval(Graphics g, int centerX, int centerY, int diam1, int diam2)
    {
        g.drawOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }
    private void drawRobot(Graphics2D g)
    {
        double robotDirection = robot.getRobotDirection();
        Point2D robotLocation = robot.getEntityCoordinates();

        int robotCenterX = robot.round(robotLocation.getX());
        int robotCenterY = robot.round(robotLocation.getY());

        AffineTransform t = AffineTransform.getRotateInstance(robotDirection, robotCenterX, robotCenterY);
        g.setTransform(t);
        g.setColor(Color.ORANGE);
        fillOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.WHITE);
        fillOval(g, robotCenterX  + 10, robotCenterY, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX  + 10, robotCenterY, 5, 5);
    }
    private void drawTarget(Graphics2D g)
    {
        Point2D targetLocation = target.getEntityCoordinates();
        int targetX = target.round(targetLocation.getX());
        int targetY = target.round(targetLocation.getY());

        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0);
        g.setTransform(t);
        g.setColor(Color.GREEN);
        fillOval(g, targetX, targetY, 8, 8);
        g.setColor(Color.BLACK);
        drawOval(g, targetX, targetY, 8, 8);
    }
    public void hidePauseWindow(){
        JOptionPane.getRootFrame().dispose();
    }
    public void showPauseWindow(){
        JOptionPane.showMessageDialog(GameVisualizer.this, "ПАУЗА");
    }
    public Point2D getWindowSize(){
        Rectangle r = GameVisualizer.this.getBounds();
        return new Point2D.Double(r.width, r.height);
    }
}
