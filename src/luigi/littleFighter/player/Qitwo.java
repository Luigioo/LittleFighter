package luigi.littleFighter.player;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Qitwo extends StateFightA{
    
    public static final int id = 8;

    public Qitwo(Robot robot){super(robot);}

    @Override
    public void init() {
        sprites = loadSpriteID("qitwo");
        int[] tempDura = {
            6,
            6,
            6,
            6,
            6,
            6,
            6
        };
        durations = tempDura;
    }

    @Override
    public void update() {
        upSpriteDuration();
        switch(spriteIndex){
            case 0:

            break;

            case 1:
                handleInput();
            break;

            case 2:
                fasterHitbox(56, 33, 50, 21);
            case 3:
            case 4:
                handleInput();
            break;
            
            case 5:
                fasterRemove();
                fasterNext();
            case 6:
                handleInput();
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
    @Override
    public int id() {return id;}

    public void handleInput(){
        if(input.keyPressed(KeyEvent.VK_J)){nextID = Punchtwo.id;}
        if(input.keyPressed(KeyEvent.VK_I)){nextID = Qione.id;}
    }

}