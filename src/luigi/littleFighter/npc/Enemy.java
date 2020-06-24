package luigi.littleFighter.npc;

import luigi.littleFighter.*;

import java.awt.image.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Enemy extends Sprite implements Layered{

    private Game game;
    private Robot hero;
    private float[] heroPos;
    private float[] pos = {400f,60f};
    private float[] relPos = {100f,100f};
    private float[] vel = {0f,0f};

    private boolean isMirror = true;

    private State[] states;
    private State state;

    // private int life = 3;

    public Enemy(Game g) {
        super("res/bandit");
        this.game = g;
        this.hero = game.getHero();
        heroPos = hero.getPos();
        State[] temps = {
            new Idle(this),
            new Walk(this)
        };
        states = temps;
        state = states[Idle.ID];
        state.onEntry();
    }

    public void update(){
        state.update();
    }

    @Override
    public void render(Graphics g){
        state.render(g);
    }

    @Override
    public float getY() {
        return pos[1];
    }

    public void changeState(int stateCode){
        game.addTask(new Runnable(){
            public void run(){
                state = states[stateCode];
                state.onEntry();
            }
        });
    }

    public Robot getRobot(){return hero;}
    public float[] getHeroPos(){return heroPos;}
    public float[] getPos(){return pos;}
    public float[] getRelPos(){return relPos;}
    public float[] getVel(){return vel;}

    public boolean isMirror(){return isMirror;}
    public void faceRight(){isMirror=false;}
    public void faceLeft(){isMirror=true;}

    
}