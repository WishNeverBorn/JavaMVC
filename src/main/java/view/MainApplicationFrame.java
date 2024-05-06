package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.*;
import controller.ApplicationController;
import view.menuBar.ClosableFrame;
import view.menuBar.MenuBarGenerator;
import view.windowSerializer.WindowSerializer;

import static view.windows.WindowManager.*;

/**
 * Класс для создания главного окна приложения
 * Создает главное окно, окна логировагия и игры, а также меню
 */
public class MainApplicationFrame extends ClosableFrame
{
    private final JDesktopPane desktopPane = new JDesktopPane();

    public MainApplicationFrame(ApplicationController controller, WindowSerializer serializer) {
        setScreenAndBounds(returnInset(50));
        setContentPane(desktopPane);

        ArrayList<JInternalFrame> frames = new ArrayList<>();

        frames.add(createLogWindow(300, 800));
        frames.add(createLocationWindow(400, 400));
        frames.add(createGameWindow(controller, 400, 400));

        addListWindows(desktopPane, frames);
        serializer.loadWindows(frames);

        MenuBarGenerator barGenerator = new MenuBarGenerator(this);
        setJMenuBar(barGenerator.generateMenuBar());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setWindowListener(desktopPane, serializer);
    }

    public JDesktopPane getDesktopPane() {
        return desktopPane;
    }
    private void setScreenAndBounds(int inset){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                screenSize.width  - inset*2,
                screenSize.height - inset*2);
    }

    private int returnInset(int insetValue){
        return insetValue;
    }
}

