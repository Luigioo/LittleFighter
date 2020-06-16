package luigi.littleFighter;

//import java.awt.event.KeyEvent;

public class StateDash extends StateA{

    
    public static final int id = 4;

    private final float DASHSPEED = 4f;
    private final float MAXACCEL = 0.4f;
    
    private float dashx;
    private float dashy;
    

    public StateDash(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        index0 = 67;
        index1 = 70;
        length = 20;
    }


    @Override
    public void update(){
        //count frames 
        frameCount++;
        if(frameCount>=length){
            robot.changeState(StateIdle.id);
            return;
        }

        //update robot state
        if(exitFlag){
            robot.changeState(StateIdle.id);
            return;
        }

        //update positions
        steer(dashx,dashy,DASHSPEED,MAXACCEL);
        updatePos();
    }

    public int getSpriteIndex(){
        return (int)map(frameCount, 0, length, index0, index1);
    }

    @Override
    public void onEntry(){
        super.onEntry();

        //determine dash direction
        updateDir();
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

}