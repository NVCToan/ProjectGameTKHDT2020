package Model;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Controller.Game;

public class Ship2 extends PlayerShip {

	public Ship2(){

        ImageIcon img = new ImageIcon("images/playerTwo.png");
        spaceShip     = img.getImage();

        this.x_pos = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 300;
        this.y_pos = Toolkit.getDefaultToolkit().getScreenSize().height - spaceShip.getHeight(null) - 30;
        this.moveX = 3;
        this.moveY = 3;
        this.moveYdead = 2;

        this.isAlive = false;

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
		 Laser laser = new Laser(x_pos, y_pos);

	        while (laserShots.size() < 2){
	            laserShots.add(laser);
	        }		
	}
	@Override
	public void keyPressed(KeyEvent e) {

		        if (e.getKeyCode() == KeyEvent.VK_A){
		            keyLeft = true;
		        }
		        if (e.getKeyCode() == KeyEvent.VK_D){
		            keyRight = true;
		        }
		        if (e.getKeyCode() == KeyEvent.VK_W){
		            keyUp = true;
		        }
		        if (e.getKeyCode() == KeyEvent.VK_S){
		            keyDown = true;
		        }
		        if (e.getKeyCode() == KeyEvent.VK_SPACE){
		            fire = true;
		        }
		        if (e.getKeyCode() == KeyEvent.VK_G){
		            specialWeapon = true;
		        }

		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A){
            keyLeft = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D){
            keyRight = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_W){
            keyUp = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S){
            keyDown = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            fire = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_G){
            specialWeapon = false;
        }		
	}

public void paint(Graphics2D g2) {
   	
    if (isAlive()){
        g2.drawImage(getImage(),getX(), getY(), null);
    } else if (!isAlive() && Game.keyTwoCounter > 0){
    	setImage("images/explosion.gif");
        g2.drawImage(getImage(), getX(), getY(), null);
    }
}

}
