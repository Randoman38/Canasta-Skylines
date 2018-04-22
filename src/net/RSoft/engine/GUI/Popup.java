package net.RSoft.engine.GUI;

import net.RSoft.engine.Main;
import net.RSoft.engine.graphics.Bitmap;
import net.RSoft.engine.graphics.CharList;

public abstract class Popup extends Bitmap {
	
	public boolean popping = true;
	
	public CharList title, text;
	public String[] lines;
	
	public Popup() {
		super((Main.WIDTH/2)-225, 360, 225, 150, 2);
		fill(this, 0xededed);
		
	}
	
	public void pop(){
		if(y > (Main.HEIGHT/2)-150 && popping){
			y -= 5;
		}else{
			popping = false;
		}
	}
	
	public abstract void tick();
	public abstract void render(int[][] pix, int xOffset, int yOffset);
}