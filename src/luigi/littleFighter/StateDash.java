package luigi.littleFighter;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.*;

public class StateDash extends StateA{

    
    public static final int id = 4;

    private final float DASHSPEED = 4f;
    private final float MAXACCEL = 0.4f;
    
    private float dashx;
    private float dashy;
    

    public StateDash(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        gapFrames = 10;
        frameCount = gapFrames;
        sprites = loadSpriteID("dash");
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

        //update positions
        steer(dashx,dashy,DASHSPEED,MAXACCEL);
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

    @Override
    public void onEntry(){
        super.onEntry();

        //determine dash direction
        updateDir();
        if(dir[0]>.1||dir[0]<-.1||dir[1]>.1||dir[1]<-.1){
            dashx = dir[0];
            dashy = dir[1];
        }else{
            dashy = 0;
            if(robot.isMirror){
                dashx = -1;
            }else{
                dashx = 1;
            }
        }
    }

}