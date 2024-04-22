package view.windows;

import controller.ApplicationController;
import log.Logger;
import javax.swing.*;

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
        LogWindow lgWindow = new LogWindow(Logger.getDefaultLogSource());
        lgWindow.setLocation(10,10);
        lgWindow.setSize(300, 800);
        lgWindow.setMinimumSize(lgWindow.getSize());
        lgWindow.pack();
        Logger.debug("Протокол работает");
        return lgWindow;
    }

    public static GameWindow createGameWindow(ApplicationController controller, int width, int height)
    {
        GameWindow gameWindow = new GameWindow(controller);
        gameWindow.setSize(width, height);
        return gameWindow;
    }

    public static LocationWindow createLocationWindow(int width, int height)
    {
        LocationWindow locationWindow = new LocationWindow();
        locationWindow.setSize(width, height);
        return locationWindow;
    }

}
