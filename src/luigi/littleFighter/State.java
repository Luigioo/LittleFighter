package luigi.littleFighter;

import luigi.engine.Input;

import java.awt.event.KeyEvent;

public enum State {

    IDLE(0, 4, 15){

    }, 
    WALK(4, 8, 18){

    },
    JUMP(0, 0, 0){

    },
    PUNCH(10, 12, 5){
        
    };

    private final int index1;
    private final int index0;
    private final int length;

    private boolean playBack;
    private int frameCount;


    private State(int index0, int index1, int length){
        this.index0 = index0;
        this.index1 = index1;
        this.length = length;
        this.playBack = false;
        this.frameCount = 0;
    }

    public void update(){
        frameCount++;
        if(frameCount>=length){
            frameCount = 0;
            playBack = !playBack;
        }
    }

    public int getSpriteIndex(){
        if(playBack){
            return (int)map(length-frameCount-1, 0, length, index0+1, index1);
        }else{
            return (int)map(frameCount, 0, length, index0, index1-1);

        }
    }

    public void reset(){
        playBack = false;
        frameCount = 0;
    }

    public boolean isPlayBack(){
        return playBack;
    }
    public void setPlayBack(boolean newPlayBack){
        playBack = newPlayBack;
    }

    public int getLength(){
        return length;
    }


    private float map(float num, float numMin, float numMax, float targetMin, float targetMax){
        float ratio = (targetMax-targetMin)/(numMax - numMin);
        return (num-numMin)*ratio+targetMin;
    }

}