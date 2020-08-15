package View;

import java.awt.Graphics2D;

import Controller.Game;
import Model.SpaceCraftWeapon;

public class EnemiWeaponView {
	Game game;
	public EnemiWeaponView() {
		game = new Game(1);
	}
	public void paint(Graphics2D g2){
		 for (int i = 0; i < game.fireShots.size(); ++i){
		 SpaceCraftWeapon fire = game.fireShots.get(i);
         if (fire.isVisible()){
             g2.drawImage(fire.getEnemyFireImg(), fire.getXPos(), fire.getYPos(), null);
         }
	}
		 }
	
}
