package controller;

import model.ApplicationModel;
import view.ApplicationView;

/**
 * Класс контроллер, осуществляет взаимодействие пользователя с программой
 * Занимается валидацией данных полученных от пользователся и их передачей в модель
 */
public class ApplicationController {
    private ApplicationModel model;
    private ApplicationView view;

    public ApplicationController(){
        this.model = new ApplicationModel();
        this.view = new ApplicationView();
    }
}
