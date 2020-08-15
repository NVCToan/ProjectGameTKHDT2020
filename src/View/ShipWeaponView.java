package View;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Model.Blaster;
import Model.Laser;
import Model.Ship1;
import Model.Ship2;

public class ShipWeaponView {
	Ship1 ship1;
	Ship2 ship2;
	public ShipWeaponView() {
		ship1 = new Ship1("NullConstructor");
		ship2 = new Ship2("NullConstructor");
	}
	public void paint(Graphics2D g2) {
            /* paint blaster beams */
            /* create arraylist to store blaster shots array */
            ArrayList blasterShots = ship1.getBlasterShots();
            for (int i = 0; i < blasterShots.size(); ++i){
                Blaster temp = (Blaster)blasterShots.get(i);
                g2.drawImage(temp.getBlasterImg(), temp.getXPos(), temp.getYPos(), null);
            }
            // paint laser
            ArrayList<Laser> laserShots = ship1.getLaserShots();
            for (int i = 0; i < laserShots.size(); ++i){
                Laser temp = laserShots.get(i);
                g2.drawImage(temp.getLaserImg(), temp.getXPos(), temp.getYPos(), null);
            }
	}
}
