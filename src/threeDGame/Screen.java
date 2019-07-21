package threeDGame;

import java.util.Random;

public class Screen extends Render {

	private Render test;
	private Render3D render;
	public Screen(int width, int height) {
		super(width, height);
		render = new Render3D(width, height);
		Random random = new Random();
		test = new Render(256,256);
		for(int i =0 ; i < 256 * 256; i++) {
			test.pixels[i] = random.nextInt() * (random.nextInt(256*256)/(256*255));
			//System.out.println(random.nextInt(4)/3 +"asdf");
		}
	}
	
	public void render(Game game) {
		
		
		for(int i=0; i < width * height; i++)
			pixels[i] = 0;
		
		draw(this, 0, 0);
		
		render.floor(game);
		draw(render, 0, 0);
		/*
		for(int i = 0; i < 100; i++) {
			//int anix = (int) (Math.sin((System.currentTimeMillis()+i*2) %2000/2000.0 * Math.PI * 2) * 180);
			double x = game.time;
			//System.out.println("Double " + x);
			//int anix = (int) (Math.sin((x+i) %1000/1000.0 * Math.PI * 2) * 180);
			int anix = (int) (Math.sin((x+i) %1000/100.0) * 180);
			//int time = game.tick()+i;
			//System.out.println(time);
			//time = (int)System.currentTimeMillis();
			
			//int anix = (int) (Math.sin((game.time + i) % 1000 / 100) * 100);
			//int aniy = (int) (Math.cos((System.currentTimeMillis()+i*2) %2100/2100.0 * Math.PI * 2) * 250);
			int aniy = (int) (Math.cos((x +i) %1000 /100.0)* 180);
			draw(test, (width-256)/2 - anix,(height-256)/2 - aniy);
			//System.out.println(anim);
			//System.out.println(System.currentTimeMillis());
		}
		*/
	}

}
