package net.RSoft.engine.GUI;

import net.RSoft.engine.graphics.Bitmap;
import net.RSoft.engine.graphics.CharList;

public class MainMenu extends Bitmap {
	
	public boolean about = false, help = false;
	
	public String[] linesA, linesB;
	
	public Bitmap menu;
	
	public CharList title, text;
	
	public StartGame start;
	public About aboot;
	public Help helping;
	
	public Return back;
	
	public MainMenu() {
		super(0, 0, "GUI/MainMenu", 4);
		
		menu = new Bitmap(0, 0, "GUI/Menu", 4);
		
		title = new CharList(100, 10, "", 3, 0x000000);
		text = new CharList(0, 0, "", 1, 0x000000);
		
		start = new StartGame(150, 240);
		aboot = new About(250, 240);
		helping = new Help(362, 240);
		
		back = new Return(616, 4);
		
		linesA = new String[]{"Made in 48 hours for Ludum Dare.", "Theme: Combine 2 Incompatible Genres", "User: Randoman38"};
		linesB = new String[]{"The goal is to reach a population of 50 people with homes.", "", "More people come the happier your city is, and some will leave if they", "become too unhappy.", "All stats update at the end of the month, as that's when everything's done being built.", "Every building has an adjacency bonus (buildings of the same kind) of 0.5.", "Harbors provide a bonus to all buildings.", "", "On the bottom bar are the Sell Card and Demolish buttons.", "To sell, select a card as if to build and then select the Sell Button.", "You get half its build cost.", "To demolish, just select the button first, then the tile you want.", "It costs 4 to demolish.", "", "Good luck!"};
	}
	
	public void tick(){
		if(about){
			back.tick();
		}else if(help){
			back.tick();
		}else{
			start.tick();
			aboot.tick();
			helping.tick();
		}
	}
	
	public void render(int[][] pix, int xOffset, int yOffset){
		if(about){
			menu.draw(pix, xOffset, yOffset);
			
			title.setString("About:", 0x000000);
			title.draw(pix, xOffset, yOffset);
			
			for(int i=0;i<linesA.length;i++){
				text.setString(linesA[i], 0x000000);
				text.x = x+25;
				text.y = y+150+i*8+i*5;
				
				text.draw(pix, xOffset, yOffset);
			}
			
			back.draw(pix, xOffset, yOffset);
		}else if(help){
			menu.draw(pix, xOffset, yOffset);
			
			title.setString("Help:", 0x000000);
			title.draw(pix, xOffset, yOffset);
			
			for(int i=0;i<linesB.length;i++){
				text.setString(linesB[i], 0x000000);
				text.x = x+25;
				text.y = y+100+i*8+i*5;
				
				text.draw(pix, xOffset, yOffset);
			}
			
			back.draw(pix, xOffset, yOffset);
		}else{
			draw(pix, xOffset, yOffset);
			start.draw(pix, xOffset, yOffset);
			aboot.draw(pix, xOffset, yOffset);
			helping.draw(pix, xOffset, yOffset);
		}
	}
}