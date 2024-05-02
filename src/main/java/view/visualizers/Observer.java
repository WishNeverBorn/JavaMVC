package view.visualizers;

import model.Entity;

/**
 * Интерфейс наблюдателя, следит за изменениями наблюдаемого объекта и реагирует на них
 */
public interface Observer {
    void update(Entity typeEntity);
}
