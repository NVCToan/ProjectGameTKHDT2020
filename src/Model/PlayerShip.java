package Model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public abstract class PlayerShip {
	protected int x_pos;
    protected int y_pos;
    protected Image spaceShip;

    /* speed of moving right, left and when dead */
    protected int moveX;
    protected int moveY;
    protected int moveYdead;

    protected int blasterDelay = 20;
    protected int laserDelay = 100;
    
    /* container to hold blaster shots */
    static ArrayList blasterShots;
    static ArrayList<Laser> laserShots;
    
    protected boolean keyLeft;
    protected boolean keyRight;
    protected boolean keyUp;
    protected boolean keyDown;
    protected boolean fire;
    protected boolean specialWeapon;
    protected boolean isAlive;

    

    public void moveRight(){
        x_pos += moveX;
    }

    public void moveLeft(){
        x_pos -= moveX;
    }

    public void moveForward(){
        y_pos -= moveY;
    }

    public void moveBack(){
        y_pos += moveY;
    }

    public Image getImage(){
        return spaceShip;
    }

    public void setImage(String name){
        ImageIcon img = new ImageIcon(name);
        spaceShip     = img.getImage();
    }

    public int getX(){
        return x_pos;
    }

    public int getY(){
        return y_pos;
    }

    public void setX(int x){
        this.x_pos = x;
    }

    public void setY(int y){
        this.y_pos = y;
    }

    /* generate blaster beam */
    public abstract void setWeapon1(IWeapon weapon1);

    public static ArrayList getBlasterShots(){
        return blasterShots;
    }

    /* generate laser beam */
    public abstract void setWeapon2(IWeapon weapon1);

    public ArrayList<Laser> getLaserShots(){
        return laserShots;
    }

    /**
     * Method for creating a rectangle around the player so we can check for collisions
     */
    public Rectangle getBounds(){
        return new Rectangle(x_pos, y_pos, spaceShip.getWidth(null), spaceShip.getHeight(null));
    }

    public int getBlasterDelay(){
        return this.blasterDelay;
    }

    public void setBlasterDelay(){
        --this.blasterDelay;
    }
    public int getLaserDelay() {
    	return this.blasterDelay;
    }
    public void setLaserDelay() {
    	--this.laserDelay;
    }
    public boolean isKeyLeft(){
        return keyLeft;
    }

    public boolean isKeyRight(){
        return keyRight;
    }

    public boolean isKeyUp(){
        return keyUp;
    }

    public boolean isKeyDown(){
        return keyDown;
    }

    public boolean isFire(){
        return fire;
    }
    public void setFire(boolean b) {
    	
    	this.fire = b;
    }
    public boolean isSpecialWeapon(){
        return specialWeapon;
    }

    public boolean isAlive(){
        return isAlive;
    }

    public void setAlive(boolean is){
        this.isAlive = is;
    }

    public void moveDeadPlayer(){
        this.y_pos += moveYdead;
    }

    /* register when key is down */
    public abstract void keyPressed(KeyEvent e);

       
      
     


    /* register when key is released */
    public abstract void keyReleased(KeyEvent e);

       
}
