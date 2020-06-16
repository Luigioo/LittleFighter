package luigi.littleFighter;

import luigi.engine.Input;

import java.awt.event.KeyEvent;


public class Robot extends Sprite{

    private Input input;
    private float[] vel;

    
    private StateA[] states;
    private StateA state;
    private int stateToChange;
    
    public Robot(Input input) {
        super("res\\bandit.png", "res\\bandit_0_mirror.png");
        new Sprite("bandit");
        this.input = input;
        this.vel = new float[2];
        this.stateToChange = -1;
        StateA[] tempS = {
            new StateIdle(this),
            new StateWalk(this),
            new StateJump(this),
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

        //update sprite tile index for rendering
        super.index = state.getSpriteIndex();

        //update everything else
        state.update();
        
    }
    
    public void changeState(int stateCode){
        stateToChange = stateCode;
    }
    
    public Input getInput(){
        return input;
    }
    public float[] getVel(){
        return vel;
    }



}