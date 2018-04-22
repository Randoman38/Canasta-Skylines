package net.RSoft.engine.map;

public class WaterTile extends Tile {

	public WaterTile(int x, int y, int scale) {
		super(x, y, "Tile/Water", scale);
		
		id = 0;
	}

	public Tile clone(int x, int y, int scale) {
		return new WaterTile(x, y, scale);
	}
}