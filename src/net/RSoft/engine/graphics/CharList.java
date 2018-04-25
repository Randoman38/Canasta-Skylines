package net.RSoft.engine.graphics;

public class CharList extends Bitmap {
	
	public final int TILEWIDTH = 7, TILEHEIGHT = 8;
	private String[] chars = {"ABCDEFGHIJKLMNOPQRSTUVWXYZ",
							  "abcdefghijklmnopqrstuvwxyz",
							  "0123456789/,.-_!?:()'+="};
	
	public CharList(int x, int y, String str, int color){
		super(x, y, "Font");
		
		read(str, color);
	}
	
	public CharList(int x, int y, String str, int scale, int color){
		super(x, y, "Font", scale);
		
		read(str, color);
	}
	
	public void setString(String str){
		read(str, -1);
	}
	
	public void setString(String str, int c){
		read(str, c);
	}
	
	private void read(String str, int c){
		WIDTH = TILEWIDTH*str.length()*SCALE;
		HEIGHT = TILEHEIGHT*SCALE;
		
		pixels = new int[(WIDTH/SCALE)*(HEIGHT/SCALE)];
		
		for(int i=0;i<str.length();i++){
			int[] xy = matchChar(str.charAt(i));
			for(int x=0;x<TILEWIDTH;x++){
				for(int y=0;y<TILEHEIGHT;y++){
					if(bckup.getRGB(x+xy[0], y+xy[1]) == -1){
						pixels[((i*TILEWIDTH)+x)+(y*WIDTH/SCALE)] = c;
					}else{
						pixels[((i*TILEWIDTH)+x)+(y*WIDTH/SCALE)] = bckup.getRGB(x+xy[0], y+xy[1]);
					}
				}
			}
		}
	}
	
	private int[] matchChar(char c){
		for(int y=0;y<chars.length;y++){
			for(int x=0;x<chars[y].length();x++){
				if(c == chars[y].charAt(x)){
					return new int[] {x*TILEWIDTH, y*TILEHEIGHT};
				}
			}
		}
		
		return new int[] {175, 16};
	}
}