package threeDGame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Display extends Canvas implements Runnable {
	static final int WIDTH = 800;
	static final int HEIGHT = 600;
	static final String TITLE = "ThreeD Game?";
	
	private Thread thread; 
	private Boolean running = false;
	private Render render;
	private Screen screen;
	private BufferedImage img;
	private Game game;
	int[] pixels;
	InputHandler input;
	int fps;
	
	public Display() {
		game = new Game();
		screen = new Screen(WIDTH, HEIGHT);
		//render = new Render(WIDTH, HEIGHT);
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
		input = new InputHandler();
		addMouseListener(input);
		addMouseMotionListener(input);
		addKeyListener(input);
		addFocusListener(input);
	}
	
	private void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
		System.out.println("Thread Started.");
	}
	
	private void stop() {
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(0);	
		}
		
		
	}

	@Override
	public void run() {
		System.out.println("Running.");
		int frames = 0;
		double unprocessedSeconds = 0;
		long previousTime = System.nanoTime();
		double secondsPerTick = 1 / 60.0;
		int tickCount = 0;
		Boolean ticked = false;
		while(running) {
			long currentTime = System.nanoTime();
			long passedTime = currentTime - previousTime;
			previousTime = currentTime;
			unprocessedSeconds += passedTime / 1000000000.0;
			while(unprocessedSeconds > secondsPerTick) {
				tick();
				unprocessedSeconds -= secondsPerTick;
				//ticked = true;
				tickCount++;
				if(tickCount % 60 == 0) {
					System.out.println(frames + " fps");
					fps = frames;
					previousTime += 1000;
					frames = 0;
				}
			}
			//if(ticked) {
			//	render();
			//	frames++;
			//}
			render();
			frames++;
		}
		
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.render(game);
		for(int i = 0; i < WIDTH * HEIGHT; i++)
			pixels[i] = screen.pixels[i];
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, this.WIDTH, this.HEIGHT, null);
		g.setFont(new Font("Verdana", 3, 30));
		g.setColor(Color.yellow);
		g.drawString(fps + " FPS", 50, 50);
		g.dispose();
		bs.show();
		
	}

	private void tick() {
		game.tick(input.key);
	}
	
	public static void main(String[] args) {
		Display display = new Display();
		JFrame jframe = new JFrame();
		jframe.pack();
		jframe.add(display);
		jframe.setSize(WIDTH, HEIGHT);
		jframe.setTitle(TITLE);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setResizable(false);
		jframe.setVisible(true);
		jframe.setLocationRelativeTo(null);
		System.out.println("Starting...");
		display.start();
	}

}
