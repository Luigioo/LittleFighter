
package luigi.littleFighter.player;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.*;


public class StateRun extends StateA {

    public static final int id = 5;

    private final float RUNSPEED = 4;
    private final float MAXACCEL = 0.34f;

    private float dirX = 0;

    public StateRun(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        gapFrames = 10;
        frameCount = 0;
        sprites = loadSpriteID("run");
    }

    @Override
    public void update() {
        updateSprite();
        updateDir();

        //update robot state
        if(dir[0]==0||dir[0]!=dirX){
            robot.changeState(StateIdle.id);
            return;
        }

        if(input.keyPressed(KeyEvent.VK_K)){
            robot.changeState(StateDash.id);
            return;
        }


        // update positions
        steer(dirX*2, dir[1], RUNSPEED, MAXACCEL);
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
    public void onEntry() {
        super.onEntry();
        //record run direction
        dirX = 0;
        if(dir[0]<0){
            dirX = -1;
            robot.faceLeft();
        }else if(dir[0]>0){
            dirX = 1;
            robot.faceRight();
        }
    }

    @Override
    public int id() {
        return id;
    }
    
}