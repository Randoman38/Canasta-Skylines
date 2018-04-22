package net.RSoft.engine.GUI;

import net.RSoft.engine.Main;

public class Return extends Button{
	
	public Return(int x, int y) {
		super(x, y, 12, 12, "GUI/Return", 4);
		
	}
	
	public void onPress() {
		Main.mn.menu.help = false;
		Main.mn.menu.about = false;
	}
	
	public void cusTick() {
		if(contains(Main.mn.ms)){
			state = 1;
		}else{
			state = 0;
		}
	}
}