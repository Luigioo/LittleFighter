package luigi.littleFighter.player;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import luigi.littleFighter.collide.Hitbox;

public class Qione extends StateFightA {
    
    public static final int id = 7;

    public Qione(Robot robot) {super(robot);}

    private BufferedImage[] transed;
    private BufferedImage[] startup;

    @Override
    public void init() {
        startup = loadSpriteID("qione");
        transed = startup.clone();
        transed[0] = getSpritebyName("qitwo2");
        transed[1] = getSpritebyName("qitwo1");
        sprites = startup;
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
                fasterNext();
                fasterRemove();
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
        sprites = startup;
        if(history.get(history.size()-1) instanceof Qitwo){
            System.out.println("from qitwo");
            sprites = transed;
        }
    }
    @Override
    public int id() {return id;}

    public void handleInput(){
        //if(input.keyPressed(KeyEvent.VK_J)){nextID = Punchtwo.id;}
        if(input.keyPressed(KeyEvent.VK_I)){nextID = Qitwo.id;}
    }
    
}