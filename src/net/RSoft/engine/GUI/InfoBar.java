package net.RSoft.engine.GUI;

import net.RSoft.engine.Main;
import net.RSoft.engine.graphics.Bitmap;
import net.RSoft.engine.graphics.CharList;

public class InfoBar extends Bitmap {
	
	public Bitmap pop, food, gold, energy, gCap, happy;
	public CharList popNum, foodNum, goldNum, eNum, gcNum, happyNum, date;

	public InfoBar(int x, int y) {
		super(x, y, "GUI/InfoBar", 2);
		
		pop = new Bitmap(x+8, y+8, "GUI/Pop", 2);
		popNum = new CharList(x+28, y+8, "100/100", 1, -1);
		
		gold = new Bitmap(x+90, y+8, "GUI/Gold", 2);
		goldNum = new CharList(x+110, y+8, "100/100", 1, -1);
		
		energy = new Bitmap(x+177, y+8, "GUI/Energy", 2);
		eNum = new CharList(x+197, y+8, "100", 1, -1);
		
		food = new Bitmap(x+225, y+8, "GUI/Food", 2);
		foodNum = new CharList(x+245, y+8, "100", 1, -1);
		
		gCap = new Bitmap(x+270, y+8, "GUI/Garbage", 2);
		gcNum = new CharList(x+290, y+8, "100", 1, -1);
		
		happy = new Bitmap(x+320, y+8, "GUI/Happiness", 2);
		happyNum = new CharList(x+340, y+8, "100", 1, -1);
		
		date = new CharList(x+540, y+8, "", -1, 1);
	}
	
	public void tick(){
		popNum.setString(Main.mn.gm.pop + "/" + Main.mn.gm.hoCap);
		goldNum.setString(Main.mn.gm.gold + "/" + Main.mn.gm.maxGold);
		eNum.setString(Main.mn.gm.energy + "");
		foodNum.setString(Main.mn.gm.food + "");
		gcNum.setString(Main.mn.gm.gCap + "");
		happyNum.setString(Main.mn.gm.happiness + "");
		date.setString(Main.mn.gm.months[Main.mn.gm.month] + Main.mn.gm.day + ", " + Main.mn.gm.year);
	}
	
	public void render(int[][] pix, int xOffset, int yOffset){
		draw(pix, xOffset, yOffset);
		
		pop.draw(pix, xOffset, yOffset);
		popNum.draw(pix, xOffset, yOffset);
		
		gold.draw(pix, xOffset, yOffset);
		goldNum.draw(pix, xOffset, yOffset);
		
		energy.draw(pix, xOffset, yOffset);
		eNum.draw(pix, xOffset, yOffset);
		
		food.draw(pix, xOffset, yOffset);
		foodNum.draw(pix, xOffset, yOffset);
		
		gCap.draw(pix, xOffset, yOffset);
		gcNum.draw(pix, xOffset, yOffset);
		
		happy.draw(pix, xOffset, yOffset);
		happyNum.draw(pix, xOffset, yOffset);
		
		date.draw(pix, xOffset, yOffset);
	}
}