package luigi.littleFighter.collide;

import java.awt.Color;
import java.awt.Graphics;

public abstract interface Collidable{
    public default boolean collideWith(Collidable c){
        int[] th = this.getCords();
        int[] ot = c.getCords();
        th[2]+=th[0];
        th[3]+=th[1];
        ot[2]+=ot[0];
        ot[3]+=ot[1];
        return !(th[2]<ot[0]||th[0]>ot[2]||th[3]<ot[1]||th[1]>ot[3]);
    }
    public void onCollide(Collidable c);
    public int[] getCords();
    public default void gizmos(Graphics g){
        g.setColor(new Color(0f,.29f,.6f,.5f));
        int[] r = getCords();
        g.fillRect(r[0],r[1],r[2],r[3]);
    }
}