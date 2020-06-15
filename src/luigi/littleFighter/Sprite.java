package luigi.littleFighter;

import java.io.File;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.awt.Graphics;

public class Sprite {

    protected float xPos = 0;
    protected float yPos = 160;

    private float width = 80;
    private float height = 80;

    private BufferedImage image;
    private BufferedImage mirror;

    protected int index = 0;
    protected boolean isMirror = false;
    
    public Sprite(String imagePath, String mirrorPath){

        try{
            File in = new File(imagePath);
            image = ImageIO.read(in);
            File in1 = new File(mirrorPath);
            mirror = ImageIO.read(in1);

        }catch(Exception e){
            e.printStackTrace();
        }


    }

    public void render(Graphics g){
        if(!isMirror){
            int row = index/10;
            int col = index%10;
            int x1 = col*(int)width, x2 = (col+1)*(int)width;
            int y1 = row*(int)height, y2 = (row+1)*(int)width;
            g.drawImage(image, 
                (int)xPos, (int)yPos, (int)(xPos+width), (int)(yPos+height),
                x1, y1, x2, y2, null);
        }else{
            int row = index/10;
            int col = 9-index%10;
            int x1 = col*(int)width, x2 = (col+1)*(int)width;
            int y1 = row*(int)height, y2 = (row+1)*(int)width;
            g.drawImage(mirror, 
                (int)xPos, (int)yPos, (int)(xPos+width), (int)(yPos+height),
                x1, y1, x2, y2, null);
        }


    }

    public void faceRight(){
        isMirror = false;
    }
    public void faceLeft(){
        isMirror = true;
    }
    
    public float getXPos(){
        return xPos;
    }
    public float getYPos(){
        return yPos;
    }

    public void setXPos(float newXPos){
        xPos = newXPos;
    }
    public void setYPos(float newYPos){
        yPos = newYPos;
    }

}