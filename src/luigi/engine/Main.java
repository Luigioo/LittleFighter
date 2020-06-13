package luigi.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Main extends AbGame{

	private Input input;
	
	@Override
	public void setup() {
		
	}
	
	@Override
	public void update() {

	}
	
	@Override
	public void render(Graphics g) {
		
	}

	public Main(){
		GameThread gameThread = new GameThread(this,720,480);
		input = gameThread.getInput();
		gameThread.start();
	}
	public static void main(String[] args){
		new Main();
	}

}