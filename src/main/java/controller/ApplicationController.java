package controller;
import view.visualizers.GameVisualizer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Класс контроллер, осуществляет взаимодействие пользователя с программой
 * Занимается валидацией данных полученных от пользователся и их передачей в модель
 */
public class ApplicationController {
    public ApplicationController(GameVisualizer view){
        Timer someTimer = new Timer("event generator", true);
        someTimer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                view.onModelUpdateEvent();
            }
        }, 0, 10);
    }
}
