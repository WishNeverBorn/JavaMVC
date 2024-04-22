package view.visualizers;
import javax.swing.*;

/**
 * Класс визуализатора для окна местоположения робота
 * В данный момент пустая заготовка
 * TODO:
 * 1.Сделать подписку на модель робота
 * 2.Привязать к LocationWindow для отображения состояния модели в случае ее обновления
 */
public class LocationVisualizer extends JPanel implements Observer{
    public LocationVisualizer(){

    }
    @Override
    public void update() {
        updatePosition();
    }

    private void updatePosition(){

    }
}
