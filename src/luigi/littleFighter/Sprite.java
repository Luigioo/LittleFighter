package luigi.littleFighter;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.image.*;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.Graphics;


public class Sprite {

    protected BufferedImage[] sprites;
    protected String[] spriteNames;

    protected float[] pos;

    private static final float width = 80;
    private static final float height = 80;

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

        float[] tempPos = {0f,160f};
        pos = tempPos;

    }

    public Sprite(String folder){
        this.spriteNames = getResourceFiles(folder);

        for(String s:spriteNames){
            System.out.println(s);
        }
        // ClassLoader loader = Thread.currentThread().getContextClassLoader();
        // URL url = loader.getResource("res/"+folder);
        // String path = url.getPath();
        // File[] files = new File(path).listFiles();
        // try{
        //     sprites = new BufferedImage[files.length];
        //     for(int i=files.length-1;i>=0;i--){
        //         System.out.println(files[i]);
        //         sprites[i]=ImageIO.read(files[i]);
        //     }
        // }catch(Exception e){
        //     System.out.println("exception");
        // }
    }
    public BufferedImage[] loadSpriteID(String targetName){
        BufferedImage[] sps = getSprites();
        String[] names = getSpriteNames();
        List<BufferedImage> targets = new ArrayList<BufferedImage>();
        for(int i=0;i<names.length;i++){
            if(names[i].contains(targetName)){
                targets.add(sps[i]);
            }
        }
        return targets.toArray(new BufferedImage[0]);
    }

    private String[] getResourceFiles(String path){
        List<String> filenames = new ArrayList<String>();
    
        try (
                InputStream in = getResourceAsStream(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String resource;
    
            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
            java.util.Collections.sort(filenames);

            sprites = new BufferedImage[filenames.size()];
            for(int i=filenames.size()-1;i>=0;i--){
                sprites[i]=ImageIO.read(new File(path+"/"+filenames.get(i)));
            }
        }catch(Exception e){System.out.println("exception");};

        return filenames.toArray(new String[0]);
    }
    
    private InputStream getResourceAsStream(String resource) {
        final InputStream in
                = getContextClassLoader().getResourceAsStream(resource);
    
        return in == null ? getClass().getResourceAsStream(resource) : in;
    }
    
    private ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    //getters & setters
    public BufferedImage[] getSprites(){
        return sprites;
    }
    public String[] getSpriteNames(){
        return spriteNames;
    }

}