package luigi.littleFighter;


import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.*;


public class StatePunch extends StateA{

    public static final int id = 3;


    public StatePunch(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        gapFrames = 10;
        frameCount = gapFrames;
        sprites = loadSpriteID("punch");
    }

    @Override
    public void update(){
        //count frames 
        frameCount--;
        if(frameCount<=0){
            frameCount = gapFrames;
            spriteIndex++;
            spriteIndex%=sprites.length;
            if(spriteIndex==0){
                robot.changeState(StateIdle.id);
                return;
            }
        }
        updatePos();
    }

    @Override
    public void render(Graphics g) {
        BufferedImage image = sprites[spriteIndex];
        if(robot.isMirror){
            g.drawImage(image, (int)pos[0]+image.getHeight(), (int)pos[1], image.getWidth(), -image.getHeight(), null);
        }else{
            g.drawImage(image, (int)pos[0],(int)pos[1],image.getWidth(),image.getHeight(),null);
        }
    }


    
}