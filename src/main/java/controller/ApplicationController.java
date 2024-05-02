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
    private final RobotEntity robotEntity = new RobotEntity(targetEntity);
    private final GameVisualizer gameVisualizer = new GameVisualizer(robotEntity, targetEntity);
    private boolean isGamePaused = true;
    Timer timer = new Timer(10, event -> {
        robotEntity.moveRobot(10);
        if(targetReached()){
            targetEntity.randomPosition(gameVisualizer.getWindowSize());
        }
    });
    private boolean targetReached(){
        return (targetEntity.getEntityCoordinates().distance(robotEntity.getEntityCoordinates()) < 10.0);
    }
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
                    robotEntity.directByKey(commandCode);
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
