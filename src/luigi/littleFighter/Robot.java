package luigi.littleFighter;

import luigi.engine.Input;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;



public class Robot extends Sprite{

    private Game game;

    private Input input;

    private float[] pos;
    private float[] vel;
    private float[] dir;
    protected boolean isMirror = false;

    
    private StateA[] states;
    private StateA state;
    private ArrayList<StateA> stateHistory = new ArrayList<StateA>();

    private int frameCount = 0;
    
    public Robot(Game g) {
        //super("res\\bandit.png", "res\\bandit_0_mirror.png");
        super("res/bandit");
        // new Sprite("bandit");
        this.game = g;
        this.input = g.getInput();
        this.pos = new float[2];
        pos[1]=160;
        this.vel = new float[2];
        this.dir = new float[2];    
        StateA[] tempS = {
            new StateIdle(this),
            new StateWalk(this),
            null,
            new StatePunch(this),
            new StateDash(this),
            new StateRun(this)
        };
        states = tempS;
        state = states[StateIdle.id];
        state.onEntry();
        stateHistory.add(state);//idle's previous state is idle??
    }
    
    
    public void update(){
        
        frameCount++;
        //console log
        // if(frameCount%20==0){
        //     System.out.println(pos[0]+" "+pos[1]);
        //     System.out.println(vel[0]+" "+vel[1]);
        // }
        


        //update everything else
        state.update();
        
    }

    public void render(Graphics g){
        state.render(g);
    }
    
    public void changeState(int stateCode){
        game.addTask(new Runnable(){
            public void run(){
                stateHistory.add(state);
                state = states[stateCode];
                state.onEntry();
            }
        });
    }

    public void cutHistory(){
        if(stateHistory.size()>100000){
            stateHistory.subList(0,99900).clear();
            System.out.println("stateHistory.size() out of boundry");
        }
    }
    
    public void faceRight(){
        isMirror = false;
    }
    public void faceLeft(){
        isMirror = true;
    }
    
    public Input getInput(){
        return input;
    }

    public float[] getPos(){
        return pos;
    }
    public float[] getVel(){
        return vel;
    }
    public float[] getDir(){
        return dir;
    }

    public ArrayList<StateA> getHistory(){
        return stateHistory;
    }

}