package net.RSoft.engine.cards;

import net.RSoft.engine.Main;

public class Upgrade extends Card {
	
	public Upgrade(int x, int y, int index) {
		super(x, y, 40, 22, "Card/Upgrade", 2, index, new String[]{"Upgrade", "Increases all outputs of selected tile", "by 1."}, 6, null);
	}
	
	public Card clone(int x, int y, int index) {
		return new Upgrade(x, y, index);
	}
	
	public void build(int x, int y) {
		if(Main.mn.gm.map.buildings[x][y] != null && !Main.mn.gm.map.buildings[x][y].upgraded){
			if(Main.mn.gm.map.buildings[x][y].gold > 0){
				Main.mn.gm.map.buildings[x][y].gold ++;
			}
			
			if(Main.mn.gm.map.buildings[x][y].hoCap > 0){
				Main.mn.gm.map.buildings[x][y].hoCap ++;
			}
			
			if(Main.mn.gm.map.buildings[x][y].gCap > 0){
				Main.mn.gm.map.buildings[x][y].gCap ++;
			}
			
			if(Main.mn.gm.map.buildings[x][y].energy > 0){
				Main.mn.gm.map.buildings[x][y].energy ++;
			}
			
			if(Main.mn.gm.map.buildings[x][y].food > 0){
				Main.mn.gm.map.buildings[x][y].food ++;
			}
			
			Main.mn.gm.map.buildings[x][y].upgraded = true;
			Main.mn.gm.gold -= Main.mn.gm.cards.cards[Main.mn.gm.cardBuilding].cost;
			Main.mn.gm.cards.cards[Main.mn.gm.cardBuilding] = null;
			Main.mn.gm.building = false;
			Main.mn.gm.cardBuilding = -1;
		}
	}
}