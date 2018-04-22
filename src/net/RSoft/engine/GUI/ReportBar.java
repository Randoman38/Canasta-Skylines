package net.RSoft.engine.GUI;

import net.RSoft.engine.graphics.Bitmap;
import net.RSoft.engine.graphics.CharList;

public class ReportBar extends Bitmap {
	
	public CharList arrivals;
	
	public ReportBar(int x, int y) {
		super(x, y, "GUI/Report", 2);
		
		arrivals = new CharList(x+8, y+8, "0 arrivals last month...", 1, -1);
	}
	
	public void tick(){
		
	}
	
	public void updateBar(int arrivals){
		if(arrivals < 0){
			this.arrivals.setString((arrivals*-1) + " left last month.");
		}else{
			this.arrivals.setString(arrivals + " arrivals last month.");
		}
	}
	
	public void render(int[][] pix, int xOffset, int yOffset){
		draw(pix, xOffset, yOffset);
		arrivals.draw(pix, xOffset, yOffset);
	}
}