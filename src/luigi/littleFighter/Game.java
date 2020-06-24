package luigi.littleFighter;

import luigi.engine.*;
import luigi.littleFighter.npc.Enemy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;

public class Game extends AbGame implements Comparator<Layered>{

    private ArrayList<Runnable> todos;

    private AdjustPanel ap;
    
    private Robot hero;
    private ArrayList<Enemy> enemies;

    private List<Layered> layeredRenders;

    @Override
    public void setup() {

        setName("little");

        todos = new ArrayList<Runnable>();

        ap = new AdjustPanel(this);
        ap.createGUI();

        hero = new Robot(this);
        enemies = new ArrayList<Enemy>();
        enemies.add(new Enemy(this));

        layeredRenders = new ArrayList<Layered>();
        layeredRenders.add(hero);
        for(Enemy e:enemies){addLayeredRenders(e);}

    }

    @Override
    public void update() {
        //System.out.println(ap.getValue());
        hero.update();
        for(Enemy e:enemies){
            e.update();
        }
        //execute the to-dos
        for(Runnable r:todos){
            r.run();
        }
        todos.clear();
    }

    @Override
    public void render(Graphics g) {
        ImageIcon bg = new ImageIcon("res/bg/bg_thv.png");
        g.drawImage(bg.getImage(),0,0,null);
        Collections.sort(layeredRenders, this);
        for(Layered r:layeredRenders){
            r.render(g);
        }
    }

    public void addTask(Runnable r){
        todos.add(r);
    }

    public void addLayeredRenders(Layered l){
        layeredRenders.add(l);
    }

    public Robot getHero(){
        return hero;
    }

    public static void main(String args[]){
        Game game = new Game();
        game.start();
    }

    @Override
    public int compare(Layered o1, Layered o2) {
        return (int)(o1.getY()-o2.getY());
    }
    
}