package net.RSoft.engine.map;

import net.RSoft.engine.graphics.Bitmap;

public abstract class Tile extends Bitmap {
	
	public int id = -1;
	
	public Tile(int x, int y, String url, int scale) {
		super(x, y, url, scale);
		
	}
	
	public abstract Tile clone(int x, int y, int scale);
}