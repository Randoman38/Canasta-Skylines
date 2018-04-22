package net.RSoft.engine.map;

public class PolderTile extends Tile {
	
	public PolderTile(int x, int y, int scale) {
		super(x, y, "Tile/Polder", scale);
		
	}
	
	public Tile clone(int x, int y, int scale) {
		return new PolderTile(x, y, scale);
	}
}