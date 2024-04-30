package view.windows;

import javax.swing.*;
import java.awt.*;

/**
 * Класс для окна координат робота
 */
public class InformationWindow extends JInternalFrame {
    private final JLabel label;
    private final JPanel panel;
    String coordinates = "Управление\n"
            + "Enter - начать игру\n"
            + "Пробел - пауза\n"
            + "W - направление вверх\n"
            + "A - направление влево\n"
            + "S - направление вниз\n"
            + "D - направление вправо\n";
    public InformationWindow()
    {
        super("Окно справки", true, true, true, true);
        panel = new JPanel(new BorderLayout());
        label = new JLabel("<html>" + coordinates.replace("\n", "<br>") + "</html>");
        getContentPane().add(panel);
        getContentPane().add(label, BorderLayout.CENTER);
        pack();
    }

    public void updateCoordinates(double[] robotPosition){
        label.setText(String.format(coordinates, robotPosition[0], robotPosition[1]));
    }
}
