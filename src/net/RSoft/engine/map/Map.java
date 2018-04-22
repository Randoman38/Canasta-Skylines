package net.RSoft.engine.map;

import java.util.Random;

import net.RSoft.engine.Main;
import net.RSoft.engine.graphics.Bitmap;
import net.RSoft.engine.map.building.Building;

public class Map {
	
	public Bitmap sel, owned;
	public boolean isSel = false;
	
	public Tile[] templates = {new WaterTile(0, 0, 0), new PastureTile(0, 0, 0), new PolderTile(0, 0, 0)};
	
	public boolean[][] claimed;
	public Tile[][] tiles;
	public Building[][] buildings;
	
	public final int WIDTH, HEIGHT, SCALE;
	public final int X, Y;
	
	public Map(int x, int y, int width, int height, int scale){
		WIDTH = width;
		HEIGHT = height;
		SCALE = scale;
		
		X = x;
		Y = y;
		
		sel = new Bitmap(0, 0, "GUI/Selected", 2);
		owned = new Bitmap(0, 0, "GUI/Owned", 2);
		
		claimed = new boolean[width][height];
		tiles = new Tile[width][height];
		buildings = new Building[width][height];
		
		for(int xo=0;xo<WIDTH;xo++){
			for(int yo=0;yo<HEIGHT;yo++){
				buildings[xo][yo] = null;
			}
		}
		
		for(int xo=0;xo<WIDTH;xo++){
			for(int yo=0;yo<HEIGHT;yo++){
				claimed[xo][yo] = false;
			}
		}
		
		generate();
	}
	
	public void generate(){
		int[][] temp = new int[WIDTH][HEIGHT];
		
		for(int x=0;x<WIDTH;x++){
			for(int y=0;y<HEIGHT;y++){
				temp[x][y] = 0;
			}
		}
		
		int loops = new Random().nextInt(3)+3;
		for(int i=0;i<loops;i++){
			boolean b = true;
			
			do{
				int x = new Random().nextInt(WIDTH),
					y = new Random().nextInt(HEIGHT);
				if(temp[x][y] == 0){
					temp[x][y] = 1;
					b = false;
				}
			}while(b);
		}
		
		loops = new Random().nextInt(5)+4;
		for(int i=0;i<loops;i++){
			for(int x=0;x<WIDTH;x++){
				for(int y=0;y<HEIGHT;y++){
					if(temp[x][y] == 1){
						switch(new Random().nextInt(4)){
						case 0:
							if(y-1 >= 0){
								temp[x][y-1] = 1;
							}
							break;
						case 1:
							if(y+1 < HEIGHT){
								temp[x][y+1] = 1;
							}
							break;
						case 2:
							if(x-1 >= 0){
								temp[x-1][y] = 1;
							}
							break;
						case 3:
							if(x+1 < WIDTH){
								temp[x+1][y] = 1;
							}
							break;
						}
					}
				}
			}
		}
		
		for(int x=0;x<WIDTH;x++){
			for(int y=0;y<HEIGHT;y++){
				tiles[x][y] = templates[temp[x][y]].clone(X+x*SCALE*16, Y+y*SCALE*16, SCALE);
			}
		}
	}
	
	public void applyAdjacencies(){
		for(int x=0;x<WIDTH;x++){
			for(int y=0;y<HEIGHT;y++){
				if(buildings[x][y] != null){
					int i = 0;
					
					if((y-1 >= 0 && buildings[x][y-1] != null) && (buildings[x][y-1].id == buildings[x][y].id || buildings[x][y-1].id == "Building/Harbour")){
						i++;
					}
					
					if((y+1 < HEIGHT && buildings[x][y+1] != null) && (buildings[x][y+1].id == buildings[x][y].id || buildings[x][y+1].id == "Building/Harbour")){
						i++;
					}
					if((x-1 >= 0 && buildings[x-1][y] != null) && (buildings[x-1][y].id == buildings[x][y].id || buildings[x-1][y].id == "Building/Harbour")){
						i++;
					}
					if((x+1 < WIDTH && buildings[x+1][y] != null) && (buildings[x+1][y].id == buildings[x][y].id || buildings[x+1][y].id == "Building/Harbour")){
						i++;
					}
					
					if(i > 0){
						buildings[x][y].bonuses = (int) (i/2);
					}
				}
			}
		}
	}
	
	public void tick(){
		for(int x=0;x<WIDTH;x++){
			for(int y=0;y<HEIGHT;y++){
				tiles[x][y].tick();
			}
		}
		
		for(int x=0;x<WIDTH;x++){
			for(int y=0;y<HEIGHT;y++){
				if(y-1 >= 0 && buildings[x][y-1] != null){
					claimed[x][y] = true;
				}
				if(y+1 < HEIGHT && buildings[x][y+1] != null){
					claimed[x][y] = true;
				}
				if(x-1 >= 0 && buildings[x-1][y] != null){
					claimed[x][y] = true;
				}
				if(x+1 < WIDTH && buildings[x+1][y] != null){
					claimed[x][y] = true;
				}
			}
		}
	}
	
	public void render(int[][] pix, int xOffset, int yOffset){
		for(int x=0;x<WIDTH;x++){
			for(int y=0;y<HEIGHT;y++){
				tiles[x][y].draw(pix, xOffset, yOffset);
			}
		}
		
		for(int x=0;x<WIDTH;x++){
			for(int y=0;y<HEIGHT;y++){
				if(buildings[x][y] != null){
					buildings[x][y].render(pix, xOffset, yOffset);
				}
			}
		}
		
		isSel = false;
		if(Main.mn.gm.building || Main.mn.gm.demolishing){
			for(int x=0;x<WIDTH;x++){
				for(int y=0;y<HEIGHT;y++){
					if(claimed[x][y] && buildings[x][y] == null && !Main.mn.gm.demolishing){
						owned.x = tiles[x][y].x;
						owned.y = tiles[x][y].y;
						owned.draw(pix, xOffset, yOffset);
					}
					
					if(tiles[x][y].contains(Main.mn.ms)){
						sel.x = tiles[x][y].x;
						sel.y = tiles[x][y].y;
						sel.draw(pix, 0, 0);
						
						Main.mn.gm.selX = x;
						Main.mn.gm.selY = y;
						
						isSel = true;
					}
				}
			}
		}
	}
}