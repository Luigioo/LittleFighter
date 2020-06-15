package luigi.littleFighter;

import java.awt.event.KeyEvent;

public class StateIdle extends StateA{

    public static final int id = 0;


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
        
        if(input.keyPressed(KeyEvent.VK_J)){
            robot.changeState(StatePunch.id);
            return;
        }

        if(!(xdir==0&&ydir==0)){
            robot.changeState(StateWalk.id);
            return;
        }

        
    }

}