package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Controller.Game;

public class OverGameView {
	Game game;
public OverGameView() {
	game = new Game(1);
	
}
public void paint(Graphics2D g2) {
	 g2.setFont(new Font("SanSerif", Font.BOLD, 50));
     g2.setColor(Color.red);
     g2.drawString("YOU LOSS", (game.screenWidth - 200) / 2, game.screenHeight / 3);
     g2.setFont(new Font("SanSerif", Font.BOLD, 16));
     g2.setColor(Color.GRAY);
     g2.drawString("Press space to play again", (game.screenWidth - 95) / 2, game.screenHeight / 2);
}
}
