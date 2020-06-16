package luigi.littleFighter;

import java.awt.event.KeyEvent;

public class StateWalk extends StateA{

    public static final int id = 1;

    private final float WALKSPEED = 2;
    private final float MAXACCEL = 0.2f;

    public StateWalk(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        index0 = 4;
        index1 = 8;
        length = 18;
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
        if(xdir==0){
            if(ydir==0){
                robot.changeState(StateIdle.id);
                return;
            }
        }
        

        //face left/right
        if(xdir>.1){
            robot.faceRight();
        }else if(xdir<-.1){
            robot.faceLeft();
        }

        //update positions
        steer(xdir,ydir,WALKSPEED,MAXACCEL);
        updatePos();
    }


}