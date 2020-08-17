package Model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public abstract class PlayerShip {
	public static int x_pos;
    public static int y_pos;
    protected Image spaceShip;

    /* speed of moving right, left and when dead */
    protected int moveX;
    protected int moveY;
    public static int moveYdead =2000;
    public static int moveYAlive =Toolkit.getDefaultToolkit().getScreenSize().height - 200;

    protected int blasterDelay = 20;
    protected int laserDelay = 100;
    
    /* container to hold blaster shots */
    static ArrayList blasterShots;
    static ArrayList<Laser> laserShots;
    
    public static boolean keyLeft;
    public static boolean keyRight;
    public static boolean keyUp;
    public static boolean keyDown;
    public static boolean fire;
    public static boolean specialWeapon;
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

    public static  void moveDeadPlayer(){
    	y_pos += moveYdead;
    }
    public static  void moveAlivePlayer(){
    	y_pos = moveYAlive;
    }
    public void setYPos(int yPos) {
    	this.y_pos = yPos;
    }
	public abstract void setWeapon2Laser();

	public abstract void paint(Graphics2D g2);


       
}
