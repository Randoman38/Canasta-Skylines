package net.RSoft.engine.GUI;

import java.util.ArrayList;
import java.util.Random;

import net.RSoft.engine.cards.*;
import net.RSoft.engine.graphics.Bitmap;
import net.RSoft.engine.graphics.CharList;

public class CardHolder extends Bitmap {
	
	public int hovered = -1;
	public CharList[] desc = {new CharList(x+18, y+253, "", 2, 0xffffff), new CharList(0, 0, "", 1, 0xffffff)};
	
	public ArrayList<Card> deck = new ArrayList<Card>();
	
	public Card[] cards;
	
	public CardHolder(int x, int y) {
		super(x, y, "GUI/Cards", 2);
		
		cards = new Card[15];
		
		for(int i=0;i<cards.length;i++){
			cards[i] = null;
		}
		
		//deck.add(new (0, 0, -1));
		
		deck.add(new House(0, 0, -1));
		deck.add(new LandClaim(0, 0, -1));
		deck.add(new Harbour(0, 0, -1));
		deck.add(new Landfill(0, 0, -1));
		deck.add(new Turbines(0, 0, -1));
		deck.add(new Crops(0, 0, -1));
		deck.add(new Commerce(0, 0, -1));
		deck.add(new Upgrade(0, 0, -1));
		deck.add(new Polder(0, 0, -1));
		
		addCard(1);
		addCard(0);
		monthlyCards(3);
	}
	
	public void addCard(int ind){
		for(int i=0;i<cards.length;i++){
			if(cards[i] == null){
				cards[i] = deck.get(ind).clone(x+8+(i%3)*80+(i%3)*16, y+((i-(i%3))/3)*44+((i-(i%3))/3)*6, i);
				break;
			}
		}
	}
	
	public void monthlyCards(int num){
		for(int i=0;i<num;i++){
			int ind = new Random().nextInt(deck.size());
			
			addCard(ind);
		}
	}
	
	public void tick(){
		hovered = -1;
		
		for(int i=0;i<cards.length;i++){
			if(cards[i] != null){
				cards[i].tick();
			}
		}
	}
	
	public void render(int pix[][], int xOffset, int yOffset){
		draw(pix, xOffset, yOffset);
		
		for(int i=0;i<cards.length;i++){
			if(cards[i] != null){
				cards[i].draw(pix, xOffset, yOffset);
			}
		}
		
		if(hovered > -1 && cards[hovered] != null){
			desc[0].setString(cards[hovered].desc[0]);
		}else{
			desc[0].setString("None selected.");
		}
		
		desc[0].draw(pix, xOffset, yOffset);
		
		if(hovered > -1 && cards[hovered] != null){
			for(int i=1;i<cards[hovered].desc.length;i++){
				desc[1].setString(cards[hovered].desc[i]);
				desc[1].x = x+12;
				desc[1].y = y+269+i*8+i*3;
				
				desc[1].draw(pix, xOffset, yOffset);
			}
		}
	}
}
