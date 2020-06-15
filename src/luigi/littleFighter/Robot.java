package luigi.littleFighter;

import luigi.engine.Input;

import java.awt.event.KeyEvent;


public class Robot extends Sprite{

    private Input input;

    private StateA[] states;

    private StateA state;

    
    public Robot(Input input) {
        super("res\\bandit.png", "res\\bandit_0_mirror.png");
        this.input = input;
        StateA[] tempS = {
            new StateIdle(this),
            new StateWalk(this),
            new StateJump(this),
            new StatePunch(this),
            new StateDash(this)
        };
        states = tempS;
        changeState(StateIdle.id);
    }
    
    
    public void update(){

        //update sprite tile index for rendering
        super.index = state.getSpriteIndex();

        //update everything else
        state.update();
        
    }
    
    public void changeState(int stateCode){
        state = states[stateCode];
        state.onEntry();
    }
    
    public StateA getState(){
        return state;
    }

    public Input getInput(){
        return input;
    }

}