package net.RSoft.engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import net.RSoft.engine.GUI.MainMenu;

public class Main extends JFrame implements Runnable{
	private static final long serialVersionUID = 1L;
	
	private boolean isRunning = false;
	public boolean mainMenu = true;
	
	private String NAME = "Canasta: Skylines";
	
	public static int WIDTH, HEIGHT, TWIDTH, THEIGHT;
	public static double RATIO;
	public int[][] pixels;
	
	public Thread thread;
	public BufferedImage scr;
	
	public static Main mn;
	public Cursor ms;
	
	public MainMenu menu;
	public Game gm;
	
	public static void main(String[] args){
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		mn = new Main(640, 360, size);
	}
	
	public Main(int w, int h, Dimension size){
		WIDTH = w;
		HEIGHT = h;
		
		TWIDTH = size.width;
		THEIGHT = size.height;
		
		RATIO = (((double) w/(double) TWIDTH)+((double) h/(double) THEIGHT))/2;
		
		pixels = new int[WIDTH][HEIGHT];
		
		thread = new Thread(this);
		
		ms = new Cursor();
		
		menu = new MainMenu();
		gm = new Game(WIDTH, HEIGHT);
		
		addKeyListener(new InputHandler());
		addMouseListener(ms);
		
		setSize(size.width, size.height);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(NAME);
		
		start();
	}
	
	public void start(){
		isRunning = true;
		thread.start();
	}
	
	public void startGame(){
		gm = new Game(WIDTH, HEIGHT);
	}
	
	public void tick(){
		ms.tick();
		
		if(mainMenu){
			menu.tick();
		}else{
			gm.tick();
		}
	}
	
	public void render(){
		Graphics g = scr.getGraphics();
		
		for(int x=0;x<pixels.length;x++){
			for(int y=0;y<pixels[x].length;y++){
				pixels[x][y] = -1;
			}
		}
		
		if(mainMenu){
			menu.render(pixels, 0, 0);
		}else{
			gm.render(pixels);
		}
		
		for(int x=0;x<pixels.length;x++){
			for(int y=0;y<pixels[x].length;y++){
				scr.setRGB(x, y, pixels[x][y]);
			}
		}
		
		g = getGraphics();
		//min y 3, max 26; range 23
		g.drawImage(scr, 3, 14, TWIDTH, THEIGHT, null);
		g.dispose();
	}
	
	public void run() {
		scr = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
		
		while(isRunning){
			
			tick();
			render();
			
			try{
				Thread.sleep(20);
			}catch(Exception e){ }
		}
	}
}