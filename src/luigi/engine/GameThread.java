package luigi.engine;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.*;

public class GameThread extends JPanel implements Runnable{

	private AbGame abGame;
	private Input input;

	private JFrame frame;
    private Thread thread;
    private float frameCap = 1.0f/60.0f;
	private float SECOND = 1000000000.0f;
	
	private int frameCount = 0;

    public GameThread(AbGame ab, int wid, int hig){
        abGame = ab;
		thread = new Thread(this);
		input = new Input(this);
		this.setPreferredSize(new Dimension(wid,hig));
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	public GameThread(AbGame ab, int wid, int hig, JFrame fm){
		this(ab,wid,hig);
		frame = fm;
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(wid, hig);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		abGame.render(g);
	}

    public void start(){
		if(frame!=null){
			frame.setVisible(true);
		}
		thread.start();
    }

    
    @Override
    public void run(){

        float firstTime = 0;
		float lastTime = System.nanoTime() / SECOND;
		float passedTime = 0;
		
		float unprocessedTime = 0;
		float fpsTime = 0;
		int frames = 0;
        int fps = 0;
        
        while(true){
            boolean render = false;
			
			firstTime = System.nanoTime() / SECOND;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;
			
			unprocessedTime += passedTime;
			fpsTime += passedTime;
			
			while(unprocessedTime >= frameCap)
			{
				render = true;
				unprocessedTime -= frameCap;
				
				//update
				frameCount++;
                abGame.update();
				input.update();
                //print fps
				if(fpsTime >= 1)
				{
					fpsTime = 0;
					fps = frames;
					frames = 0;
					System.out.println(fps);
				}
			}
			
			if(render)
			{
                //render
                repaint();
                //
				frames++;
			}
			else
			{
				try
				{
					Thread.sleep(1);
				} 
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			

        }


	}
	
	public Input getInput(){
		return input;
	}
	
	public void setName(String newName){
		if(frame!=null){
			frame.setTitle(newName);
		}else{
			System.out.println("no frame");
		}
	}

	public int getFrameCount(){
		return frameCount;
	}
    
}