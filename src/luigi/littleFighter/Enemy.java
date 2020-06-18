package luigi.littleFighter;

import java.awt.image.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Enemy extends Sprite{

    private Game game;
    private Robot hero;
    private float[] pos = {400f,60f};
    private float[] vel = new float[2];
    private boolean isMirror = true;
    private EState state = EState.IDLE;

    private int life = 3;

    public Enemy(Game g) {
        super("res/bandit");
        this.game = g;
        EState.construct(this);
    }

    public void hurt(){
        life--;
        System.out.println("hurt");
        //plays an animation...

    }
    public float[] getPos(){
        return pos;
    }
    public float[] getVel(){
        return vel;
    }
    public void test(){
        // state.update();
        System.out.println(state.test);
    }
    public void test1(){
        state.update();
    }

    //-------------------------------------
    private enum EState{
        IDLE() {
            public void init() {
                sprites=master.loadSpriteID("idle");
            }
            public void update(){
                test = "idle";
            }
        },
        WALK() {
            public void init() {
                master.hurt();
                sprites = master.loadSpriteID("walk");
            }  
            public void update(){
                test = "walk";
            }

        };
        
        public static String test = "original";

        protected BufferedImage[] sprites;
        protected int gapFrames = 10;
        protected int frameCount;
        protected int spriteIndex;

        protected static Enemy master;
        protected static float[] pos;
        protected static float[] vel;

        public static void construct(Enemy e){
            master = e;
            pos = master.getPos();
            vel = master.getVel();
        }

        public abstract void init();
        public abstract void update();
        public void render(Graphics g){
            BufferedImage image = sprites[spriteIndex];
            if(master.isMirror){
                g.drawImage(image, (int)pos[0]+image.getWidth(), (int)pos[1], -image.getWidth(), image.getHeight(), null);
            }else{
                g.drawImage(image, (int)pos[0],(int)pos[1],image.getWidth(),image.getHeight(),null);
            }
        }

    }

    
}