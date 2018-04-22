package net.RSoft.engine.GUI;

import net.RSoft.engine.Main;
import net.RSoft.engine.graphics.Bitmap;

public abstract class Button extends Bitmap {
	
	public int state = 0, type = 0;
	
	public Button(int x, int y, int width, int height, String url){
		super(x, y, url);
		
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[width*height];
		
		read(type, state);
	}
	
	public Button(int x, int y, int width, int height, String url, int scale){
		super(x, y, url, scale);
		
		WIDTH = width*scale;
		HEIGHT = height*scale;
		pixels = new int[width*height];
		
		read(type, state);
	}
	
	protected void read(int xOffs, int yOffs){
		for(int i=0;i<pixels.length;i++){
			pixels[i] = bckup.getRGB(i%(WIDTH/SCALE)+xOffs, (i-(i%(WIDTH/SCALE)))/(WIDTH/SCALE)+yOffs);
		}
	}
	
	public abstract void onPress();
	public abstract void cusTick();
	
	public void tick(){
		cusTick();
		
		read(type*(WIDTH/SCALE), state*(HEIGHT/SCALE));
		
			if(contains(Main.mn.ms)){
				if(!Main.mn.ms.pressBut && Main.mn.ms.released){
					Main.mn.ms.pressBut = true;
					onPress();
				}
			}
	}
}