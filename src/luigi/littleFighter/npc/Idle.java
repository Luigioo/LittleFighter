package luigi.littleFighter.npc;

public class Idle extends State{

    protected static final int ID = 0;

    public Idle(Enemy master) {
        super(master);
        sprites = loadSpriteID("idle");
    }


    public void update(){
        updateSprite();
        steer(0,0,100,0.5f);
        updateRelativePos();
        //update state
        if(hypotenuse(relPos[0], relPos[1])<100){
            changeState(Walk.ID);
        }
        //
        updatePos();
    }
    
}