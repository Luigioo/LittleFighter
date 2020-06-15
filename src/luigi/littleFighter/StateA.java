package luigi.littleFighter;

import luigi.engine.Input;

import java.awt.event.KeyEvent;

public abstract class StateA {
    
    protected int index0;
    protected int index1;
    protected int length;

    protected boolean playBack;
    protected int frameCount;
    protected boolean exitFlag;

    protected Robot robot;
    protected Input input;

    protected float xpos,ypos;
    protected float xdir,ydir;


    public StateA(Robot robot){
        this.robot = robot;
        this.input = robot.getInput();
        init();
    }

    public abstract void init();


    public void update(){

        //count frames 
        frameCount++;
        if(frameCount>=length){
            if(playBack){
                exitFlag = true;
            }
            frameCount = 0;
            playBack = !playBack;
        }
        //update directions
        xdir = 0;
        ydir = 0;
        if(input.getKey(KeyEvent.VK_D)){
            xdir++;
        }
        if(input.getKey(KeyEvent.VK_A)){
            xdir--;
        }
        if(input.getKey(KeyEvent.VK_S)){
            ydir++;
        }
        if(input.getKey(KeyEvent.VK_W)){
            ydir--;
        }
    }

    public int getSpriteIndex(){
        if(playBack){
            return (int)map(length-frameCount-1, 0, length, index0+1, index1);
        }else{
            return (int)map(frameCount, 0, length, index0, index1-1);

        }
    }

    public void onEntry(){
        playBack = false;
        frameCount = 0;
        exitFlag = false;
    }

    private float map(float num, float numMin, float numMax, float targetMin, float targetMax){
        float ratio = (targetMax-targetMin)/(numMax - numMin);
        return (num-numMin)*ratio+targetMin;
    }


}