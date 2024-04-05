package view;
import java.awt.event.KeyEvent;
import javax.swing.*;

import log.Logger;

public class MenuBarGenerator extends ClosableFrame {

    private final JDesktopPane desktopPane;
    public MenuBarGenerator(JDesktopPane desktopPane)
    {
        this.desktopPane = desktopPane;
    };

    public JMenuBar generateMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();

        menuBar.add(setLookAndFeelMenu());
        menuBar.add(setTestMenu());
        menuBar.add(setSystemMenu());
        return menuBar;
    }

    private JMenu setLookAndFeelMenu()
    {
        JMenu lookAndFeelMenu = new JMenu("Режим отображения");
        lookAndFeelMenu.setMnemonic(KeyEvent.VK_V);
        lookAndFeelMenu.getAccessibleContext().setAccessibleDescription(
                "Управление режимом отображения приложения");

        {
            JMenuItem systemLookAndFeel = new JMenuItem("Системная схема", KeyEvent.VK_S);
            systemLookAndFeel.addActionListener((event) -> {
                setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                this.invalidate();
            });
            lookAndFeelMenu.add(systemLookAndFeel);
        }

        {
            JMenuItem crossplatformLookAndFeel = new JMenuItem("Универсальная схема", KeyEvent.VK_S);
            crossplatformLookAndFeel.addActionListener((event) -> {
                setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                this.invalidate();
            });
            lookAndFeelMenu.add(crossplatformLookAndFeel);
        }

        return lookAndFeelMenu;
    }

    private void setLookAndFeel(String className)
    {
        try
        {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(this);
        }
        catch (ClassNotFoundException | InstantiationException
               | IllegalAccessException | UnsupportedLookAndFeelException e)
        {
            // just ignore
        }
    }

    private JMenu setTestMenu()
    {
        JMenu testMenu = new JMenu("Тесты");
        testMenu.setMnemonic(KeyEvent.VK_T);
        testMenu.getAccessibleContext().setAccessibleDescription(
                "Тестовые команды");

        {
            JMenuItem addLogMessageItem = new JMenuItem("Сообщение в лог", KeyEvent.VK_S);
            addLogMessageItem.addActionListener((event) -> {
                Logger.debug("Новая строка");
            });
            testMenu.add(addLogMessageItem);
        }

        return testMenu;
    }

    private JMenu setSystemMenu()
    {
        JMenu systemMenu = new JMenu("Система");
        systemMenu.setMnemonic(KeyEvent.VK_X);
        systemMenu.getAccessibleContext().setAccessibleDescription(
                "Уаправление работой приложения");

        {
            JMenuItem menuCloseOperation = new JMenuItem("Закрыть приложение", KeyEvent.VK_X);
            menuCloseOperation.addActionListener((event) -> closeWithConfirm(desktopPane));
            systemMenu.add(menuCloseOperation);
        }

        return systemMenu;
    }
}
