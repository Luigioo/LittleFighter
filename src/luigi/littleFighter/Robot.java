package luigi.littleFighter;

import luigi.engine.Input;

public class Robot extends Anime{

    private Input input;

    private float xVel = 0;
    private float yVel = 0;

    private final float XSPEED = 2;
    private final float YSPEED = 2;

    private final float WALKSPEED = 2;

    public Robot(String imagePath, String mirrorPath) {
        super(imagePath, mirrorPath);
    }


    public void update(){
        super.update();
        if(xVel>.1||xVel<-.1||yVel>.1||yVel<-.1){
            float hyp = hypotenuse(xVel, yVel);
            xVel /= hyp;
            yVel /= hyp;
    
            xPos+=xVel*WALKSPEED;
            yPos+=yVel*WALKSPEED;
        }

        if(xPos>720){
            xPos = -60;
        }

    }

    public void moveX(int vX){
        xVel = vX;
        if(vX>0){
            faceRight();
        }else if(vX<0){
            faceLeft();
        }
    }

    public void moveY(int vY){
        yVel = vY;
    }

    private float hypotenuse(float x, float y){
        return (float)Math.sqrt((x*x+y*y));
    }
    
}