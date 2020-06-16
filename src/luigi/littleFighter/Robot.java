package luigi.littleFighter;

import luigi.engine.Input;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;



public class Robot extends Sprite{

    private Input input;

    private float[] pos;
    private float[] vel;
    private float[] dir;//to be implemented
    protected boolean isMirror = false;

    
    private StateA[] states;
    private StateA state;
    private int stateToChange;//change to todos
    
    public Robot(Input input) {
        //super("res\\bandit.png", "res\\bandit_0_mirror.png");
        super("res/bandit");
        // new Sprite("bandit");
        this.input = input;
        this.pos = new float[2];
        this.vel = new float[2];
        this.dir = new float[2];
        this.stateToChange = -1;
        StateA[] tempS = {
            new StateIdle(this),
            new StateWalk(this),
            null,
            new StatePunch(this),
            new StateDash(this)
        };
        states = tempS;
        state = states[StateIdle.id];
        state.onEntry();
    }
    
    
    public void update(){

        //System.out.println(pos[0]+" "+pos[1]);

        //update state
        if(stateToChange!=-1){
            state = states[stateToChange];
            state.onEntry();
            stateToChange = -1;
        }

        //update everything else
        state.update();
        
    }

    public void render(Graphics g){
        state.render(g);
    }
    
    public void changeState(int stateCode){
        stateToChange = stateCode;
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


}