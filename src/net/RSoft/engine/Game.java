package net.RSoft.engine;

import java.util.Random;

import net.RSoft.engine.GUI.*;
import net.RSoft.engine.map.Map;

public class Game {
	
	public int[][] pixels;
	
	public String[] months = {"Jan.", "Feb.", "Mar.", "Apr.", "May.", "Jun.", "July ", "Aug.", "Sept.", "Oct.", "Nov.", "Dec."};
	public int[] monthLengths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	public int maxCount = 20, count = maxCount, day = 1, month = 0, year = 2018, monthCount = 0;
	
	public int gold = 12, maxGold = 100, pop = 2, arrived = 0, hoCap = 0, gCap = 0, energy = 0, food = 0, happiness = generateHappiness();
	
	public boolean building = false, demolishing = false;
	public int cardBuilding = -1, selX = 0, selY = 0;
	
	public InfoBar infoBar;
	public ReportBar report;
	public CardHolder cards;
	public Map map;
	
	public Popup event = null;
	
	public SellButton sell;
	public Demolish demolish;
	
	public Game(int width, int height){
		pixels = new int[width][height];
		
		infoBar = new InfoBar(0, 4);
		report = new ReportBar(0, 332);
		cards = new CardHolder(352, 44);
		map = new Map(0, 44, 11, 9, 2);
		
		sell = new SellButton(322, 332);
		demolish = new Demolish(298, 332);
	}
	
	public void tickMonth(){
		gold += pop + 2;
		
		hoCap = 0;
		gCap = 0;
		energy = 0;
		food = 0;
		
		map.applyAdjacencies();
		
		for(int x=0;x<map.WIDTH;x++){
			for(int y=0;y<map.HEIGHT;y++){
				if(map.buildings[x][y] != null){
					gold += map.buildings[x][y].gold;
					if(gold > maxGold){
						gold = maxGold;
					}
					
					if(map.buildings[x][y].hoCap > 0){
						hoCap += map.buildings[x][y].hoCap + map.buildings[x][y].bonuses;
					}
					
					if(map.buildings[x][y].gCap > 0){
						gCap += map.buildings[x][y].gCap + map.buildings[x][y].bonuses;
					}
					
					if(map.buildings[x][y].energy > 0){
						energy += map.buildings[x][y].energy + map.buildings[x][y].bonuses;
					}
					
					if(map.buildings[x][y].food > 0){
						food += map.buildings[x][y].food + map.buildings[x][y].bonuses;
					}
				}
			}
		}
		
		cards.monthlyCards(4);
		
		happiness = generateHappiness();
		arrived = visitation();
		
		report.updateBar(arrived);
		
		if(hoCap >= 50 && pop >= 50){
			event = new WinPopup();
		}
		
		if(monthCount > 4){
			if(pop <= 3){
				event = new LosePopup();
			}
		}
	}
	
	public int visitation(){
		int add = new Random().nextInt(9), added = 0,
			sub = 0, total = 0;
		
		for(int i=0;i<add;i++){
			if(pop<hoCap){
				pop++;
				added++;
			}else{
				pop++;
				added++;
				break;
			}
		}
		
		if(monthCount > 1){
			if(happiness < 50){
				if(happiness < 25){
					sub = new Random().nextInt(4)+1;
				}else{
					sub = new Random().nextInt(3);
				}
			}
		}
		
		pop -= sub;
		total = added - sub;
		
		if(pop < 0){
			while(pop < 0){
				pop++;
				total--;
			}
		}
		
		return total;
	}
	
	public int generateHappiness(){
		int hc = 0, gc = 0, en = 0, fd = 0, total = 0;
		
		if(hoCap > pop){
			hc = 100;
		}else if(hoCap == pop){
			hc = 85;
		}else{
			hc = (int) (hoCap/pop)-15;
			
			if(hc < 0){
				hc = 0;
			}
		}
		
		if(gCap > pop){
			gc = 100;
		}else if(gCap == pop){
			gc = 85;
		}else{
			gc = (int) (gCap/pop)-15;
			
			if(gc < 0){
				gc = 0;
			}
		}
		
		if(energy > pop){
			en = 100;
		}else if(energy == pop){
			en = 85;
		}else{
			en = (int) (energy/pop)-15;
			
			if(en < 0){
				en = 0;
			}
		}
		
		if(food > pop){
			fd = 100;
		}else if(food == pop){
			fd = 85;
		}else{
			fd = (int) (food/pop)-15;
			
			if(fd < 0){
				fd = 0;
			}
		}
		
		total = (hc+gc+en+fd)/4;
		
		return total;
	}
	
	public void tick(){
		if(event == null){
			if(!building && !demolishing){
				count--;
				if(count <= 0){
					count = maxCount;
					day++;
					
					if(day>monthLengths[month]){
						day = 1;
						month++;
						monthCount++;
						
						if(month>=months.length){
							month = 0;
							year++;
						}
						
						tickMonth();
					}
				}
				
				infoBar.tick();
				report.tick();
				map.tick();
				cards.tick();
				demolish.tick();
			}else if(building){
				if(Main.mn.ms.released && map.isSel){
						
					if(cards.cards[cardBuilding].noBuild != null){
						int noMatches = 0;
						
						for(int i=0;i<cards.cards[cardBuilding].noBuild.length;i++){
							if(map.tiles[selX][selY].id != cards.cards[cardBuilding].noBuild[i]){
								noMatches++;
							}
						}
						
						if(noMatches == cards.cards[cardBuilding].noBuild.length && cards.cards[cardBuilding].cost <= gold){
							cards.cards[cardBuilding].build(selX, selY);
						}
					}else{
						if(cards.cards[cardBuilding].cost <= gold){
							cards.cards[cardBuilding].build(selX, selY);
						}
							
					}
				}
				
				if(Main.mn.ms.oReleased){
					building = false;
					cardBuilding = -1;
				}
				
				sell.tick();
			}else if(demolishing){
				if(Main.mn.ms.released && map.isSel){
					if(map.buildings[selX][selY] != null){
						if(gold >= 4){
							gold -= 4;
							map.buildings[selX][selY] = null;
							demolishing = false;
						}
					}
				}
				
				if(Main.mn.ms.oReleased){
					demolishing = false;
				}
			}
		}else{
			if(event.popping){
				event.pop();
			}else{
				event.tick();
			}
		}
	}
	
	public void render(int[][] pix){
		infoBar.render(pixels, 0, 0);
		report.render(pixels, 0, 0);
		map.render(pixels, 0, 0);
		cards.render(pixels, 0, 0);
		
		sell.draw(pixels, 0, 0);
		demolish.draw(pixels, 0, 0);
		
		if(event != null){
			event.render(pixels, 0, 0);
		}
		
		for(int x=0;x<pix.length;x++){
			for(int y=0;y<pix[0].length;y++){
				pix[x][y] = pixels[x][y];
			}
		}
	}
}