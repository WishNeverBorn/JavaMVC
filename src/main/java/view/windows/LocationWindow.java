package view.windows;

import model.GameModel;
import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class LocationWindow extends JInternalFrame {
    private JLabel label;
    private JPanel panel;
    private GameModel robotModel = new GameModel();
    private final java.util.Timer locTimer = initTimer();
    private String coordinates = "Координаты робота X=%f Y=%f";
    private static java.util.Timer initTimer()
    {
        java.util.Timer timer = new Timer("events generator", true);
        return timer;
    }
    public LocationWindow()
    {
        super("Координаты робота", true, true, true, true);
        panel = new JPanel(new BorderLayout());
        label = new JLabel(String.format(coordinates, 100.0, 100.0));

        getContentPane().add(panel);
        getContentPane().add(label);
        pack();

        locTimer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                updateCoordinates(robotModel.getRobotPosition());
            }
        }, 0, 10);
    }

    public void updateCoordinates(double[] robotPosition){
        label.setText(String.format(coordinates, robotPosition[0], robotPosition[1]));
    }
}
