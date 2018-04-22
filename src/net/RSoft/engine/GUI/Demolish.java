package net.RSoft.engine.GUI;

import net.RSoft.engine.Main;

public class Demolish extends Button {
	
	public Demolish(int x, int y) {
		super(x, y, 12, 12, "GUI/Demolish", 2);
		
	}
	
	public void onPress() {
		Main.mn.gm.demolishing = true;
	}
	
	public void cusTick() {
		if(contains(Main.mn.ms)){
			state = 1;
		}else{
			state = 0;
		}
	}
}