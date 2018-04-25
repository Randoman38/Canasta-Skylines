package net.RSoft.engine.cards;

import net.RSoft.engine.Main;

public class LandClaim extends Card {
	
	public LandClaim(int x, int y, int index) {
		super(x, y, 40, 22, "Card/LandClaim", 2, index, new String[]{"Land Claim", "Allows you to claim land to build on."}, 4, new int[]{0});
		
	}
	
	public Card clone(int x, int y, int index) {
		return new LandClaim(x, y, index);
	}

	public void build(int x, int y) {
		if(!Main.mn.gm.map.claimed[x][y]){
			Main.mn.gm.map.claimed[x][y] = true;
			Main.mn.gm.gold -= Main.mn.gm.cards.cards[Main.mn.gm.cardBuilding].cost;
			Main.mn.gm.cards.cards[Main.mn.gm.cardBuilding] = null;
			Main.mn.gm.firstClaim = true;
			Main.mn.gm.building = false;
			Main.mn.gm.cardBuilding = -1;
		}
	}

}
