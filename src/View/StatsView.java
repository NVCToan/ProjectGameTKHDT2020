package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Controller.Game;
import Model.Shield;
import Model.Ship2;

public class StatsView {
	Game game;
	Shield shield;
	Ship2 ship2;
	 private int playerOneScore;
	 private int playerTwoScore;
	 public StatsView() {
		 shield = new Shield();
		 game = new Game(1);
		 ship2 = new Ship2("NullConstructor");
		 playerOneScore = 0;
	 }

	public int getPlayerOneScore() {
		return playerOneScore;
	}

	public void setPlayerOneScore(int playerOneScore) {
		this.playerOneScore = playerOneScore;
	}

	public int getPlayerTwoScore() {
		return playerTwoScore;
	}

	public void setPlayerTwoScore(int playerTwoScore) {
		this.playerTwoScore = playerTwoScore;
	}
	 public void paint(Graphics2D g2) {
		 g2.setFont(new Font("SanSerif", Font.BOLD, 20));
	        g2.setColor(Color.GREEN);
	        g2.drawString("PLAYER ONE", game.getScreenWidth() - 200, 30);
	        g2.drawString("POINTS: " + getPlayerOneScore(), game.getScreenWidth() - 200, 70);
	        if (shield.isShieldActive()){
	            g2.drawString("SHIELD: ACTIVE", game.getScreenWidth() - 200, 100);
	        } else {
	            g2.drawString("SHIELD: DISABLED", game.getScreenWidth() - 200, 100);
	        }
////	         Player 2
	        if (ship2.isAlive() || game.getKeyTwoCounter() > 0){
	            g2.drawString("PLAYER TWO", 5, 30);
	            g2.drawString("POINTS: " + getPlayerTwoScore(), 5, 70);
	        }
	 }
}
