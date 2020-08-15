package View;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Background {
    private Image backgroundImage;
	  private int backgroundY;
	    private int bgMotion;
	    private int bgMotionSec;
	    public Background () {
	    	ImageIcon img = new ImageIcon("images/background.jpg");
	    	backgroundImage    = img.getImage();
	        bgMotion    = backgroundImage.getHeight(null);
	        bgMotionSec = 0;
	        backgroundY = 0;
	    }
	    public void backgroundMovement(){

	        bgMotion    -= 1;
	        bgMotionSec += 1;
	        backgroundY += 1;
	    }
	public void paint(Graphics2D g2 ) {
	    if ((backgroundY - 0) % (backgroundImage.getHeight(null) * 2) == 0){
            bgMotionSec = 0;
        } else if ((backgroundY - backgroundImage.getHeight(null)) % (backgroundImage.getHeight(null) * 2) == 0){
            bgMotion = (backgroundImage.getHeight(null) * 2);
        }
        g2.drawImage(backgroundImage, 0, backgroundImage.getHeight(null) - bgMotion, null);
        if (backgroundY > 0){
            g2.drawImage(backgroundImage, 0, -(backgroundImage.getHeight(null) - bgMotionSec), null);
        }
	}
	public Image getBackgroundImage() {
		return backgroundImage;
	}
	public void setBackgroundImage(Image background) {
		this.backgroundImage = background;
	}
	public int getBackgroundY() {
		return backgroundY;
	}
	public void setBackgroundY(int backgroundY) {
		this.backgroundY = backgroundY;
	}
	public int getBgMotion() {
		return bgMotion;
	}
	public void setBgMotion(int bgMotion) {
		this.bgMotion = bgMotion;
	}
	public int getBgMotionSec() {
		return bgMotionSec;
	}
	public void setBgMotionSec(int bgMotionSec) {
		this.bgMotionSec = bgMotionSec;
	}
	
}
