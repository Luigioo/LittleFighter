package luigi.littleFighter.collide;


import java.util.List;
import java.util.ArrayList;

public class Hitbox implements Collidable{


    private int[] cords = new int[4];

    private List<Hurtbox> hurts = new ArrayList<Hurtbox>();

    // private OnCollide onCollide;
    // private GetCords getCords;

    private int damage = 1;

    public Hitbox(){}

    // public Hitbox(OnCollide o, GetCords g){
    //     this.onCollide = o;
    //     this.getCords = g;
    // }

    @Override
    public void onCollide(Collidable c) {
        System.out.println("hits");
        if(c instanceof Hurtbox){
            hurts.add((Hurtbox)c);
        }
        // if(onCollide!=null){
        //     onCollide.onCollide(c);
        // }
    }

    @Override
    public int[] getCords() {
        return null;
    }

    public int getDamage(){return damage;}
    public void setDamage(int i){damage = i;}
    public List<Hurtbox> getHurts(){return hurts;}
    
}