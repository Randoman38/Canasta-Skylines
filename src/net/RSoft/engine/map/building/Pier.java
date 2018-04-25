package net.RSoft.engine.map.building;

public class Pier extends Building {
	
	public Pier(int x, int y, int scale) {
		super(x, y, "Building/Pier", scale, new String[]{"Building/Harbour", "Building/Pier", "Building/Housing"});

		hoCap = 2;
	}
}