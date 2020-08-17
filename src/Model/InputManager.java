package Model;
import javax.swing.*;

import Controller.Game;

import java.awt.*;
import java.awt.event.KeyEvent;



public class InputManager {
	Game game;
	public InputManager(Game game) {
		this.game = game;
	}
	public void processKeyPressed(int keyCode) {
	
		switch (keyCode) {
		case KeyEvent.VK_LEFT:
			PlayerShip.keyLeft = true;

			break ;
		case KeyEvent.VK_RIGHT:
			PlayerShip.keyRight = true;

			break ;
		case KeyEvent.VK_UP:
			PlayerShip.keyUp = true;

			break ;
		case KeyEvent.VK_DOWN:
			PlayerShip.keyDown = true;

			break ;
		case KeyEvent.VK_Q:
			PlayerShip.fire =  true; 
			game.blaserShotSound.play();
			break ;
		case KeyEvent.VK_W:
			PlayerShip.specialWeapon = true;
			game.bigBagShot.play();
			break ;
		case KeyEvent.VK_A:
			if(game.superBuff < 1) {
        		game.superBuff++;
        	}else game.superBuff=0;
              
			break;
		case KeyEvent.VK_R:
			game.shield.shieldActive=true;
		break;
		case KeyEvent.VK_T:
			game.shield.shieldActive=false;
			break;
		case KeyEvent.VK_2:
			if( game.keyTwoCounter == 0 && !game.isGameLost) {
				 game.ship2.setAlive(true);
	                game.keyTwoCounter++;
			}
			break;
		case KeyEvent.VK_SPACE:
				game.resetGame();
			break;
		case KeyEvent.VK_ESCAPE:
			if( game.escapeCounter < 1) {
				game.isGameStarted = false;
				game.escapeCounter+=1;
			}else
			if(game.escapeCounter > 0) {
				game.isGameStarted = true;
                game.escapeCounter-=1;
			}
			break;
		case KeyEvent.VK_ENTER:
			
			break ;
			
			

		
		}
	}
	public void processKeyReleased(int keyCode) {
		switch (keyCode) {
		case KeyEvent.VK_LEFT:
			PlayerShip.keyLeft = false;

			break ;
		case KeyEvent.VK_RIGHT:
			PlayerShip.keyRight = false;

			break ;
		case KeyEvent.VK_UP:
			PlayerShip.keyUp = false;

			break ;
		case KeyEvent.VK_DOWN:
			PlayerShip.keyDown = false;

			break ;
		case KeyEvent.VK_Q:
			PlayerShip.fire =false ; 
			game.blaserShotSound.stop();
			break ;
		case KeyEvent.VK_W:
			PlayerShip.specialWeapon = false;
			break ;
		case KeyEvent.VK_A:
			
			break;
		case KeyEvent.VK_ESCAPE:
			if( game.escapeCounter < 1) {
				game.isGameStarted = true;
			}
			if(game.escapeCounter > 0) {
				game.isGameStarted = false;
			}
			break;
		case KeyEvent.VK_R:
			game.shield.shieldActive=true;
		break;
		case KeyEvent.VK_T:
			game.shield.shieldActive=false;
			break;
		case KeyEvent.VK_ENTER:
			
			break ;
		case KeyEvent.VK_SPACE:
			
		break;
			
			
		}
	}
}