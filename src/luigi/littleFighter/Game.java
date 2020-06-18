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
    private ArrayList<Enemy> enemies;

    private AdjustPanel ap;

    @Override
    public void setup() {
        setName("little");
        todos = new ArrayList<Runnable>();
        ap = new AdjustPanel(this);
        ap.createGUI();
        hero = new Robot(this);
        enemies = new ArrayList<Enemy>();
        enemies.add(new Enemy(this));
        enemies.get(0).test1();
        enemies.get(0).test();
        enemies.add(new Enemy(this));
        enemies.get(1).test();
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
        ImageIcon bg = new ImageIcon("res/backgrounds/bg_thv.png");
        g.drawImage(bg.getImage(),0,0,null);
        hero.render(g);
    }

    public void addTask(Runnable r){
        todos.add(r);
    }

    public Robot getHero(){
        return hero;
    }

    public static void main(String args[]){
        Game game = new Game();
        game.start();
    }
    
}