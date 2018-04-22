package net.RSoft.engine.GUI;

import net.RSoft.engine.Main;

public class ExitGame extends Button {
	
	public ExitGame(int x, int y) {
		super(x, y, 20, 12, "GUI/ExitGame", 2);
		
	}
	
	public void onPress() {
		Main.mn.mainMenu = true;
		Main.mn.gm = null;
	}
	
	public void cusTick() {
		if(contains(Main.mn.ms)){
			state = 1;
		}else{
			state = 0;
		}
	}
}