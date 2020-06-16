package luigi.littleFighter;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.*;

public class StateIdle extends StateA{

    public static final int id = 0;
    
    private final float friction = .6f;

    public StateIdle(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        gapFrames=10;
        frameCount=gapFrames;
        sprites = loadSpriteID("idle");
    }

    @Override
    public void update(){
        
        updateSprite();
        
        updateDir();

        //update robot state
        if(input.keyPressed(KeyEvent.VK_K)){
            robot.changeState(StateDash.id);
            return;
        }
        if(input.keyPressed(KeyEvent.VK_J)){
            robot.changeState(StatePunch.id);
            return;
        }
        
        if(!(dir[0]==0&&dir[1]==0)){
            robot.changeState(StateWalk.id);
            return;
        }
        
        friction(friction);
        updatePos();
        
    }

    public void render(Graphics g){
        BufferedImage image = sprites[spriteIndex];
        if(robot.isMirror){
            g.drawImage(image, (int)pos[0]+image.getHeight(), (int)pos[1], image.getWidth(), -image.getHeight(), null);
        }else{
            g.drawImage(image, (int)pos[0],(int)pos[1],image.getWidth(),image.getHeight(),null);
        }
    }

}