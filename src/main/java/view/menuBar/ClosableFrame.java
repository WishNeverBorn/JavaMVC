package view.menuBar;

import view.windowSerializer.WindowSerializer;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Класс закрываемого окна
 * Содержит реализацию методов для безопасного закрытия окна
 */
public class ClosableFrame extends JFrame {
    WindowSerializer in_serializer;

    protected void setWindowListener(JDesktopPane desktopPane, WindowSerializer serializer)
    {
        in_serializer = serializer;
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                closeWithConfirm(desktopPane);
            }
        });
    }

    /**
     * Метод вызывает диалоговое окно, подтвержающее закрытие приложения
     * При нажатии на "Да" закрывает все окна
     * @param desktopPane - главное окно, поверх которого выведется уведомление
     */
    protected void closeWithConfirm(JDesktopPane desktopPane)
    {
        int option = JOptionPane.showConfirmDialog(desktopPane,
                "Вы уверены, что хотите закрыть приложение?",
                "Подтверждение",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if(option == 0) {
            in_serializer.saveWindowPositions();
            setVisible(false);
            dispose();
            //System.exit(0);
        }
    }
}
