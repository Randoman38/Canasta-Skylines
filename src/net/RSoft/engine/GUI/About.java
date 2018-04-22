package net.RSoft.engine.GUI;

import net.RSoft.engine.Main;

public class About extends Button {
	
	public About(int x, int y) {
		super(x, y, 28, 12, "GUI/About", 4);
		
	}
	
	public void onPress() {
		Main.mn.menu.about = true;
	}
	
	public void cusTick() {
		if(contains(Main.mn.ms)){
			state = 1;
		}else{
			state = 0;
		}
	}
}