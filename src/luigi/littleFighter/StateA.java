package luigi.littleFighter;

import luigi.engine.Input;

import java.awt.event.KeyEvent;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

public abstract class StateA {

    protected int index0;
    protected int index1;
    protected int length;

    protected boolean playBack;
    protected int frameCount;
    protected boolean exitFlag;

    protected Robot robot;
    protected Input input;
    protected float[] pos;
    protected float[] vel;

    protected float xdir,ydir;


    public StateA(Robot robot){
        this.robot = robot;
        this.input = robot.getInput();
        this.pos = robot.getPos();
        this.vel = robot.getVel();
        init();
    }

    public abstract void init();

    public String fileName(){
        return null;
    }

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

        updateDir();
    }

    public int getSpriteIndex(){
        if(playBack){
            return (int)map(length-frameCount-1, 0, length, index0+1, index1);
        }else{
            return (int)map(frameCount, 0, length, index0, index1-1);

        }
    }

    public void onEntry(){
        frameCount = 0;
        playBack = false;
        exitFlag = false;
    }


    public void updateDir(){
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

    public float map(float num, float numMin, float numMax, float targetMin, float targetMax){
        float ratio = (targetMax-targetMin)/(numMax - numMin);
        return (num-numMin)*ratio+targetMin;
    }

    public float hypotenuse(float x, float y){
        return (float)Math.sqrt((x*x+y*y));
    }

    public void updatePos(){
        pos[0]+=vel[0];
        pos[1]+=vel[1];
    }

    public void accel(float x, float y){
        vel[0] += x;
        vel[1] += y;
    }

    public void steer(float xdir, float ydir, float maxSpeed, float maxAccel){
        float xSteer = xdir;
        float ySteer = ydir;
        if(!isO(xSteer)){
            if(!isO(ySteer)){
                //get desired velocities
                float hyp = hypotenuse(xSteer, ySteer);
                xSteer = xSteer/hyp*maxSpeed;
                ySteer = ySteer/hyp*maxSpeed;
                //get steer vel
                xSteer-=vel[0];
                ySteer-=vel[1];
                //apply accel
                float steerAccel = hypotenuse(xSteer, ySteer);
                if(maxAccel<steerAccel){
                    xSteer = xSteer/steerAccel*maxAccel;
                    ySteer = ySteer/steerAccel*maxAccel;
                    accel(xSteer, ySteer);
                }
            }else{
                    xSteer*=maxSpeed;
                    xSteer-=vel[0];
                    xSteer/=Math.abs(xSteer);
                    accel(xSteer*maxAccel,0);
            }
        }else if(!isO(ySteer)){
            if(maxAccel<Math.abs(maxSpeed-Math.abs(vel[1]))){
                accel(0,ySteer*maxAccel);
            }
        }
    }

    public void friction(float percentageRemain){
        if(isO(vel[0])){
            vel[0] = 0;
        }
        if(isO(vel[1])){
            vel[1] = 0;
        }
        vel[0]*=percentageRemain;
        vel[1]*=percentageRemain;

    }

    public boolean isO(float n){
        return n<.1&&n>-.1;
    }
}