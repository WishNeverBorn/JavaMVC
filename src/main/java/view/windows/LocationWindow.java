package view.windows;

import model.RobotEntity;
import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Класс для окна координат робота
 */
public class LocationWindow extends JInternalFrame {
    private final JLabel label;
    private final JPanel panel;
     String coordinates = "Координаты робота X=%f Y=%f";
    public LocationWindow()
    {
        super("Координаты робота", true, true, true, true);
        panel = new JPanel(new BorderLayout());
        label = new JLabel(String.format(coordinates, 100.0, 100.0));

        getContentPane().add(panel);
        getContentPane().add(label);
        pack();
    }

    public void updateCoordinates(double[] robotPosition){
        label.setText(String.format(coordinates, robotPosition[0], robotPosition[1]));
    }
}
