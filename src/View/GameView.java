package View;

import java.awt.Frame;

import javax.swing.JFrame;

import Controller.Game;

public class GameView {
	public GameView() {
		JFrame frame = new JFrame();
        Game gameMain = new Game();
        frame.add(gameMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Spacecraft Battles");
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
}
