package View;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Controller.Game;
import Model.Blaster;
import Model.Laser;
import Model.Ship1;
import Model.Ship2;
import Model.Ship3;
import Model.Ship4;

public class ShipWeaponView {
	ArrayList blasterShots= null;
	ArrayList<Laser> laserShots =null;
	Ship1 ship1;
	Ship2 ship2;
	Ship3 ship3;
	Ship4 ship4;
	Game game;
	public ShipWeaponView() {
		ship1 = new Ship1("NullConstructor");
		ship2 = new Ship2("NullConstructor");
		ship3 = new Ship3("NullConstructor");
		ship4 = new Ship4("NullConstructor");
		game = new Game(0);
	}
	public void paint(Graphics2D g2) {
            /* paint blaster beams */
            /* create arraylist to store blaster shots array */
		switch (game.idToStart) {
		case 1:
			blasterShots = ship1.getBlasterShots();
			break ;
		case 2:
			blasterShots = ship2.getBlasterShots();
			break ;
		case 3:
			blasterShots = ship3.getBlasterShots();
			break ;
		case 4:
			blasterShots = ship4.getBlasterShots();
			break ;

		default:
			throw new IllegalArgumentException("Unexpected value: " + game.idToStart);
		}
			
            for (int i = 0; i < blasterShots.size(); ++i){
                Blaster temp = (Blaster)blasterShots.get(i);
                g2.drawImage(temp.getBlasterImg(), temp.getXPos(), temp.getYPos(), null);
            }
//             paint laser
        	switch (game.idToStart) {
    		case 1:
    			laserShots = ship1.getLaserShots();
    			break ;
    		case 2:
    			laserShots = ship2.getLaserShots();
    			break ;
    		case 3:
    			 laserShots = ship3.getLaserShots();
    			break ;
    		case 4:
    			laserShots = ship4.getLaserShots();
    			break ;

    		default:
    			throw new IllegalArgumentException("Unexpected value: " + game.idToStart);
    		}
           
            for (int i = 0; i < laserShots.size(); ++i){
                Laser temp = laserShots.get(i);
                g2.drawImage(temp.getLaserImg(), temp.getXPos(), temp.getYPos(), null);
            }
	}
}
