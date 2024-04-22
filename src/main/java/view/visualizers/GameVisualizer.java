package view.visualizers;

import model.RobotEntity;
import model.TargetEntity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import javax.swing.*;

/**
 * Класс визуализатор на данный момент некорректный
 * Совмещает функционал контроллера и визуализатора
 * Задачи:
 * 1. MouseListener по-хорошему надо вынести в контроллер (или нет? контроллер же регулирует работу визуализатора)
 * 2. Сделать общего для визуализаторов предка (абстрактный  класс AbstractVisializer)
 */
public class GameVisualizer extends JPanel implements Observer
{
    private final RobotEntity robot;

    public GameVisualizer(RobotEntity robotEntity)
    {
        robot = robotEntity;
        robot.addObserver(this);
        setDoubleBuffered(true);
    }
    public void onModelUpdateEvent()
    {
        robot.moveRobot(10);
    }
    @Override
    public void update() {
        repaint();
    }
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        drawRobot(g2d, robot);
    }
    public static void fillOval(Graphics g, int centerX, int centerY, int diam1, int diam2)
    {
        g.fillOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }
    public static void drawOval(Graphics g, int centerX, int centerY, int diam1, int diam2)
    {
        g.drawOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }
    private void drawRobot(Graphics2D g, RobotEntity robotModel)
    {
        Point2D robotLocation = robot.getEntityCoordinates();
        int robotCenterX = robot.round(robotLocation.getX());
        int robotCenterY = robot.round(robotLocation.getY());

        AffineTransform t = AffineTransform.getRotateInstance(robotModel.getRobotDirection(), robotCenterX, robotCenterY);
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
    private void drawTarget(Graphics2D g, TargetEntity target)
    {
        Point2D robotLocation = target.getEntityCoordinates();
        int targetX = target.round(robotLocation.getX());
        int targetY = target.round(robotLocation.getY());

        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0);
        g.setTransform(t);
        g.setColor(Color.GREEN);
        fillOval(g, targetX, targetY, 8, 8);
        g.setColor(Color.BLACK);
        drawOval(g, targetX, targetY, 8, 8);
    }
}
