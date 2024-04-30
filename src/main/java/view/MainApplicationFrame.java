package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;
import controller.ApplicationController;
import view.menuBar.ClosableFrame;
import view.menuBar.MenuBarGenerator;
import view.windowsSerializer.WindowSerializer;

import static view.windows.WindowManager.*;

/**
 * Класс для создания главного окна приложения
 * Создает главное окно, окна логировагия и игры, а также меню
 */
public class MainApplicationFrame extends ClosableFrame
{
    private final JDesktopPane desktopPane = new JDesktopPane();
    private final ArrayList<JInternalFrame> frames = new ArrayList<>();
    public MainApplicationFrame(ApplicationController controller, WindowSerializer serializer) {
        setScreenAndBounds(returnInset(50));
        setContentPane(desktopPane);

        frames.add(createLocationWindow(400, 400));
        frames.add(createLogWindow());
        frames.add(createGameWindow(controller, 400, 400));
        addListWindows(desktopPane, frames);
        serializer.loadWindows(frames);

        MenuBarGenerator barGenerator = new MenuBarGenerator(this);
        setJMenuBar(barGenerator.generateMenuBar());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setWindowListener(desktopPane, serializer);

        addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyChar() + " key pressed");
            }
        });
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

