package Model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class InputManager {
	private PlayerShip playerShip;
	public InputManager(PlayerShip playerShip ) {
		this.playerShip = playerShip;
	}
	public void processKeyPressed(int keyCode) {
		switch (keyCode) {
		case KeyEvent.VK_UP:
			break ;
		case KeyEvent.VK_DOWN:
			break ;
		case KeyEvent.VK_LEFT:
			break ;
		case KeyEvent.VK_RIGHT:
			break ;
		case KeyEvent.VK_A:
		case KeyEvent.VK_ENTER:
			if(playerShip.CURRENT_SCREEN  == playerShip.GAMEOVER_SRCEEN) {
				playerShip.CURRENT_SCREEN = playerShip.BEGIN_SCREEN;
			}
			break ;
		case KeyEvent.VK_SPACE:
			switch (playerShip.CURRENT_SCREEN) {
			case playerShip.BEGIN_SCREEN:
				playerShip.CURRENT_SCREEN = playerShip.GAMEPLAY_SCREEN;
				break ;
			case FlappyBirds.GAMEPLAY_SCREEN:
				if(flappyBird.bird.getLive())flappyBird.bird.fly();
				break ;
			default: ;
				break ;
			}
			
			

		
		}
	}
	public void processKeyReleased(int keyCode) {
		switch (keyCode) {
		case KeyEvent.VK_UP:

			break ;
		case KeyEvent.VK_DOWN:
			break ;
		case KeyEvent.VK_RIGHT:
			break ;
		case KeyEvent.VK_A:
			
			break ;
		case KeyEvent.VK_ENTER:
			
			break ;
		case KeyEvent.VK_SPACE:
			
			break ;
			
			
		}
	}
}