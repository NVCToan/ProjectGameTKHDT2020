package Model;
/**
 * Class for creating a protecting Shield around Player One
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Shield {

    private int x_pos;
    private int y_pos;
    private Image shield;

    public static boolean shieldActive;
  public Shield() {
	  
  }
    public Shield(int x, int y){
        this.x_pos = x;
        this.y_pos = y;

        ImageIcon img = new ImageIcon("images/shield.png");
        shield        = img.getImage();

        shieldActive = false;
    }

    public int shieldX(){
        return this.x_pos;
    }

    public int shieldY(){
        return this.y_pos;
    }

    public void moveShield(PlayerShip p){
        this.x_pos = p.getX() - 15;
        this.y_pos = p.getY() - 15;
    }

    public Image getImage(){
        return shield;
    }

    public boolean isShieldActive(){
        return shieldActive;
    }

    public void setShield(boolean is){
        shieldActive = is;
    }

    public void paint(Graphics2D g2) {
        if (isShieldActive()){
        	g2.drawImage(getImage(), shieldX(),shieldY(), null);
        }
    }
}
