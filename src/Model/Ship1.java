package Model;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Controller.Game;

public class Ship1 extends PlayerShip {
	public Ship1(String nullContrucstor) {
		
	}
	public Ship1(){

        ImageIcon img = new ImageIcon("images/spacecraft.png");
        spaceShip     = img.getImage();
        fire = true;
       
        this.x_pos = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
        this.y_pos = Toolkit.getDefaultToolkit().getScreenSize().height - spaceShip.getHeight(null) - 30;
        this.moveX = 3;
        this.moveY = 3;
        this.moveYdead = 2;

        this.isAlive = true;

        blasterShots = new ArrayList();
        laserShots   = new ArrayList<Laser>();
    }
	@Override
	public void setWeapon1(IWeapon weapon1) {
		 blasterShots.add(weapon1);
         blasterDelay = 20;
	}

	@Override
	public void setWeapon2(IWeapon weapon1) {
		 Laser laser = new Laser(x_pos -20, y_pos-10);
		 
		 laserDelay=100 ;
	        while (laserShots.size() < 2){
	            laserShots.add(laser);
	        }	
	        
	}
	public void setWeapon2Laser() {
		Laser laser = new Laser(x_pos -20, y_pos-10);
		
		laserDelay=100 ;
		while (laserShots.size() < 2){
			laserShots.add(laser);
		}		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		 if (e.getKeyCode() == KeyEvent.VK_LEFT){
	            keyLeft = true;
	        }
	        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
	            keyRight = true;
	        }
	        if (e.getKeyCode() == KeyEvent.VK_UP){
	            keyUp = true;
	        }
	        if (e.getKeyCode() == KeyEvent.VK_DOWN){
	            keyDown = true;
	        }
	        if (e.getKeyCode() == KeyEvent.VK_Q){
	            
	            	setFire(true); 

	        }
	        if (e.getKeyCode() == KeyEvent.VK_W){
	            specialWeapon = true;
	            if(laserDelay!=0) {
	            	
	            	Game.bigBagShot.play();
	            }
	        }		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		 if (e.getKeyCode() == KeyEvent.VK_LEFT){
	            keyLeft = false;
	        }
	        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
	            keyRight = false;
	        }
	        if (e.getKeyCode() == KeyEvent.VK_UP){
	            keyUp = false;
	        }
	        if (e.getKeyCode() == KeyEvent.VK_DOWN){
	            keyDown = false;
	        }
	        if (e.getKeyCode() == KeyEvent.VK_Q){
	            setFire(false);
	        }
	        if (e.getKeyCode() == KeyEvent.VK_W){
	            specialWeapon = false;
	        }			
	}

public void paint(Graphics2D g2) {
	   if (isAlive()){
		   g2.drawImage(getImage(), getX(), getY(), null);
     } else {
     	setImage("images/explosion.gif");
     	g2.drawImage(getImage(), getX(), getY(), null);
     }
}

}
