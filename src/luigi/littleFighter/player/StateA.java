package luigi.littleFighter.player;

import luigi.engine.Input;
import luigi.littleFighter.*;
import luigi.littleFighter.collide.*;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.*;

public abstract class StateA {

    protected int gapFrames = 10;
    protected int frameCount = 0;
    protected int spriteIndex = 0;
    protected int[] durations;

    protected Game game;
    protected Robot robot;
    protected Input input;
    protected float[] pos;
    protected float[] vel;
    protected float[] dir;
    protected ArrayList<StateA> history;
    
    protected BufferedImage[] sprites;
    protected boolean shouldExit = false;


    public StateA(Robot robot){
        this.game = robot.getGame();
        this.robot = robot;
        this.input = robot.getInput();
        this.pos = robot.getPos();
        this.vel = robot.getVel();
        this.dir = robot.getDir();
        this.history = robot.getHistory();
        init();
    }

    public abstract void init();

    public BufferedImage[] loadSpriteID(String targetName){return robot.loadSpriteID(targetName);}
    public BufferedImage getSpritebyName(String name){return robot.getSpritebyName(name);}
    //update methods
    public abstract void update();

    public void updateSprite(){
        frameCount++;
        if(frameCount>=gapFrames){
            frameCount = 0;
            spriteIndex++;
            if(spriteIndex == sprites.length){shouldExit=true;}
            spriteIndex%=sprites.length;
        }
    }
    public void upSpriteDuration(){
        frameCount++;
        if(frameCount>=durations[spriteIndex]){
            frameCount = 0;
            spriteIndex++;
            if(spriteIndex == sprites.length){shouldExit=true;}
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
    //

    public void render(Graphics g){
        BufferedImage image = sprites[spriteIndex];
        if(robot.isMirror){
            g.drawImage(image, (int)pos[0]+image.getWidth(), (int)pos[1], -image.getWidth(), image.getHeight(), null);
        }else{
            g.drawImage(image, (int)pos[0],(int)pos[1],image.getWidth(),image.getHeight(),null);
        }
    }

    /**
     * contains code, so do super();
     */
    public void onEntry(){
        frameCount = 0;
        spriteIndex=0;
        shouldExit = false;
    }
    public void onExit(){

    }
    public abstract int id();

    public Hitbox createHitBox(int offx,int offy,int colWidth,int colHeight){
        Hitbox r = new Hitbox(){
            public int[] getCords() {
                int[] r = {(int)pos[0]+offx,
                    (int)pos[1]+offy,
                    colWidth,
                    colHeight};
                return r;
            }
        };
        game.addHitBox(r);
        return r;
    }
    public void removeHitBox(Hitbox hb){
        game.removeHitBox(hb);
    }

//#region maths &physics
    //2d physics methods
    public void accel(float x, float y){
        vel[0] += x;
        vel[1] += y;
    }
    public void steer(float xdir, float ydir, float maxSpeed, float maxAccel){
        float steer[] = {xdir,ydir};
        //get desired velocities
        normal(steer);
        mult(steer,maxSpeed);
        //get steer vel
        float dvx = steer[0]-vel[0];
        float dvy = steer[1]-vel[1];
        //apply accel
        if(hypotenuse(dvx, dvy)<maxAccel){
            //if the differences in vel are small enough
            //then just set to the desired speed
            vel[0]=steer[0];
            vel[1]=steer[1];
        }else{
            steer[0]-=vel[0];
            steer[1]-=vel[1];
            normal(steer);
            mult(steer,maxAccel);
            accel(steer[0],steer[1]);
        }
    }
    public void friction(float percentageRemain){
        if(isO(vel[0])){
            vel[0] = 0f;
        }else{
            vel[0]*=percentageRemain;
        }
        if(isO(vel[1])){
            vel[1] = 0f;
        }else{
            vel[1]*=percentageRemain;
        }
    }

    //math methods
    public float map(float num, float numMin, float numMax, float targetMin, float targetMax){
        float ratio = (targetMax-targetMin)/(numMax - numMin);
        return (num-numMin)*ratio+targetMin;
    }
    public void mult(float[] v,float mul){
        v[0]*=mul;
        v[1]*=mul;
    }
    public float hypotenuse(float x, float y){
        return (float)Math.sqrt((x*x+y*y));
    }
    public void normal(float[] v){
        float hyp = hypotenuse(v[0],v[1]);
        if(hyp != 0){
            v[0]/=hyp;
            v[1]/=hyp;
        }
    }
    public boolean isO(float n){
        return n<.1&&n>-.1;
    }
//#endregion

}