package net.RSoft.engine.cards;

import net.RSoft.engine.Main;
import net.RSoft.engine.map.building.TrashHeap;

public class Landfill extends Card {
	
	public Landfill(int x, int y, int index) {
		super(x, y, 40, 22, "Card/Landfill", 2, index);
		
		desc = new String[]{"Landfill", "Provides 1 trash capacity."};
		
		cost = 2;
	}
	
	public Card clone(int x, int y, int index) {
		return new Landfill(x, y, index);
	}
	
	public void build(int x, int y) {
		boolean b = false;
		
		if(y-1 >= 0 && Main.mn.gm.map.tiles[x][y-1].id != 0){
			b = true;
		}
		if(y+1 < Main.mn.gm.map.HEIGHT && Main.mn.gm.map.tiles[x][y+1].id != 0){
			b = true;
		}
		if(x-1 >= 0 && Main.mn.gm.map.tiles[x-1][y].id != 0){
			b = true;
		}
		if(x+1 < Main.mn.gm.map.WIDTH && Main.mn.gm.map.tiles[x+1][y].id != 0){
			b = true;
		}
		
		if(b && Main.mn.gm.map.buildings[x][y] == null && Main.mn.gm.map.claimed[x][y]){
			Main.mn.gm.map.buildings[x][y] = new TrashHeap(Main.mn.gm.map.X + x*16*Main.mn.gm.map.SCALE, Main.mn.gm.map.Y + y*16*Main.mn.gm.map.SCALE, Main.mn.gm.map.SCALE);
			Main.mn.gm.gold -= Main.mn.gm.cards.cards[Main.mn.gm.cardBuilding].cost;
			Main.mn.gm.cards.cards[Main.mn.gm.cardBuilding] = null;
			Main.mn.gm.building = false;
			Main.mn.gm.cardBuilding = -1;
		}
	}
}