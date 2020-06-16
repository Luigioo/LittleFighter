package luigi.littleFighter;

import luigi.engine.Input;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.*;

public abstract class StateA {

    protected int gapFrames = 10;
    protected int frameCount = gapFrames;
    protected int spriteIndex = 0;

    protected Robot robot;
    protected Input input;
    protected float[] pos;
    protected float[] vel;
    protected float[] dir;
    
    protected BufferedImage[] sprites;

    public StateA(Robot robot){
        this.robot = robot;
        this.input = robot.getInput();
        this.pos = robot.getPos();
        this.vel = robot.getVel();
        this.dir = robot.getDir();
        System.out.println("");
        init();
    }

    public abstract void init();

    public BufferedImage[] loadSpriteID(String targetName){
        BufferedImage[] sps = robot.getSprites();
        String[] names = robot.getSpriteNames();
        List<BufferedImage> targets = new ArrayList<BufferedImage>();
        for(int i=0;i<names.length;i++){
            if(names[i].contains(targetName)){
                targets.add(sps[i]);
            }
        }
        return targets.toArray(new BufferedImage[0]);
    }

    public String fileName(){
        return null;
    }

    //update methods
    public abstract void update();

    public void updateSprite(){
        frameCount--;
        if(frameCount<=0){
            frameCount = gapFrames;
            spriteIndex++;
            spriteIndex%=sprites.length;
        }
    }
    public void updateDir(){
        //update directions
        dir[0] = 0;
        dir[1] = 0;
        if(input.getKey(KeyEvent.VK_D)){
            dir[0]++;
        }
        if(input.getKey(KeyEvent.VK_A)){
            dir[0]--;
        }
        if(input.getKey(KeyEvent.VK_S)){
            dir[1]++;
        }
        if(input.getKey(KeyEvent.VK_W)){
            dir[1]--;
        }
    }
    public void updatePos(){
        pos[0]+=vel[0];
        pos[1]+=vel[1];
    }

    public abstract void render(Graphics g);

    public void onEntry(){
        frameCount = gapFrames;
        spriteIndex=0;
    }

    //2d physics methods
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

    //math methods
    public float map(float num, float numMin, float numMax, float targetMin, float targetMax){
        float ratio = (targetMax-targetMin)/(numMax - numMin);
        return (num-numMin)*ratio+targetMin;
    }
    public float hypotenuse(float x, float y){
        return (float)Math.sqrt((x*x+y*y));
    }
    public boolean isO(float n){
        return n<.1&&n>-.1;
    }
}