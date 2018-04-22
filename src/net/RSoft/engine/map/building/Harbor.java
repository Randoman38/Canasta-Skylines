package net.RSoft.engine.map.building;

public class Harbor extends Building {

	public Harbor(int x, int y, int scale) {
		super(x, y, "Building/Harbour", scale);
		
		gold = 2;
	}
}