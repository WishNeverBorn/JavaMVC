package controller;

import model.RobotEntity;
import model.TargetEntity;
import view.visualizers.GameVisualizer;
import javax.swing.*;;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Класс контроллер, осуществляет взаимодействие пользователя с программой
 * В данный момент подключает визуализатор для отрисовки графики
 */
public class ApplicationController extends JPanel {
    private final TargetEntity targetEntity = new TargetEntity();
    private final RobotEntity robotEntity = new RobotEntity();
    private final GameVisualizer gameVisualizer = new GameVisualizer(robotEntity, targetEntity);
    private boolean isGamePaused = true;
    private final Timer timer = new Timer(10, event -> {
        robotEntity.moveRobot(10);
        if(targetReached()){
            targetEntity.randomPosition();
        }
    });
    public ApplicationController(){
        robotEntity.addObserver(gameVisualizer);
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e) {
                processCode(e.getKeyCode());
            }
        });
    }
    private boolean targetReached(){
        return (targetEntity.getEntityCoordinates().distance(robotEntity.getEntityCoordinates()) < 10.0);
    }
    private double directByKey(int keyCode){
        double direction = 0.0;
        switch(keyCode){
            case KeyEvent.VK_W -> {
                direction = -Math.PI / 2;
            }
            case KeyEvent.VK_A -> {
                direction = Math.PI;
            }
            case KeyEvent.VK_S -> {
                direction = Math.PI / 2;
            }
            case KeyEvent.VK_D -> {
                direction = 0;
            }
        }
        return direction;
    }
    private void processCode(int commandCode){
        switch(commandCode){
            case KeyEvent.VK_ENTER -> {
                if(isGamePaused){
                    timer.start();
                    gameVisualizer.hidePauseWindow();
                    isGamePaused = false;
                }
                else{
                    timer.stop();
                    gameVisualizer.showPauseWindow();
                    isGamePaused = true;
                }
            }
            default -> {
                if(!isGamePaused) {
                    double newDirection = directByKey(commandCode);
                    robotEntity.setDirection(newDirection);
                }
            }
        }
    }
    public RobotEntity getRobotEntity(){
        return robotEntity;
    }
    public GameVisualizer getGameVisualizer(){
        return gameVisualizer;
    }
}
