package view.windows;

import javax.swing.*;
import java.awt.*;

/**
 * Класс информации по игре, содержит справку по управлению
 * TODO:
 *  Добавить панельку которая делает подсчет игровых очков
 */
public class InformationWindow extends JInternalFrame {
    public final String manual = "Управление\n"
            + "Enter - начать игру/приостановить игру\n"
            + "W - направление вверх\n"
            + "A - направление влево\n"
            + "S - направление вниз\n"
            + "D - направление вправо\n";
    public InformationWindow()
    {
        super("Окно справки", true, true, true, true);
        JPanel panel = new JPanel(new BorderLayout());
        JLabel controlsLabel = new JLabel("<html>" + manual.replace("\n", "<br>") + "</html>");
        getContentPane().add(panel);
        getContentPane().add(controlsLabel, BorderLayout.WEST);
        pack();
    }
}
