package luigi.littleFighter.npc;

import luigi.littleFighter.*;
import luigi.littleFighter.collide.*;
import luigi.littleFighter.player.Robot;

import java.awt.Color;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Enemy extends Sprite implements Layered, Hurtbox{

    private Game game;
    private Robot hero;
    private float[] heroPos;
    private float[] pos = {400f,160f};
    private float[] relPos = {100f,100f};
    private float[] vel = {0f,0f};
    private int[] health = {4};

    private boolean isMirror = true;

    private State[] states;
    private State state;

    private int colWidth = 40;
    private int colHeight = 60;

    // private int life = 3;

    public Enemy(Game g) {
        super("res/bandit");
        this.game = g;
        this.hero = game.getHero();
        heroPos = hero.getPos();
        State[] temps = {
            new Idle(this),
            new Walk(this),
            new Hurt(this)
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
        gizmos(g);
        // g.setColor(new Color(0f,.29f,.6f,.5f));
        // g.fillRect((int)pos[0]+(80-colWidth)/2,(int)pos[1]+(80-colHeight)/2,colWidth,colHeight);
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
    public int[] getHealth(){return health;}

    public boolean isMirror(){return isMirror;}
    public void faceRight(){isMirror=false;}
    public void faceLeft(){isMirror=true;}

    @Override
    public void onCollide(Collidable c) {
        state.onCollide(c);
    }

    @Override
    public int[] getCords() {
        int[] r = {(int)pos[0]+(80-colWidth)/2,
        (int)pos[1]+(80-colHeight)/2,
        colWidth,
        colHeight};
        return r;
    }

    
}