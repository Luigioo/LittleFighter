package luigi.littleFighter;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.*;

public class StateDash extends StateA{

    
    public static final int id = 4;

    private final float DASHSPEED = 6f;
    private final float MAXACCEL = 0.4f;
    
    private float dashx;
    private float dashy;
    

    public StateDash(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        gapFrames = 10;
        frameCount = 0;
        sprites = loadSpriteID("dash");
    }


    @Override
    public void update(){
        //count frames 
        frameCount++;
        if(frameCount>=gapFrames){
            frameCount = 0;
            spriteIndex++;
            spriteIndex%=sprites.length;
            if(spriteIndex==0){
                robot.changeState(StateIdle.id);
                return;
            }
        }

        //
        if((sprites.length*gapFrames)-
            (spriteIndex*gapFrames+frameCount)<15){
                steer(0,0,100,0.1f);
        }else{
            steer(dashx,dashy,DASHSPEED,MAXACCEL);
        }


        //update positions
        updatePos();
    }

    @Override
    public void render(Graphics g) {
        BufferedImage image = sprites[spriteIndex];
        if(robot.isMirror){
            g.drawImage(image, (int)pos[0]+image.getWidth(), (int)pos[1], -image.getWidth(), image.getHeight(), null);
        }else{
            g.drawImage(image, (int)pos[0],(int)pos[1],image.getWidth(),image.getHeight(),null);
        }
    }

    @Override
    public void onEntry(){
        super.onEntry();

        //determine dash direction
        updateDir();
        if((dir[0]+dir[1])==0){
            if(robot.isMirror){
                dashx = -2;
            }else{
                dashx = 2;
            }
            dashy = 0;
        }else{
            dashx=dir[0];
            dashy=dir[1];
        }
    }
    public int id(){
        return id;
    }
}