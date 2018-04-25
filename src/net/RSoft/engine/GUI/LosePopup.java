package net.RSoft.engine.GUI;

import net.RSoft.engine.graphics.CharList;

public class LosePopup extends Popup {

public ExitGame exit;
	
	public LosePopup(){
		super();
		
		title = new CharList(x+150, y, "You Lose...", 2, 0x000000);
		text = new CharList(x+14, y, "", 1, 0x000000);
		
		lines = new String[]{"Maybe luck just wasn't on your side?", "The district had the highest faith in your ability, but has", "unfortunately decided to remove you from the planning", "committee.", "", "    Maybe there's another project for you?"};
		exit = new ExitGame(x+(WIDTH/2)-10, y+270);
	}
	
	public void unlock(){
		
	}
	
	public void tick() {
		exit.tick();
	}
	
	public void cusRender(int[][] pix, int xOffset, int yOffset) {
		exit.y = y+270;
		
		exit.draw(pix, xOffset, yOffset);
	}
}