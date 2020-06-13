package luigi.littleFighter;

public class Anime extends Sprite{

    // private int[][] states = {
    //     {0, 4, 15},//idle
    //     {4, 8, 15},//walk
    //     {10, 12, 5}//light punch
    // };

    protected State state = State.IDLE;

    public Anime(String imagePath, String mirrorPath) {
        super(imagePath, mirrorPath);
    }



    public void update(){

        // if(frame>=state.getLength()){
        //     frame = 0;
        //     state.altPlayBack();
        // }

        // index = state.getSpriteIndex(frame);

        // frame++;

        // state.setPlayBack(!state.isPlayBack());
        // System.out.println(state.isPlayBack());
        // State tes = State.Jump;
        // System.out.println(tes.isPlayBack());

        index = state.getSpriteIndex();
        state.update();

        //System.out.println("index: " + index + "frame: "+frame);
    }

    // private float map(float num, float numMin, float numMax, float targetMin, float targetMax){
    //     float ratio = (targetMax-targetMin)/(numMax - numMin);
    //     return (num-numMin)*ratio+targetMin;
    // }

    public void changeState(State newState){
        state = newState;
        state.reset();
    }
    
}
