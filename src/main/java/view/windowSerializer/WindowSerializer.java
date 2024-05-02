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
    private String path = new File("src/main/resources/windowPositions.ser").getAbsolutePath();
    private List<JInternalFrame> windows = new ArrayList<>();
    public WindowSerializer(){}
    public void loadWindows(ArrayList<JInternalFrame> JWindows){
        windows.addAll(JWindows);
    }
    public void saveWindowPositions() {
        try(FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

            for (JInternalFrame window : windows) {
                out.writeObject(window.getLocation());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadWindowPositions() {
        try(FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn)) {

            for (JInternalFrame window : windows) {
                window.setLocation((java.awt.Point) in.readObject());
            }
        }
        catch (IOException | ClassNotFoundException e) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(path);
                fileOutputStream.close();
            }
            catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
