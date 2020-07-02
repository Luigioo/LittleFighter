package luigi.littleFighter.npc;

// import luigi.littleFighter.Game;
import luigi.littleFighter.player.Robot;
import luigi.littleFighter.collide.*;

import java.awt.image.*;
import java.awt.Graphics;

public abstract class State {

    protected Enemy master;
    protected Robot hero;
    protected float[] heroPos;
    protected float[] pos;
    protected float[] relPos;
    protected float[] vel;
    protected Hurtbox hb;
    protected int[] health;


    protected BufferedImage[] sprites;
    protected int gapFrames = 10;
    protected int frameCount = 0;
    protected int spriteIndex = 0;
    protected boolean shouldExit = false;

    public State(Enemy master){
        this.master = master;
        hero=master.getRobot();
        heroPos=master.getHeroPos();
        pos=master.getPos();
        relPos=master.getRelPos();
        vel=master.getVel();
        hb=master;
        health = master.getHealth();
    }

    public void update(){}
    
    public void render(Graphics g){
        BufferedImage image = sprites[spriteIndex];
        if(isMirror()){
            g.drawImage(image, (int)pos[0]+image.getWidth(), (int)pos[1], -image.getWidth(), image.getHeight(), null);
        }else{
            g.drawImage(image, (int)pos[0],(int)pos[1],image.getWidth(),image.getHeight(),null);
        }
        
    }

    public void updateSprite(){
        frameCount++;
        if(frameCount>=gapFrames){
            frameCount = 0;
            spriteIndex++;
            if(spriteIndex==sprites.length){shouldExit = true;}
            spriteIndex%=sprites.length;
        }
    }
    public void updateHeroPos(){
        heroPos = hero.getPos().clone();
    }
    public void updateRelativePos(){
        updateHeroPos();
        relPos[0]=heroPos[0]-pos[0];
        relPos[1]=heroPos[1]-pos[1];
    }
    public void updatePos(){
        pos[0]+=vel[0];
        pos[1]+=vel[1];
    }
    public void updateFace(){
        if(relPos[0]>0){
            faceRight();
        }else{
            faceLeft();
        }
    }
    
    public void onEntry(){
        frameCount = gapFrames;
        spriteIndex = 0;
        shouldExit = false;
    }

    public void onCollide(Collidable c){
        if(c instanceof Hitbox){
            Hitbox hi = (Hitbox)c;
            takeDamage(hi.getDamage());
        }
        System.out.println("enemyhealth: "+health[0]);
    }

    public void takeDamage(int d){
        health[0] = Math.max(health[0]-d,0);
    }

//#region wrappers
    public boolean isMirror(){return master.isMirror();}
    public void faceRight(){master.faceRight();}
    public void faceLeft(){master.faceLeft();}

    public BufferedImage[] loadSpriteID(String name){
        return master.loadSpriteID(name);
    }
    public void changeState(int stateID){
        master.changeState(stateID);
    }
//#endregion
    
//#region math&physics

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