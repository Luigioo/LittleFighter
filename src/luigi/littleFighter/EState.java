// package luigi.littleFighter;

// import java.awt.image.*;
// import java.util.ArrayList;
// import java.util.List;
// import java.awt.event.KeyEvent;
// import java.awt.Graphics;
// import java.awt.Graphics2D;

// public class EState {

//     private Idle i = new Idle();

//    // protected BufferedImage[] sprites;
//     // private int gapFrames = 10;
//     // protected int frameCount;
//     // protected int spriteIndex;

//     private static Enemy master;
//     private static float[] pos;
//     private static float[] vel;

//     public EState(Enemy e){
//         master = e;
//         pos = master.getPos();
//         vel = master.getVel();
//     }

//     public void render(Graphics g){
//         BufferedImage image = sprites[spriteIndex];
//         if(master.isMirror){
//             g.drawImage(image, (int)pos[0]+image.getWidth(), (int)pos[1], -image.getWidth(), image.getHeight(), null);
//         }else{
//             g.drawImage(image, (int)pos[0],(int)pos[1],image.getWidth(),image.getHeight(),null);
//         }
//     }

//     private class Idle{
//         public Idle(){
//             gapFrames = 01;
//         }
//     }

//     private class Walk{

//     }

// }

