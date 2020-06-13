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
        hero = new Robot("res\\bandit.png", "res\\bandit_0_mirror.png");
        
    }

    @Override
    public void update() {

        int xDir, yDir;
        switch(hero.state){
            case IDLE:
                xDir = 0;
                yDir = 0;
                if(input.getKey(KeyEvent.VK_D)){
                    xDir++;
                }
                if(input.getKey(KeyEvent.VK_A)){
                    xDir--;
                }
                if(input.getKey(KeyEvent.VK_S)){
                    yDir++;
                }
                if(input.getKey(KeyEvent.VK_W)){
                    yDir--;
                }
                if(!(xDir==0&&yDir==0)){
                    hero.changeState(State.WALK);
                }else{
                    hero.moveX(0);
                    hero.moveY(0);
                }
                if(input.keyPressed(KeyEvent.VK_J)){
                    hero.changeState(State.PUNCH);
                }
            break;

            case WALK:
                xDir = 0;
                yDir = 0;
                if(input.getKey(KeyEvent.VK_D)){
                    xDir++;
                }
                if(input.getKey(KeyEvent.VK_A)){
                    xDir--;
                }
                if(input.getKey(KeyEvent.VK_S)){
                    yDir++;
                }
                if(input.getKey(KeyEvent.VK_W)){
                    yDir--;
                }
                if(xDir==0){
                    if(yDir==0){
                        hero.changeState(State.IDLE);
                    }else{
                        hero.moveY(yDir);
                    }
                }else{
                    hero.moveX(xDir);
                    hero.moveY(yDir);
                }
                // if(!(input.getKey(KeyEvent.VK_D)||input.getKey(KeyEvent.VK_A)||
                //     input.getKey(KeyEvent.VK_S)||input.getKey(KeyEvent.VK_W))){
                //         hero.changeState(State.IDLE);
                //         break;
                // }else{
                //     if(input.getKey(KeyEvent.VK_D)){
                //         if(input.getKey(KeyEvent.VK_A)){
                //             hero.moveX(0);
                //             if(!input.getKey(KeyEvent.VK_S)&&!input.getKey(KeyEvent.VK_W)){
                //                 hero.changeState(State.IDLE);
                //                 break;
                //             }
                //         }else{
                //             hero.faceRight();
                //             hero.moveX(1);
                //         }
                //     }else if(input.getKey(KeyEvent.VK_A)){
                //         hero.faceLeft();
                //         hero.moveX(-1);
                //     }
                //     if(input.getKey(KeyEvent.VK_S)){
                //         if(input.getKey(KeyEvent.VK_W)){
                //             hero.moveY(0);
                //             if(!input.getKey(KeyEvent.VK_D)&&!input.getKey(KeyEvent.VK_A)){
                //                 hero.changeState(State.IDLE);
                //                 break;
                //             }
                //         }else{
                //             hero.moveY(1);
                //         }
                //     }else if(input.getKey(KeyEvent.VK_W)){
                //         hero.moveY(-1);
                //     }
                // }

            break;

            case JUMP:

            break;

            case PUNCH:
                
            break;


            default:
            break;
        }

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