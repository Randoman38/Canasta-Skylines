package net.RSoft.engine;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Cursor extends Point implements MouseListener{
	private static final long serialVersionUID = 1L;
	
	public boolean held = false, released = false, oReleased = false, pressBut = false;
	private int count = 1;

	public Cursor(){
		
	}
	
	public int[] getXY(){ return new int[] {x, y}; }
	
	public void tick(){
		x = (int) ((MouseInfo.getPointerInfo().getLocation().x - Main.mn.getX())*Main.RATIO);
		y = (int) ((MouseInfo.getPointerInfo().getLocation().y - Main.mn.getY())*Main.RATIO);
		//System.out.println(x + ", " + y);
		
		if(count <= 0){
			count = 1;
			released = false;
			oReleased = false;
			pressBut = false;
		}else if(released && count > 0){
			count--;
		}
	}
	
	public void mousePressed(MouseEvent e) {
		int button = e.getButton();
		
		if(button == MouseEvent.BUTTON1){
			held = true;
		}
	}

	public void mouseReleased(MouseEvent e) {
		int button = e.getButton();
		
		if(button == MouseEvent.BUTTON1){
			held = false;
			released = true;
		}else if(button == MouseEvent.BUTTON3){
			oReleased = true;
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}
}