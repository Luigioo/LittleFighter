package luigi.littleFighter.player;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.*;

public class StateIdle extends StateA{

    public static final int id = 0;
    
    private final float friction = .6f;
    private final int CLICKLIMIT = 15;

    public StateIdle(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        gapFrames=10;
        frameCount=0;
        sprites = loadSpriteID("idle");
    }

    @Override
    public void update(){
        
        updateSprite();
        updateDir();
        steer(0,0,100,0.5f);

        //update robot state

        if(input.keyPressed(KeyEvent.VK_K)){
            robot.changeState(StateDash.id);
            return;
        }
        if(input.keyPressed(KeyEvent.VK_J)){
            robot.changeState(StatePunch.id);
            return;
        }
        if(input.keyPressed(KeyEvent.VK_I)){
            robot.changeState(Qione.id);
            return;
        }
        
        int gap = input.getGap(KeyEvent.VK_D);
        int gapA = input.getGap(KeyEvent.VK_A);
        if(gap>0&&gap<CLICKLIMIT||gapA>0&&gapA<CLICKLIMIT){
            robot.changeState(StateRun.id);
            return;
        }

        if(!(isO(dir[0])&&isO(dir[1]))){
            robot.changeState(StateWalk.id);
            return;
        }
        
        updatePos();
        
    }

    public void render(Graphics g){
        BufferedImage image = sprites[spriteIndex];
        if(robot.isMirror){
            g.drawImage(image, (int)pos[0]+image.getWidth(), (int)pos[1], -image.getWidth(), image.getHeight(), null);
        }else{
            g.drawImage(image, (int)pos[0],(int)pos[1],image.getWidth(),image.getHeight(),null);
        }
    }

    public int id(){
        return id;
    }
}