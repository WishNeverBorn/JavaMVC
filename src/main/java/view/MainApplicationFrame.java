package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;

import view.visualizers.GameVisualizer;
import static view.windows.WindowManager.*;

/**
 * Класс для создания главного окна приложения
 * Создает главное окно, окна логировагия и игры, а также меню
 */
public class MainApplicationFrame extends ClosableFrame
{
    private final JDesktopPane desktopPane = new JDesktopPane();

    public MainApplicationFrame(GameVisualizer someVisualizer) {

        setScreenAndBounds(returnInset(50));

        setContentPane(desktopPane);
        addWindow(desktopPane, createLogWindow());
        addWindow(desktopPane, createGameWindow(someVisualizer, 400, 400));
        addWindow(desktopPane, createLocationWindow(400, 200));

        MenuBarGenerator barGenerator = new MenuBarGenerator(this);
        setJMenuBar(barGenerator.generateMenuBar());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setWindowListener(desktopPane);

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

