package net.RSoft.engine.cards;

import net.RSoft.engine.Main;
import net.RSoft.engine.map.building.CommercialHub;

public class Commerce extends Card {
	
	public Commerce(int x, int y, int index) {
		super(x, y, 40, 22, "Card/CommerceDist", 2, index);
		
		desc = new String[]{"Commercial Hub", "Provides 2 gold."};
		cost = 2;
	}
	
	public Card clone(int x, int y, int index) {
		return new Commerce(x, y, index);
	}
	
	public void build(int x, int y) {
		if(Main.mn.gm.map.buildings[x][y] == null && Main.mn.gm.map.claimed[x][y]){
			Main.mn.gm.map.buildings[x][y] = new CommercialHub(Main.mn.gm.map.X + x*16*Main.mn.gm.map.SCALE, Main.mn.gm.map.Y + y*16*Main.mn.gm.map.SCALE, Main.mn.gm.map.SCALE);
			Main.mn.gm.gold -= Main.mn.gm.cards.cards[Main.mn.gm.cardBuilding].cost;
			Main.mn.gm.cards.cards[Main.mn.gm.cardBuilding] = null;
			Main.mn.gm.building = false;
			Main.mn.gm.cardBuilding = -1;
		}
	}
}