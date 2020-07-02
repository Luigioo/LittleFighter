package luigi.littleFighter.player;

import java.awt.Graphics;

import luigi.littleFighter.collide.*;

public abstract class StateFightA extends StateA {

    protected Hitbox hb;
    protected int nextID = -1;

    public StateFightA(Robot robot) {super(robot);}

    public void fasterHitbox(int x,int y,int w, int h){
        if(hb==null){
            if(robot.isMirror){
                hb = createHitBox(80-x-w,y,w,h);
            }else{
                hb = createHitBox(x,y,w,h);
            }
        }
    }
    public void fasterRemove(){
        if(hb!=null){
            removeHitBox(hb);
            hb=null;
        }
    }
    public void fasterNext(){
        if(nextID!=-1){
            robot.changeState(nextID);
            return;
        }
    }



}