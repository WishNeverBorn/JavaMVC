package view.windows;

import controller.ApplicationController;
import java.awt.BorderLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 * Класс окна для игрового поля и робота
 */
public class GameWindow extends JInternalFrame
{
    public GameWindow(ApplicationController controller)
    {
        super("Игровое поле", true, true, true, true);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(controller);
        panel.add(controller.getGameVisualizer(), BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }
}

