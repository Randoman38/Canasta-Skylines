package net.RSoft.engine.GUI;

import net.RSoft.engine.Main;

public class SellButton extends Button {
	
	public SellButton(int x, int y) {
		super(x, y, 12, 12, "GUI/SellButton", 2);
		
	}
	
	public void onPress() {
		if(Main.mn.gm.building){
			Main.mn.gm.building = false;
			
			Main.mn.gm.gold += Main.mn.gm.cards.cards[Main.mn.gm.cardBuilding].cost/2;
			
			if(Main.mn.gm.gold > Main.mn.gm.maxGold){
				Main.mn.gm.gold = Main.mn.gm.maxGold;
			}
			
			Main.mn.gm.cards.cards[Main.mn.gm.cardBuilding] = null;
			
			Main.mn.gm.cardBuilding = -1;
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