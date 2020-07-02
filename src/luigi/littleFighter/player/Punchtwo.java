package luigi.littleFighter.player;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;

import luigi.littleFighter.collide.Hitbox;

public class Punchtwo extends StateA {

    public static final int id = 6;
    private Hitbox hb;
    private int nextID=-1;

    public Punchtwo(Robot robot) {super(robot);}

    @Override
    public void init() {
        gapFrames = 10;
        frameCount = 0;
        sprites = loadSpriteID("punchtwo");
    }

    @Override
    public void update() {
        //count frames 
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

                if(input.keyPressed(KeyEvent.VK_J)){nextID = StatePunch.id;}
            break;
            case 2:
                if(hb!=null){
                    removeHitBox(hb);
                    hb=null;
                }
                if(input.keyPressed(KeyEvent.VK_J)){nextID = StatePunch.id;}
            break;
            case 3:
                if(input.keyPressed(KeyEvent.VK_J)){nextID = StatePunch.id;}
                if(nextID!=-1){
                    robot.changeState(StatePunch.id);
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
        BufferedImage image = sprites[spriteIndex];
        if(robot.isMirror){
            g.drawImage(image, (int)pos[0]+image.getWidth(), (int)pos[1], -image.getWidth(), image.getHeight(), null);
        }else{
            g.drawImage(image, (int)pos[0],(int)pos[1],image.getWidth(),image.getHeight(),null);
        }

        if(hb!=null){
            hb.gizmos(g);
        }
    }

    @Override
    public void onEntry(){
        super.onEntry();
        nextID = -1;
    }

    @Override
    public int id() {return id;}
    
}