package view.visualizers;

import model.RobotEntity;
import model.TargetEntity;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;

/**
 * Класс визуализатор на данный момент некорректный
 * Совмещает функционал контроллера и визуализатора
 * Задачи:
 * 1. MouseListener по-хорошему надо вынести в контроллер, пока что не получилось
 */
public class GameVisualizer extends JPanel implements Observer
{
    private RobotEntity robot = new RobotEntity();
    private TargetEntity target = new TargetEntity();
    public GameVisualizer()
    {
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                modelsUpdateEvent(e);
            }
        });

        robot.addObserver(this);
        setDoubleBuffered(true);
    }
    public void modelsUpdateEvent(MouseEvent e){
        target.updatePosition(e.getPoint().x, e.getPoint().y);
        onModelUpdateEvent();
    }
    public void onModelUpdateEvent()
    {
        double[] targetCoordinate = target.getEntityCoordinates();
        robot.updatePosition(10, targetCoordinate);
    }
    protected void onRedrawEvent()
    {
        EventQueue.invokeLater(this::repaint);
    }
    @Override
    public void update() {
        onRedrawEvent();
    }
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        drawRobot(g2d, robot);
        drawTarget(g2d, target);
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
        double[] coordinate = robot.getEntityCoordinates();
        int robotCenterX = robot.round(coordinate[0]);
        int robotCenterY = robot.round(coordinate[1]);

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
        double[] position = target.getEntityCoordinates();
        int targetX = target.round(position[0]);
        int targetY = target.round(position[1]);

        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0);
        g.setTransform(t);
        g.setColor(Color.GREEN);
        fillOval(g, targetX, targetY, 8, 8);
        g.setColor(Color.BLACK);
        drawOval(g, targetX, targetY, 8, 8);
    }
}
