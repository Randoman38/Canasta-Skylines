package net.RSoft.engine.graphics;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

public class Bitmap {
	
	public int WIDTH, HEIGHT, SCALE = 1;
	public int x, y;
	public int[] pixels;
	
	protected BufferedImage bckup;
	
	public Bitmap(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.WIDTH = width;
		this.HEIGHT = height;
		pixels = new int[width*height];
		
		SCALE = 1;
		
		for(int i=0;i<pixels.length;i++){
			pixels[i] = new Random().nextInt();
		}
	}
	
	public Bitmap(int x, int y, int width, int height, int scale){
		this.x = x;
		this.y = y;
		this.WIDTH = width*scale;
		this.HEIGHT = height*scale;
		pixels = new int[width*height];
		
		if(scale>0){
			SCALE = scale;
		}else{
			SCALE = 1;
		}
		
		for(int i=0;i<pixels.length;i++){
			pixels[i] = new Random().nextInt();
		}
	}
	
	public Bitmap(int x, int y, String url){
		this.x = x;
		this.y = y;
		
		SCALE = 1;
		try{
			bckup = ImageIO.read(new File("res/" + url + ".png"));
		}catch(Exception e){ }
		
		WIDTH = bckup.getWidth();
		HEIGHT = bckup.getHeight();
		
		read();
	}
	
	public Bitmap(int x, int y, String url, int scale){
		this.x = x;
		this.y = y;
		
		if(scale>0){
			SCALE = scale;
		}else{
			SCALE = 1;
		}
		
		try{
			bckup = ImageIO.read(new File("res/" + url + ".png"));
		}catch(Exception e){ }
		
		WIDTH = bckup.getWidth()*SCALE;
		HEIGHT = bckup.getHeight()*SCALE;
		
		read();
	}
	
	protected void read(){
		pixels = new int[WIDTH/SCALE*HEIGHT/SCALE];
		for(int i=0;i<pixels.length;i++){
			pixels[i] = bckup.getRGB(i%(WIDTH/SCALE), (i-(i%(WIDTH/SCALE)))/(WIDTH/SCALE));
		}
	}
	
	protected void read(int xOffs, int yOffs){
		for(int i=0;i<pixels.length;i++){
			pixels[i] = bckup.getRGB(i%(WIDTH/SCALE)+xOffs, (i-(i%(WIDTH/SCALE)))/(WIDTH/SCALE)+yOffs);
		}
	}
	
	public boolean contains(Point p){
		if(p.x >= x && p.x < x+WIDTH){
			if(p.y >= y && p.y < y+HEIGHT+SCALE){
				return true;
			}
		}
		return false;
	}
	
	public static void fill(Bitmap bm, int c){
		for(int i=0;i<bm.pixels.length;i++){
			
			bm.pixels[i] = c;
		}
	}
	
	public static void fill(Bitmap bm, int startInd, int endInd, int c){
		if(startInd<0){
			startInd = 0;
		}
		
		for(int i=startInd;i<endInd;i++){
			if(i>=bm.pixels.length){
				break;
			}
			bm.pixels[i] = c;
		}
	}
	
	public void tick(){
		
	}
	
	public void draw(int[][] pix, int xOffset, int yOffset){
		for(int xOff=this.x;xOff+xOffset<x+xOffset+WIDTH;xOff++){
			for(int yOff=this.y;yOff+yOffset<y+yOffset+HEIGHT;yOff++){
				if(xOff+xOffset<pix.length && xOff+xOffset>0){
					if(yOff+yOffset<pix[xOff+xOffset].length && yOff+yOffset>0){
						int i = (int) Math.floor((((xOff-this.x)/SCALE)+(((yOff-this.y)/SCALE)*(WIDTH/SCALE))));
						if(pixels[i] != 0xffe987ff){ //RGB: 233, 135, 255
							pix[xOff+xOffset][yOff+yOffset] = pixels[i];
						}
					}
				}
			}
		}
	}
	
	public void draw(int[][] pix, int xOffset, int yOffset, int x, int y, int width, int height){
		if((width+x > 0 && width+x < WIDTH) && (height+y > 0 && height+y < HEIGHT)){
			for(int xOff=this.x+x;xOff+xOffset<this.x+x+xOffset+width;xOff++){
				for(int yOff=this.y+y;yOff+yOffset<this.y+y+yOffset+height;yOff++){
					if(xOff+xOffset<pix.length && xOff+xOffset>0){
						if(yOff+yOffset<pix[xOff+xOffset].length && yOff+yOffset>0){
							int i = (int) Math.floor((((xOff-this.x)/SCALE)+(((yOff-this.y)/SCALE)*(WIDTH/SCALE))));
							if(pixels[i] != 0xffe987ff){ //RGB: 233, 135, 255
								pix[xOff+xOffset][yOff+yOffset] = pixels[i];
							}
						}
					}
				}
			}
		}else{
			draw(pix, xOffset, yOffset);
		}
	}
}