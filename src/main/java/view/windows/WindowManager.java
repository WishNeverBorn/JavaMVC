package view.windows;

import controller.ApplicationController;
import log.Logger;
import javax.swing.*;
import java.util.List;

/**
 * Класс менеджера для создания окон
 * TODO
 * выделить abstract class для обобщения?
 */
public class WindowManager {
    public static void addListWindows(JDesktopPane desktopPane, List<JInternalFrame> frames){
        for(JInternalFrame frame : frames){
            desktopPane.add(frame);
            frame.setVisible(true);
        }
    }

    public static LogWindow createLogWindow(int width, int height)
    {
        LogWindow lgWindow = new LogWindow(Logger.getDefaultLogSource());
        lgWindow.setLocation(10,10);
        lgWindow.setSize(width, height);
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

    public static InformationWindow createLocationWindow(int width, int height)
    {
        InformationWindow informationWindow = new InformationWindow();
        informationWindow.setSize(width, height);
        return informationWindow;
    }

}
