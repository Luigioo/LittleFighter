package luigi.littleFighter;

import java.awt.event.KeyEvent;

public class StateIdle extends StateA{

    public static final int id = 0;
    
    

    private final float friction = .98f;

    public StateIdle(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        index0 = 0;
        index1 = 4;
        length = 15;
    }

    @Override
    public void update(){
        super.update();
        //update robot state
        if(input.keyPressed(KeyEvent.VK_K)){
            robot.changeState(StateDash.id);
            return;
        }
        if(input.keyPressed(KeyEvent.VK_J)){
            robot.changeState(StatePunch.id);
            return;
        }
        
        if(!(xdir==0&&ydir==0)){
            robot.changeState(StateWalk.id);
            return;
        }
        
        friction(friction);
        updatePos();
        
    }

    public String fileName(){
        return "idle";
    }

}