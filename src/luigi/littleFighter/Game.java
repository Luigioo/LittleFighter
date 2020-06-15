package luigi.littleFighter;

import luigi.engine.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class Game extends AbGame {

    private Robot hero;
    
    @Override
    public void setup() {
        setName("little");
        hero = new Robot(input);
        
    }

    @Override
    public void update() {
        hero.update();
    }

    @Override
    public void render(Graphics g) {
        hero.render(g);

    }

    public static void main(String args[]){
        Game game = new Game();
        game.start();
    }
    
}