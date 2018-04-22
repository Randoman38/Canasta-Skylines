package net.RSoft.engine.map.building;

public class WindFarm extends Building {
	
	public WindFarm(int x, int y, int scale) {
		super(x, y, "Building/WindFarm", scale);
		
		energy = 2;
	}
}