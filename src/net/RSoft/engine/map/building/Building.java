package net.RSoft.engine.map.building;

import net.RSoft.engine.graphics.Bitmap;

public abstract class Building extends Bitmap {
	
	public Bitmap upgrade;
	public boolean upgraded = false;
	
	public String id = "";
	public String[] adj;
	
	public int gold = 0, hoCap = 0, gCap = 0, energy = 0, food = 0, bonuses = 0;
	
	public Building(int x, int y, String url, int scale, String[] adj) {
		super(x, y, url, scale);
		
		id = url;
		this.adj = adj;
		upgrade = new Bitmap(x, y, "GUI/Upgraded", scale);
	}
	
	public void render(int[][] pix, int xOffset, int yOffset){
		draw(pix, xOffset, yOffset);
		
		if(upgraded){
			upgrade.draw(pix, xOffset, yOffset);
		}
	}
}