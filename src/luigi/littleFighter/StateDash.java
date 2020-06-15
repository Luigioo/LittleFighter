package luigi.littleFighter;

//import java.awt.event.KeyEvent;

public class StateDash extends StateA{

    
    public static final int id = 4;

    private final float DASHSPEED = 10;
    
    private float dashx;
    private float dashy;
    

    public StateDash(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        index0 = 67;
        index1 = 70;
        length = 12;
    }


    @Override
    public void update(){
        super.update();

        //update robot state
        if(exitFlag){
            robot.changeState(StateIdle.id);
        }

        //update positions
        float hyp = hypotenuse(dashx, dashy);
        dashx /= hyp;
        dashy /= hyp;
            
        robot.setXPos(robot.getXPos()+xdir*DASHSPEED);
        robot.setYPos(robot.getYPos()+ydir*DASHSPEED);


    }

    @Override
    public void onEntry(){
        super.onEntry();

        //determine dash direction
        if(xdir>.1||xdir<-.1||ydir>.1||ydir<-.1){
            dashx = xdir;
            dashy = ydir;
        }else{
            dashy = 0;
            if(robot.isMirror){
                dashx = -1;
            }else{
                dashx = 1;
            }
        }
    }

    private float hypotenuse(float x, float y){
        return (float)Math.sqrt((x*x+y*y));
    }
}