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

        ImageIcon img = new ImageIcon("images/ship1.png");
        spaceShip     = img.getImage();
        fire = true;
        this.x_pos = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
        this.y_pos = Toolkit.getDefaultToolkit().getScreenSize().height - spaceShip.getHeight(null) - 30;
        this.moveX = 3;
        this.moveY = 3;

        this.isAlive = true;

        blasterShots = new ArrayList();
        laserShots   = new ArrayList<Laser>();
    }
	public void setYPost(int yPost) {
		this.y_pos = yPost;
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

public void paint(Graphics2D g2) {
	   if (isAlive() ==true){
		   g2.drawImage(getImage(), getX(), getY(), null);
     } else {
     	setImage("images/explosion.gif");
     	g2.drawImage(getImage(), getX(), getY(), null);
     }
}

}
