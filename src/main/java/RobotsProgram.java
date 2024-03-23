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
            String yes = new String(ResourceBundle.getBundle("lang", locale)
                    .getString("option.yes")
                    .getBytes(StandardCharsets.ISO_8859_1),
                    StandardCharsets.UTF_8);
            UIManager.put("OptionPane.yesButtonText", yes);
            UIManager.put("OptionPane.noButtonText", "Нет");

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

