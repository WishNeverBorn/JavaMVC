package view.windows;

import log.Logger;

import javax.swing.*;

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

    public static GameWindow createGameWindow(int width, int height)
    {
        GameWindow gameWindow = new GameWindow();
        gameWindow.setSize(width, height);
        return gameWindow;
    }

}
