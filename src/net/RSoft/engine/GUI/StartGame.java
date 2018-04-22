package net.RSoft.engine.GUI;

import net.RSoft.engine.Main;

public class StartGame extends Button {
	
	public StartGame(int x, int y) {
		super(x, y, 25, 12, "GUI/StartGame", 4);
		
	}
	
	public void onPress() {
		Main.mn.mainMenu = false;
		Main.mn.startGame();
	}
	
	public void cusTick() {
		if(contains(Main.mn.ms)){
			state = 1;
		}else{
			state = 0;
		}
	}
}