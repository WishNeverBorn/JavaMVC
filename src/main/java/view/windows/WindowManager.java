package view.windows;

import controller.ApplicationController;
import log.Logger;
import javax.swing.*;
import java.awt.*;

/**
 * Класс менеджера для создания окон
 * TODO
 * выделить abstract class для обобщения?
 */
public class WindowManager {
    public static void addWindow(JDesktopPane desktopPane, JInternalFrame frame){
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    public static LogWindow createLogWindow()
    {
        LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource());
        logWindow.setSize(200, 600);
        Point location = new Point(10, 10);
        logWindow.setLocation(location);
        Logger.debug("Протокол работает");
        return logWindow;
    }

    public static GameWindow createGameWindow(ApplicationController controller)
    {
        GameWindow gameWindow = new GameWindow(controller);
        gameWindow.setSize(400, 400);
        Point location = new Point(300, 10);
        gameWindow.setLocation(location);
        return gameWindow;
    }

    public static LocationWindow createLocationWindow()
    {
        LocationWindow locationWindow = new LocationWindow();
        locationWindow.setSize(400, 200);
        Point location = new Point(800, 10);
        locationWindow.setLocation(location);
        return locationWindow;
    }

}
