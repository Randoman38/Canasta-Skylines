package net.RSoft.engine.map.building;

public class CommercialHub extends Building {

	public CommercialHub(int x, int y, int scale) {
		super(x, y, "Building/CommerceDist", scale);
		
		gold = 2;
	}
}