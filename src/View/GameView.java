package View;

import java.awt.Frame;
import java.io.IOException;
import javax.swing.JFrame;
import Controller.Game; 
import Model.CacheDataLoader; 

public class GameView {
	public GameView() {
		JFrame frame = new JFrame();
		frame.setTitle("Spacecraft Battles");
		try {
			CacheDataLoader.getInstance().loadData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Game gameMain = new Game();
        frame.add(gameMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
}
