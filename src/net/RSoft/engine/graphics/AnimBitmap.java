package net.RSoft.engine.graphics;

import java.io.File;

import javax.imageio.ImageIO;

public class AnimBitmap extends Bitmap {
	
	private int frame = 0, count = 0;
	private final int maxFrames, delay;
	
	public AnimBitmap(int x, int y, int width, int height, String url, int delay){
		super(x, y, url);
		
		try{
			bckup = ImageIO.read(new File("res/" + url + ".png"));
		}catch(Exception e){ }
		
		WIDTH = width;
		HEIGHT = height;
		
		maxFrames = bckup.getHeight()/height;
		this.delay = delay;
		
		read(frame);
	}
	
	public AnimBitmap(int x, int y, int width, int height, String url, int scale, int delay){
		super(x, y, url, scale);
		
		try{
			bckup = ImageIO.read(new File("res/" + url + ".png"));
		}catch(Exception e){ }
		
		WIDTH = width*scale;
		HEIGHT = height*scale;
		
		maxFrames = bckup.getHeight()/height;
		this.delay = delay;
		
		read(frame);
	}
	
	private void read(int offs){
		pixels = new int[WIDTH/SCALE*HEIGHT/SCALE];
		for(int i=0;i<pixels.length;i++){
			pixels[i] = bckup.getRGB(i%(WIDTH/SCALE), (i-(i%(WIDTH/SCALE)))/(WIDTH/SCALE)+offs);
		}
	}
	
	public void animate(){
		if(count > delay){
			count = 0;
			frame++;
			
			if(frame>=maxFrames){
				frame = 0;
			}
			
			read(frame*(HEIGHT/SCALE));
		}else{
			count++;
		}
	}
	
	@Override
	public void tick(){
		animate();
	}
}