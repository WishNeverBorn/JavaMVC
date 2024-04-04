import controller.ApplicationController;
import view.MainApplicationFrame;
import view.visualizers.GameVisualizer;
import java.awt.Frame;
import javax.swing.SwingUtilities;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.*;

/**
 * Main класс
 * Точка входа в программу
 */
public class Main
{
    public static void main(String[] args)
    {
        Locale locale = new Locale("ru", "RU");
        GameVisualizer m_visualizer = new GameVisualizer();
        ApplicationController m_controller = new ApplicationController(m_visualizer);

        try
        {
            UIManager.put("OptionPane.yesButtonText",
                    ResourceBundle.getBundle("lang", locale)
                            .getString("option.yes"));
            UIManager.put("OptionPane.noButtonText",
                    ResourceBundle.getBundle("lang", locale)
                            .getString("option.no"));
            //UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() ->
        {
            MainApplicationFrame frame = new MainApplicationFrame(m_visualizer);
            frame.pack();
            frame.setVisible(true);
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        });
    }}

