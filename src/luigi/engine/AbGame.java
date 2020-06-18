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
    
    public void init(){
        if(gameThread == null){
            gameThread = new GameThread(this,720,480, new JFrame(""));
            input = gameThread.getInput();
        }
    }

    public abstract void setup();
    public abstract void update();
    public abstract void render(Graphics g);
    
        public void start(){
            gameThread.start();
        }

    public void setName(String newName){
        gameThread.setName(newName);
    }

    public Input getInput(){
        return input;
    }
}