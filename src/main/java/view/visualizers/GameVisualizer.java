package view.visualizers;

import model.GameModel;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

/**
 * Класс визуализатор на данный момент некорректный
 * Совмещает функционал контроллера и визуализатора
 * Задачи:
 * 1. Отделить функционал в контроллер
 */
public class GameVisualizer extends JPanel
{
    private TargetVisualiser targetVisualiser = new TargetVisualiser();
    private GameModel robotModel = new GameModel();
    private final Timer m_timer = initTimer();

    private static Timer initTimer()
    {
        Timer timer = new Timer("events generator", true);
        return timer;
    }

    public GameVisualizer()
    {
        m_timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                onRedrawEvent();
            }
        }, 0, 50);
        m_timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                onModelUpdateEvent();
            }
        }, 0, 10);
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                targetVisualiser.setTargetPosition(e.getPoint());
                repaint();
            }
        });
        setDoubleBuffered(true);
    }

    protected void onRedrawEvent()
    {
        EventQueue.invokeLater(this::repaint);
    }

    protected void onModelUpdateEvent()
    {
        int[] targetCoordinate = targetVisualiser.getTargetPosition();
        robotModel.updatePosition(10, targetCoordinate);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        drawRobot(g2d, robotModel);
        targetVisualiser.drawTarget(g2d);
    }

    public static void fillOval(Graphics g, int centerX, int centerY, int diam1, int diam2)
    {
        g.fillOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }

    public static void drawOval(Graphics g, int centerX, int centerY, int diam1, int diam2)
    {
        g.drawOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }

    private void drawRobot(Graphics2D g, GameModel robotModel)
    {
        double[] coordinate = robotModel.getRobotPosition();
        int robotCenterX = robotModel.round(coordinate[0]);
        int robotCenterY = robotModel.round(coordinate[1]);

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
}
