package view.windowSerializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * Страшный класс WindowSerializer для сериализации объектов окон
 * Используем для сохранения состояния окон, при запуске считываем данные из файлика и по ним
 *  восстанавливаем положение окон
 * При закрытии программы выгружаем положение окон в тот же файл
 * TODO:
 *  Возможно надо доработать перехват исключений, сейчас мне физически больно на него смотреть
 */
public class WindowSerializer {
    private final String PATH = new File("src/main/resources/windowPositions.ser").getAbsolutePath();
    private List<JInternalFrame> windows = new ArrayList<>();
    public WindowSerializer(){}
    public void loadWindows(ArrayList<JInternalFrame> JWindows){
        windows.addAll(JWindows);
    }
    public void saveWindowPositions() {
        try
        {
            FileOutputStream fileOut = new FileOutputStream(PATH);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            for (JInternalFrame window : windows)
            {
                out.writeObject(window.getLocation());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadWindowPositions() {
        try
        {
            FileInputStream fileIn = new FileInputStream(PATH);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            for (JInternalFrame window : windows) {
                window.setLocation((java.awt.Point) in.readObject());
            }
        }
        catch (IOException ioException)
        {
            System.out.println("windowsPositions.ser not found, windows positions restored to default");
        }
        catch (ClassNotFoundException classException)
        {
            classException.printStackTrace();
        }
    }
}
