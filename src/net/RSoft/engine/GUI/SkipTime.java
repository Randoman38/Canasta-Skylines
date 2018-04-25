package net.RSoft.engine.GUI;

import net.RSoft.engine.Main;

public class SkipTime extends Button {
	
	public SkipTime(int x, int y) {
		super(x, y, 5, 20, "GUI/SkipTime", 2);
		
	}
	
	public void onPress() {
		if(Main.mn.gm.firstClaim && Main.mn.gm.day < Main.mn.gm.monthLengths[Main.mn.gm.month]-2){
			Main.mn.gm.day = Main.mn.gm.monthLengths[Main.mn.gm.month]-2;
		}
	}
	
	public void cusTick() {
		if(contains(Main.mn.ms)){
			state = 1;
		}else{
			state = 0;
		}
	}
}