package net.RSoft.engine.map.building;

public class TrashHeap extends Building {
	
	public TrashHeap(int x, int y, int scale) {
		super(x, y, "Building/Landfill", scale, new String[]{"Building/Harbour", "Building/Landfill"});
		
		gCap = 1;
	}

}