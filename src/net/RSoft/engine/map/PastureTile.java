package net.RSoft.engine.map;

public class PastureTile extends Tile {
	
	public PastureTile(int x, int y, int scale) {
		super(x, y, "Tile/Pasture", scale);
		
		id = 1;
	}
	
	public Tile clone(int x, int y, int scale) {
		return new PastureTile(x, y, scale);
	}
}