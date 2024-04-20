package controller;
import model.RobotEntity;
import view.visualizers.GameVisualizer;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Класс контроллер, осуществляет взаимодействие пользователя с программой
 * В данный момент подключает визуализатор для отрисовки графики
 */
public class ApplicationController extends JPanel {
    private final RobotEntity robotEntity = new RobotEntity();
    private final GameVisualizer gameVisualizer = new GameVisualizer(robotEntity);
    private boolean isGameStarted = false;
    public ApplicationController(){
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
                if(!isGameStarted){
                    Timer timer = new Timer(10, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            robotEntity.moveRobot(10);
                            gameVisualizer.onModelUpdateEvent();
                        }
                    });
                    timer.start();
                    isGameStarted = true;
                }
            }
            default -> {
                if(isGameStarted) {
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
