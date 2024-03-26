import view.MainApplicationFrame;

import java.awt.Frame;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringJoiner;

import javax.naming.ldap.Control;
import javax.swing.*;

/**
 * Main класс
 * Точка входа в программу
 */
public class RobotsProgram
{
    public static void main(String[] args) {
        Locale locale = new Locale("ru", "RU");
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            MainApplicationFrame frame = new MainApplicationFrame();
            frame.pack();
            frame.setVisible(true);
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        });
    }}

