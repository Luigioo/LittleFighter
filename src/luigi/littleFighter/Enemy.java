package luigi.littleFighter;

import java.awt.image.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Enemy extends Sprite{

    private Game game;
    private Robot hero;
    private float[] heroPos;
    private float[] pos = {400f,60f};
    private float[] relPos = {100f,100f};
    private float[] vel = {0f,0f};
    private boolean isMirror = true;

    private State[] states;
    private State state;
    private static final int idleID=0;
    private static final int walkID=1;

    private int life = 3;

    public Enemy(Game g) {
        super("res/bandit");
        this.game = g;
        this.hero = game.getHero();
        heroPos = hero.getPos();
        State[] temps = {
            new Idle(),
            new Walk()
        };
        states = temps;
        state = states[idleID];
        state.onEntry();
    }

    public void update(){
        state.update();
    }

    public void render(Graphics g){
        state.render(g);
    }

    private void changeState(int stateCode){
        game.addTask(new Runnable(){
            public void run(){
                state = states[stateCode];
                state.onEntry();
            }
        });
    }

    /*-----------------------------------------------*/
    private abstract class State{

        private BufferedImage[] sprites;
        private int gapFrames = 10;
        private int frameCount = 0;
        private int spriteIndex = 0;

        public void update(){}
        
        public void render(Graphics g){
            BufferedImage image = sprites[spriteIndex];
            if(isMirror){
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
                isMirror=false;
            }else{
                isMirror=true;
            }
        }
        
        public void onEntry(){
            frameCount = gapFrames;
            spriteIndex = 0;
        }

        
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

    private class Idle extends State{

        public Idle(){
            super.sprites = loadSpriteID("idle");
        }

        public void update(){
            updateSprite();
            steer(0,0,100,0.5f);
            updateRelativePos();
            //update state
            if(hypotenuse(relPos[0], relPos[1])<100){
                changeState(walkID);
            }
            //
            updatePos();
        }




    }

    private class Walk extends State{
        private static final float WALKSPEED = 2;
        private static final float MAXACCEL = 0.1f;
        public Walk(){
            super.sprites = loadSpriteID("walk");
        }

        public void update(){
            updateSprite();
            updateRelativePos();
            steer(relPos[0],relPos[1],WALKSPEED,MAXACCEL);
            updateFace();
            updatePos();
            if(hypotenuse(relPos[0], relPos[1])>150){
                changeState(idleID);
            }
        }
    }


    
}