package model;

import view.visualizers.GameVisualizer;

/**
 * Интерфейс для описания наблюдаемого объекта
 */
public interface Observable {
    void addObserver(GameVisualizer observer);
    void removeObserver(GameVisualizer observer);
    void notifyObservers();
}
