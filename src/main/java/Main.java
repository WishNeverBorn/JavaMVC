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
 *
 * Идеи для развития проекта:
 * 1.Хочется дать игроку более гибкое управление - переделать управление движением под нажатие клавиш
 *
 * 2.В случае ручного управления мишень для контроля движения становится ненужной - можно сделать из нее игровой объект
 *  2.1.Наш робот может "подбирать" эти объекты и получать за них очки
 *
 * 3.Окно для отображения координат робота можно использовать для отображения раскладки, либо для отображения общего счета очков
 */
public class Main
{
    public static void main(String[] args)
    {
        Locale locale = new Locale("ru", "RU");
        ApplicationController controller = new ApplicationController();

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
            //UIManager.setLookAndFeel(UIManager.getCыrossPlatformLookAndFeelClassName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() ->
        {
            MainApplicationFrame frame = new MainApplicationFrame(controller);
            frame.pack();
            frame.setVisible(true);
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        });
    }}

