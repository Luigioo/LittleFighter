package luigi.littleFighter.player;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.*;

import luigi.littleFighter.collide.Hitbox;


public class StatePunch extends StateA{

    public static final int id = 3;

    private Hitbox hb;

    private int nextID=-1;

    public StatePunch(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        gapFrames = 10;
        frameCount = 0;
        sprites = loadSpriteID("punchone");
    }

    @Override
    public void update(){
        updateSprite();
        switch(spriteIndex){
            case 0:

            break;

            case 1:
                if(hb==null){
                    if(robot.isMirror){
                        hb = createHitBox(0, 33, 57, 21);
                    }else{
                        hb = createHitBox(23, 31, 57, 21);
                    }
                }

                if(input.keyPressed(KeyEvent.VK_J)){nextID = Punchtwo.id;}
            break;

            case 2:
                if(hb!=null){
                    removeHitBox(hb);
                    hb=null;
                }
                if(input.keyPressed(KeyEvent.VK_J)){nextID = Punchtwo.id;}
            break;

            case 3:
                if(input.keyPressed(KeyEvent.VK_J)){nextID = Punchtwo.id;}
                if(nextID!=-1){
                    robot.changeState(Punchtwo.id);
                    return;
                }
            break;

            default:
            break;
        }
        friction(0.6f);
        updatePos();
        if(shouldExit){
            robot.changeState(StateIdle.id);
            return;
        }
    }


    @Override
    public void render(Graphics g) {
        super.render(g);
        if(hb!=null){
            hb.gizmos(g);
        }
    }
    @Override
    public void onEntry(){
        super.onEntry();
        nextID = -1;
    }
    public int id(){return id;}
    
    // public void handleInput(){
    //     if(input.keyPressed(KeyEvent.VK_J)){nextID = Punchtwo.id;}
    //     //if(input.keyPressed(KeyEvent.VK_J)){nextID = Punchtwo.id;}
    // }
}