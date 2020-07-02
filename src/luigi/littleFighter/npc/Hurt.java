package luigi.littleFighter.npc;

public class Hurt extends State{

    protected static final int ID = 2;

    public Hurt(Enemy master) {
        super(master);
        super.sprites = loadSpriteID("hurt");
        gapFrames = 15;
    }

    public void update(){
        updateSprite();
        steer(0,0,10,0.3f);
        updatePos();
        if(shouldExit){
            changeState(Idle.ID);
        }
    }
    
}