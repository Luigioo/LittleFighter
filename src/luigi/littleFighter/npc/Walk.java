package luigi.littleFighter.npc;

import luigi.littleFighter.collide.Collidable;

public class Walk extends State {
    
    protected static final int ID = 1;

    private static final float WALKSPEED = 2;
    private static final float MAXACCEL = 0.1f;

    public Walk(Enemy master){
        super(master);
        super.sprites = loadSpriteID("walk");
    }


    public void update(){
        updateSprite();
        updateRelativePos();
        //steer(relPos[0],relPos[1],WALKSPEED,MAXACCEL);
        updateFace();
        updatePos();
        if(hypotenuse(relPos[0], relPos[1])>150){
            changeState(Idle.ID);
        }
    }

    public void onCollide(Collidable c){
        super.onCollide(c);
        changeState(Hurt.ID);
    }

}