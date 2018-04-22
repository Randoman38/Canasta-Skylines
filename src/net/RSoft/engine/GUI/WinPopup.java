package net.RSoft.engine.GUI;

import net.RSoft.engine.graphics.CharList;

public class WinPopup extends Popup {
	
	public ExitGame exit;
	
	public WinPopup(){
		super();
		
		title = new CharList(x+150, y, "You Win!!!", 2, 0x000000);
		text = new CharList(x+14, y, "", 1, 0x000000);
		
		lines = new String[]{"Congratulations! You did it!", "The district thanks you for your efforts in getting this city", "started, and will now relieve you of your position...", "", "    Maybe there's another project for you?"};
		exit = new ExitGame(x+(WIDTH/2)-10, y+270);
	}
	
	public void tick() {
		exit.tick();
	}
	
	public void render(int[][] pix, int xOffset, int yOffset) {
		title.y = y+12;
		exit.y = y+270;
		
		draw(pix, xOffset, yOffset);
		title.draw(pix, xOffset, yOffset);
		
		for(int i=1;i<lines.length;i++){
			text.setString(lines[i], 0x000000);
			text.y = y+60+i*8+i*3;
			
			text.draw(pix, xOffset, yOffset);
		}
		
		exit.draw(pix, xOffset, yOffset);
	}
}