package net.RSoft.engine.GUI;

import net.RSoft.engine.Main;

public class Help extends Button {
	
	public Help(int x, int y) {
		super(x, y, 24, 12, "GUI/Help", 4);
		
	}
	
	public void onPress() {
		Main.mn.menu.help = true;
	}
	
	public void cusTick() {
		if(contains(Main.mn.ms)){
			state = 1;
		}else{
			state = 0;
		}
	}
}