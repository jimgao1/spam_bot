import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;


public class KeyboardThread extends Thread {
	
	String text;
	int mode;
	int duration;
	
	boolean running;
	
	public KeyboardThread(){
		this.running = false;
	}
	
	public void updateSettings(String t, int m, int d){
		this.text = t;
		this.mode = m;
		this.duration = d;
	}
	
	public void setState(boolean r){
		this.running = r;
	}
	
	public boolean EnterText(){
		try{
			Robot r = new Robot();
			int len = this.text.length();
			
			for (int i=0; i<len; i++){
				r.keyPress(KeyEvent.getExtendedKeyCodeForChar(text.charAt(i)));
				r.keyRelease(KeyEvent.getExtendedKeyCodeForChar(text.charAt(i)));
				Thread.sleep(10);
			}
			
			r.keyPress(10);
			r.keyRelease(10);
			
			
			return true;
		} catch (AWTException ex){
			return false;
		} catch (InterruptedException e) {
			return false;
		}
	}
	
	
	public void run(){
		for (int i=0; i<duration; i++){
			EnterText();
		}
	}
	
	
}
