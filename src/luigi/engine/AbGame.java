package luigi.engine;

import java.awt.Graphics;
import javax.swing.*;

public abstract class AbGame {

    protected Input input;

    protected GameThread gameThread;

    public AbGame(){
        init();
        setup();
    }

    public abstract void setup();
    public abstract void update();
    public abstract void render(Graphics g);

    public void setName(String newName){
        gameThread.setName(newName);
    }

    public void init(){
        if(gameThread == null){
            gameThread = new GameThread(this,720,480, new JFrame(""));
            input = gameThread.getInput();
        }
    }

    public void start(){
        gameThread.start();
    }

}