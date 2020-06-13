package luigi.engine;

import java.awt.Graphics;

public abstract class AbGame {

    protected Input input;

    private GameThread gameThread;

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

    private void init(){
        if(gameThread == null){
            gameThread = new GameThread(this,720,480);
            input = gameThread.getInput();
        }
    }

    public void start(){
        System.out.println("start game");
        gameThread.start();
    }

}