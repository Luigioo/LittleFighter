package luigi.littleFighter;

import luigi.engine.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;

public class Game extends AbGame {

    private ArrayList<Runnable> todos;
    
    private Robot hero;

    private AdjustPanel ap;

    @Override
    public void setup() {
        setName("little");
        todos = new ArrayList<Runnable>();
        ap = new AdjustPanel(this);
        ap.createGUI();
        hero = new Robot(input);
        
    }

    @Override
    public void update() {
        //System.out.println(ap.getValue());
        hero.update();

        //execute the to-dos
        for(Runnable r:todos){
            r.run();
        }
        todos.clear();
    }

    @Override
    public void render(Graphics g) {
        hero.render(g);

    }

    public void addTask(Runnable r){
        todos.add(r);
    }

    public static void main(String args[]){
        Game game = new Game();
        game.start();
    }
    
}