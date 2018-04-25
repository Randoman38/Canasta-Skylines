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
	
	public void render(int[][] pix, int xOffset, int yOffset){
		title.y = y+12;
		
		draw(pix, xOffset, yOffset);
		title.draw(pix, xOffset, yOffset);
		
		for(int i=0;i<lines.length;i++){
			text.setString(lines[i], 0x000000);
			text.y = y+60+i*8+i*3;
			
			text.draw(pix, xOffset, yOffset);
		}
		
		cusRender(pix, xOffset, yOffset);
	}
	
	public abstract void unlock();
	
	public abstract void tick();
	public abstract void cusRender(int[][] pix, int xOffset, int yOffset);
}