package net.RSoft.engine.map.building;

public class Farm extends Building {
	
	public Farm(int x, int y, int scale) {
		super(x, y, "Building/Farm", scale);
		
		food = 2;
	}
}