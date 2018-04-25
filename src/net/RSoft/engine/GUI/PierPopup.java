package net.RSoft.engine.GUI;

import net.RSoft.engine.Main;
import net.RSoft.engine.cards.WaterHome;
import net.RSoft.engine.graphics.CharList;

public class PierPopup extends Popup {
	
	public Return exit;
	
	public PierPopup(){
		super();
		
		title = new CharList(x+150, y, "Pier Unlocked!", 2, 0x000000);
		text = new CharList(x+14, y, "", 1, 0x000000);
		
		lines = new String[]{"After having led the city for this long, the district feels", " you are ready for piers.", "", "Provides 2 housing.", "Can only be built on water.", "", "Cost: 4"};
		exit = new Return(x+WIDTH-24, y);
	}
	
	public void unlock(){
		Main.mn.gm.cards.deck.add(new WaterHome(0, 0, -1));
	}
	
	public void tick() {
		exit.tick();
	}
	
	public void cusRender(int[][] pix, int xOffset, int yOffset) {
		exit.y = y;
		
		exit.draw(pix, xOffset, yOffset);
	}
}