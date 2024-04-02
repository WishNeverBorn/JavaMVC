package view.windows;

import view.visualizers.GameVisualizer;
import java.awt.BorderLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 * Класс окна для игрового поля и робота
 */
public class GameWindow extends JInternalFrame
{
    public GameWindow(GameVisualizer someVisualizer)
    {
        super("Игровое поле", true, true, true, true);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(someVisualizer, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }
}

