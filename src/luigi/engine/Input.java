package luigi.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input implements KeyListener, MouseListener, MouseMotionListener{

    private boolean[] curKeys = new boolean[256];
    private boolean[] lasKeys = new boolean[256];

    private int frameCount = 0;
    private int[] curPressedFrame = new int[256];
    private int[] lastPressedFrame = new int[256];
    // so...every key is recorded as pressed once at the beginning of the game??

    public Input(GameThread gt){
        gt.addKeyListener(this);
        gt.addMouseListener(this);
        gt.addMouseMotionListener(this);
    }

    public void update(){
        frameCount++;
        lastPressedFrame = curPressedFrame.clone();
        lasKeys = curKeys.clone();
    }

    public boolean keyPressed(int keyCode){
        return !lasKeys[keyCode] && curKeys[keyCode];
    }

    public boolean keyReleased(int keyCode){
        return lasKeys[keyCode] && !curKeys[keyCode];
    }

    public boolean getKey(int keyCode){
        return curKeys[keyCode];
    }

    public int getGap(int keyCode){
        return curPressedFrame[keyCode]-lastPressedFrame[keyCode];
    }

    public int getFrame(){
        return frameCount;
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        curPressedFrame[e.getKeyCode()]= frameCount;
        curKeys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        curKeys[e.getKeyCode()] = false;


    }
    
}