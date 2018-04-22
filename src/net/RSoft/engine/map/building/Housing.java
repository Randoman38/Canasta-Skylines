package net.RSoft.engine.map.building;

public class Housing extends Building{

	public Housing(int x, int y, int scale) {
		super(x, y, "Building/Housing", scale);
		
		hoCap = 2;
	}

}