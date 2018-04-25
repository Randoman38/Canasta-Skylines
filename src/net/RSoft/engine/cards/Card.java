package net.RSoft.engine.cards;

import net.RSoft.engine.Main;
import net.RSoft.engine.GUI.Button;

public abstract class Card extends Button {
	
	public int index = -1, cost = 2;
	public int[] noBuild;
	
	public String[] desc;
	
	public Card(int x, int y, int width, int height, String url, int scale, int index, String[] desc, int cost, int[] noBuild) {
		super(x, y, width, height, url, scale);
		
		this.index = index;
		this.desc = desc;
		this.cost = cost;
		this.noBuild = noBuild;
	}
	
	public void onPress() {
		Main.mn.gm.building = true;
		Main.mn.gm.cardBuilding = index;
	}
	
	public void cusTick() {
		if(contains(Main.mn.ms)){
			state = 1;
			
			Main.mn.gm.cards.hovered = index;
		}else{
			state = 0;
		}
	}
	
	public abstract Card clone(int x, int y, int index);
	public abstract void build(int x, int y);
}