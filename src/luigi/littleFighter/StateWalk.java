package luigi.littleFighter;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.*;

public class StateWalk extends StateA {

    public static final int id = 1;

    private final float WALKSPEED = 2;
    private final float MAXACCEL = 0.2f;

    public StateWalk(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        gapFrames = 10;
        frameCount = gapFrames;
        sprites = loadSpriteID("walk");
    }

    @Override
    public void update() {
        updateSprite();
        updateDir();

        // update robot state
        if (input.keyPressed(KeyEvent.VK_K)) {
            robot.changeState(StateDash.id);
            return;
        }

        if (input.keyPressed(KeyEvent.VK_J)) {
            robot.changeState(StatePunch.id);
            return;
        }
        if (dir[0] == 0) {
            if (dir[1] == 0) {
                robot.changeState(StateIdle.id);
                return;
            }
        }

        // face left/right
        if (dir[0] > .1) {
            robot.faceRight();
        } else if (dir[0] < -.1) {
            robot.faceLeft();
        }

        // update positions
        steer(dir[0], dir[1], WALKSPEED, MAXACCEL);
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