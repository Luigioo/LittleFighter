package luigi.littleFighter;

public class StatePunch extends StateA{

    public static final int id = 3;


    public StatePunch(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        index0 = 10;
        index1 = 12;
        length = 8;
    }

    @Override
    public void update(){
        super.update();

        if(exitFlag){
            robot.changeState(StateIdle.id);
            return;
        }
    }
    
}