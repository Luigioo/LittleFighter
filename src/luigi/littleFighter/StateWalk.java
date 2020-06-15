package luigi.littleFighter;

import java.awt.event.KeyEvent;

public class StateWalk extends StateA{

    public static final int id = 1;

    private final float WALKSPEED = 2;

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
        if(xdir==0){
            if(ydir==0){
                robot.changeState(StateIdle.id);
                return;
            }
        }
        
        if(input.keyPressed(KeyEvent.VK_J)){
            robot.changeState(StatePunch.id);
            return;
        }

        //update positions
        if(xdir>.1||xdir<-.1||ydir>.1||ydir<-.1){
            float hyp = hypotenuse(xdir, ydir);
            xdir /= hyp;
            ydir /= hyp;
            
            robot.setXPos(robot.getXPos()+xdir*WALKSPEED);
            robot.setYPos(robot.getYPos()+ydir*WALKSPEED);
        }
    }

    private float hypotenuse(float x, float y){
        return (float)Math.sqrt((x*x+y*y));
    }

}