import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
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
			
			/*
			 * Storing the information in the system clipboard
			 */
			StringSelection sel = new StringSelection(text);
			Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
			cb.setContents(sel, sel);
			
			/*
			 * Inputting the CTRL-V combination
			 * Pasting the text
			 */
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_Z);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyPress(10);
			r.keyRelease(10);
			
			Thread.sleep(200);
			
			
			return true;
		} catch (AWTException ex){
			return false;
		} catch (InterruptedException e) {
			return false;
		}
	}
	
	
	public void run(){
		for (int i=0; i<duration; i++){
			if (running){
				if (!EnterText()){
					System.out.println("DEBUG-Keyboard Error");
				}
			}
		}
	}
	
	
}
