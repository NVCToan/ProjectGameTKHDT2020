package Controller;
/**
 * WARNING !!!
 * The game is tested only on monitors with maximum resolution of 1366x768 and 1600x900
 * It works also good on 1280x720, 1280x768, 1360x768
 * On resolutions different from the above, the game may not work correctly, may be too slow(on higher resolutions) or
 * too fast(on lower resolutions)
 *
 * This Game class is the main class of the game and it is responsible for handling animations, collisions, drawing,
 * taking mouse and keyboard input.
 */

import javax.swing.*;

import Model.Blaster;
import Model.CacheDataLoader;
import Model.DataConfig;
import Model.InputManager;
import Model.Laser;
import Model.PlayerShip;
import Model.Shield;
import Model.Ship1;
import Model.Ship2;
import Model.Ship3;
import Model.Ship4;
import Model.SoundPlayer;
import Model.SpaceCraft;
import Model.SpaceCraftWeapon;
import View.Background;
import View.EnemiWeaponView;
import View.GameMenu;
import View.OverGameView;
import View.ShipWeaponView;
import View.StatsView;


import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JPanel implements ActionListener {
	public static int idToStart = -1;
	public  int isSelectedID ;
	
	public int superBuff=0;
    public PlayerShip ship1;
    public PlayerShip ship2;
    public PlayerShip ship3;
    public PlayerShip ship4;
    public Shield shield;
    private Image backgroundMenu;
    private Timer time;
    InputManager inputmanager;
    //Background
    Background background;
   // OverGameView
    OverGameView overGame;
    // Stats
    StatsView stats ;
    // Ship weapon
    ShipWeaponView shipWeapon;
    ArrayList blasterShots = null;
    Rectangle playerBounds = null;
    // EnemiWeapon
//    EnemiWeaponView enemiWeapon;
    /* GAME VARIABLES */
    public int escapeCounter = 0;
    public int keyTwoCounter = 0;

    /* GAME BOOLEAN data */
    public boolean isGameLost = false;
    public boolean isGameStarted = false;
    
    // get the screen dimensions
    public static int screenWidth  = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

    private DataConfig cfg = new DataConfig();

    /* add all the enemies and their weapons into ArrayList */
    
    public ArrayList<SpaceCraft> enemies = new ArrayList<SpaceCraft>();
    public ArrayList<SpaceCraftWeapon> fireShots = new ArrayList<SpaceCraftWeapon>();

    Random randGen = new Random();

    /* GAME CONSTANTS */
    private final int ENEMY_SPEED        = 2;
    private final int BUTTON_PADDING_TOP = 35;
    
    
    private final int ENEMIES_SPAWN_Y    = 6000;

    /* MENU BUTTONS AND VALUES*/
    GameMenu menu = new GameMenu();

    private boolean isStartButtonHovered;
    private boolean isExitButtonHovered;
    private boolean isSettingsButtonHovered;
    private boolean isBackButtonHovered;

    //BackgroundSound
    private SoundPlayer  menuSounds = CacheDataLoader.getInstance().getSound("menusound");
    static SoundPlayer  playGameSound  = CacheDataLoader.getInstance().getSound("playgamesound");
	
    public static SoundPlayer blaserShotSound = CacheDataLoader.getInstance().getSound("blasershot");
    public static SoundPlayer bigBagShot = CacheDataLoader.getInstance().getSound("bigbagshot");
    
    /**
     * Class for registering key presses
     */
    public int getIdToStart() {
    	return idToStart;
    }
    private class MyActionListener extends KeyAdapter {
    	
        @Override
        public void keyPressed(KeyEvent e){
        	
        	inputmanager.processKeyPressed(e.getKeyCode());
        }
        
        @Override
        public void keyReleased(KeyEvent e){
        	inputmanager.processKeyReleased(e.getKeyCode());
        }
    }

    /**
     * Class for registering mouse events
     */
    private class MouseEvents extends MouseAdapter {

        @Override
        public void mouseMoved(MouseEvent e){
            int mouseX = e.getX();
            int mouseY = e.getY();

            if (mouseX > menu.startButton().x && mouseX < menu.startButton().x + menu.startButton().width &&
                mouseY > menu.startButton().y && mouseY < menu.startButton().y + menu.startButton().height &&
                    !isGameStarted && menu.isMainMenuActive()){

                isStartButtonHovered = true;

            } else {
                isStartButtonHovered = false;
            }

            if (mouseX > menu.settingsButton().x && mouseX < menu.settingsButton().x + menu.settingsButton().width &&
                mouseY > menu.settingsButton().y && mouseY < menu.settingsButton().y + menu.settingsButton().height &&
                    !isGameStarted && menu.isMainMenuActive()){

                isSettingsButtonHovered = true;

            } else {
                isSettingsButtonHovered = false;
            }

            if (mouseX > menu.exitButton().x && mouseX < menu.exitButton().x + menu.exitButton().width &&
                mouseY > menu.exitButton().y && mouseY < menu.exitButton().y + menu.exitButton().height &&
                    !isGameStarted && menu.isMainMenuActive()){

                isExitButtonHovered = true;

            } else {
                isExitButtonHovered = false;
            }

            if (mouseX > menu.backButton().x && mouseX < menu.backButton().x + menu.backButton().width &&
                mouseY > menu.backButton().y && mouseY < menu.backButton().y + menu.backButton().height &&
                    !isGameStarted){

                isBackButtonHovered = true;

            } else {
                isBackButtonHovered = false;
            }
        }

        @Override
        public void mousePressed(MouseEvent e){
            int mouseX = e.getX();
            int mouseY = e.getY();

            if (mouseX > menu.startButton().x && mouseX <  menu.startButton().x +  menu.startButton().width &&
                mouseY >  menu.startButton().y && mouseY <  menu.startButton().y +  menu.startButton().height &&
                    !isGameStarted && menu.isMainMenuActive()){

            	menu.setSelectSpace(true);

              
            }
            
            if(menu.isSelectSpace) {
            	if(menu.idSpace(mouseX, mouseY) >0) {
            		 idToStart = menu.idSpace(mouseX, mouseY);
            		 isSelectedID = menu.idSpace(mouseX, mouseY);
            		 isGameStarted = true;
            		 playGameSound.play();	
            	}
            	
            }
           

            
            if (mouseX > menu.settingsButton().x && mouseX < menu.settingsButton().x + menu.settingsButton().width &&
                mouseY > menu.settingsButton().y && mouseY < menu.settingsButton().y + menu.settingsButton().height &&
                    !isGameStarted && menu.isMainMenuActive()){

                menu.setSettingsOpened(true);
            }
            if (mouseX > menu.backButton().x && mouseX < menu.backButton().x + menu.backButton().width &&
                mouseY > menu.backButton().y && mouseY < menu.backButton().y + menu.backButton().height &&
                    !isGameStarted){

                menu.setBackClicked(true);
            }
            if (mouseX > menu.exitButton().x && mouseX < menu.exitButton().x + menu.exitButton().width &&
                mouseY > menu.exitButton().y && mouseY < menu.exitButton().y + menu.exitButton().height &&
                    !isGameStarted && menu.isMainMenuActive()){

                System.exit(0);
            }
        }
    }
    public Game(int a) {
    	
    }

    public Game(){
        ship1    = new Ship1();
        ship2 = new Ship2();
        ship3 = new Ship3();
        ship4 = new Ship4();
        shield    = new Shield(ship1.getX(), ship1.getY());
        inputmanager = new InputManager(this);
       background = new Background();
       stats = new StatsView();
       shipWeapon = new ShipWeaponView();
       overGame = new OverGameView();
        /* spawn enemies */
        for (int i = 0; i < 10 ; ++i){
            int x_position = 50 + randGen.nextInt(screenWidth - 100);
            int y_position = -randGen.nextInt(ENEMIES_SPAWN_Y);
            SpaceCraft p = new SpaceCraft(x_position, y_position);
          
            enemies.add(p);
        }

        /* add key and mouse listeners */
        addKeyListener(new MyActionListener());
        addMouseListener(new MouseEvents());
        addMouseMotionListener(new MouseEvents());
        

        // set the background for the game menu for different resolutions
        String menuBackground = "images/gameMenuBG.jpg";
        if (screenWidth == 1366 && screenHeight == 768){
            menuBackground = "images/gameMenu2.jpg";
        } else if (screenWidth == 1440 && screenHeight == 900){
            menuBackground = "images/gameMenu3.jpg";
        }
        ImageIcon bgImg = new ImageIcon(menuBackground);
        backgroundMenu  = bgImg.getImage();

       

        setFocusable(true);

        time = new Timer(2, this);
        time.start();
        menuSounds.play();
    }

    /**
     * Method for calculating background speed
     */


    /**
     * This function handles collision detection
     * for all aspects of the game
     */
    public void detectCollisions(){
    	switch (idToStart) {
		case 1:
			blasterShots = ship1.getBlasterShots();
			playerBounds   = ship1.getBounds();
			break ;
		case 2:
			blasterShots = ship2.getBlasterShots();
			playerBounds   = ship2.getBounds();			break ;
		case 3:
			blasterShots = ship3.getBlasterShots();
			playerBounds   = ship3.getBounds();			break ;
		
		case 4:
			blasterShots = ship4.getBlasterShots();
			playerBounds   = ship4.getBounds();			break ;
			
		}
        

      

        ArrayList<Rectangle> enemiesBounds = new ArrayList<Rectangle>();

        /* HANDLE COLLISION DETECTION FOR ALL THE ENEMIES */
        for (int i = 0; i < enemies.size(); ++i){
            enemiesBounds.add(enemies.get(i).getBounds());
        }
        /* check if blaster hits the enemies */
        for (int i = 0; i < blasterShots.size(); ++i){
            Blaster temp = (Blaster)blasterShots.get(i);
            Rectangle blasterBounds = temp.getBounds();

            for (int j = 0; j < enemies.size(); ++j){
                if (enemiesBounds.get(j).intersects(blasterBounds) && enemies.get(j).isAlive()){
                    enemies.get(j).setAlive(false);
                    temp.setVisible(false);
                    stats.setPlayerOneScore(stats.getPlayerOneScore()+10);
                }
            }
        }
        /* check if laser hits the enemies */
        ArrayList<Laser> laserShots = ship1.getLaserShots();
        for (int i = 0; i < laserShots.size(); ++i){
            Laser temp = laserShots.get(i);
            Rectangle laserBounds = temp.getBounds();

            for (int j = 0; j < enemies.size(); ++j){
                if (enemiesBounds.get(j).intersects(laserBounds) && enemies.get(j).isAlive()){
                    enemies.get(j).setAlive(false);
                    temp.setVisible(false);
                    stats.setPlayerOneScore(stats.getPlayerOneScore()+10);
                }
            }
        }

        /* check if player collides with enemies */
        for (int i = 0; i < enemies.size(); ++i){
            if (playerBounds.intersects(enemies.get(i).getBounds()) &&
                    !shield.isShieldActive() && enemies.get(i).isAlive()){
            		idToStart = 0;
            		ship1.setAlive(false);
//                    isGameLost = true;

            }
        }

        // create rectangles around enemies fire
        ArrayList<Rectangle> enemiesFire = new ArrayList<Rectangle>();
        for (int i = 0; i < fireShots.size(); ++i){
            enemiesFire.add(fireShots.get(i).getBounds());
        }

        // check for collisions between player and enemies' fire
        for (int i = 0; i < fireShots.size(); ++i){
            if (playerBounds.intersects(fireShots.get(i).getBounds()) &&
                    !shield.isShieldActive() && fireShots.get(i).isVisible()){

                fireShots.get(i).setVisible(false);
                idToStart = 0;
                ship1.setAlive(false);
//                isGameLost = true;
            }
        }



    }

    /**
     * This method takes all the actions that are performed in the game
     * and repeats them every x ms, where x is the value in Timer(x, this);
     * In my case x = 2.
     */
    @Override
    public void actionPerformed(ActionEvent e){
    	// set Ship True
    	
        if (isGameStarted){
        	 switch (idToStart) {
     		case 1:
     			ship1.setAlive(true);
     			
     			break ;
     		case 2:
     			ship1.setAlive(true);

     			break ;
     		case 3:
     			ship1.setAlive(true);

     			break ;
     		
     		case 4:
     			ship1.setAlive(true);
     			break ;
     			
     		}
             
    			   if (ship1.isAlive()==true ){
    	                detectCollisions();
    	                movePlayerWeapons();
    	            }
    			   if (ship1.isAlive()==false){
    				   ship1.setFire(false);
    				   isGameLost=true;
    				   ship1.setSpecialWeapon(false); 
    			   }
    			  if (ship2.isAlive() ==true){
    	                detectCollisions();
    	                movePlayerWeapons();
    	            }
    			if (ship2.isAlive()==false){

    					ship2.setFire(false);		
    				 isGameLost=true;
    				 ship2.setSpecialWeapon(false); 
    				 }
    			if (ship3.isAlive()==true ){
        			isGameLost = false;
    				detectCollisions();
    				movePlayerWeapons();
    			}
    			if (ship3.isAlive()==false){
    				ship3.moveDeadPlayer();
    				ship3.setFire(false);	
    				isGameLost=true;
    				ship3.setSpecialWeapon(false); 	
    			}
    			if (ship4.isAlive()==true ){
    				detectCollisions();
    				movePlayerWeapons();
    			}
    			if (ship4.isAlive()==false){
    				ship4.moveDeadPlayer();
    				ship4.setFire(false);	
    				isGameLost=true;
    				ship4.setSpecialWeapon(false); 
    				}
        	//End set Ship True
            if (ship1.isAlive() == true){
                if (ship1.isKeyLeft() && ship1.getX() > 20){
                	ship1.moveLeft();
                }
                if (ship1.isKeyRight() && ship1.getX() < (screenWidth - 116)){
                	ship1.moveRight();
                }
                if (ship1.isKeyUp() && ship1.getY() > 20){
                	ship1.moveForward();
                }
                if (ship1.isKeyDown() && ship1.getY() < (screenHeight - 118)){
                	ship1.moveBack();
                }
                if (ship1.getBlasterDelay() > 0){
                	ship1.setBlasterDelay();
                }
                if (ship1.getLaserDelay() > 0){
                	ship1.setLaserDelay();
                }
                if (ship1.isFire() && ship1.getBlasterDelay() == 0){
                	 ship1.setWeapon1(new Blaster(ship1.getX(), ship1.getY()));

                }
                if (!ship1.isFire() && ship1.isSpecialWeapon() && ship1.getLaserDelay() == 0){
                	ship1.setWeapon2Laser();
                }
                
                else {
                	if(superBuff==1) {
                		
                		ship1.setImage("images/spacecraft-super1.png");
                	}
                	else ship1.setImage("images/ship1.png");
                    
                    
                }
            }
            //Ship2
            if (ship2.isAlive()==true){
                if (ship2.isKeyLeft() && ship2.getX() > 20){
                	ship2.moveLeft();
                }
                if (ship2.isKeyRight() && ship2.getX() < (screenWidth - 116)){
                	ship2.moveRight();
                }
                if (ship2.isKeyUp() && ship2.getY() > 20){
                	ship2.moveForward();
                }
                if (ship2.isKeyDown() && ship2.getY() < (screenHeight - 118)){
                	ship2.moveBack();
                }

                if (ship2.getBlasterDelay() > 0){
                	ship2.setBlasterDelay();
                }
                if (ship2.getLaserDelay() > 0){
                	ship2.setLaserDelay();
                }
                if (ship2.isFire() && ship2.getBlasterDelay() == 0){
                	ship2.setWeapon1(new Blaster(ship2.getX(), ship2.getY()));

                }
                if (!ship2.isFire() && ship2.isSpecialWeapon() && ship2.getLaserDelay() == 0){
                	ship2.setWeapon2Laser();
                }
            
                
            }
            // End ship2
            //ship3
            
            if (ship3.isAlive()==true){
                if (ship3.isKeyLeft() && ship3.getX() > 20){
                	ship3.moveLeft();
                }
                if (ship3.isKeyRight() && ship3.getX() < (screenWidth - 116)){
                	ship3.moveRight();
                }
                if (ship3.isKeyUp() && ship3.getY() > 20){
                	ship3.moveForward();
                }
                if (ship3.isKeyDown() && ship3.getY() < (screenHeight - 118)){
                	ship3.moveBack();
                }

                if (ship3.getBlasterDelay() > 0){
                	ship3.setBlasterDelay();
                }
                if (ship3.getLaserDelay() > 0){
                	ship3.setLaserDelay();
                }
                if (ship3.isFire() && ship3.getBlasterDelay() == 0){
                	ship3.setWeapon1(new Blaster(ship3.getX(), ship3.getY()));

                }
                if (!ship3.isFire() && ship3.isSpecialWeapon() && ship3.getLaserDelay() == 0){
                	ship3.setWeapon2Laser();
                }
            
                
            }
            // End ship3
            // Ship4
            if (ship4.isAlive()==true){
            	if (ship4.isKeyLeft() && ship4.getX() > 20){
            		ship4.moveLeft();
            	}
            	if (ship4.isKeyRight() && ship4.getX() < (screenWidth - 116)){
            		ship4.moveRight();
            	}
            	if (ship4.isKeyUp() && ship4.getY() > 20){
            		ship4.moveForward();
            	}
            	if (ship4.isKeyDown() && ship4.getY() < (screenHeight - 118)){
            		ship4.moveBack();
            	}
            	
            	if (ship4.getBlasterDelay() > 0){
            		ship4.setBlasterDelay();
            	}
            	if (ship4.getLaserDelay() > 0){
            		ship4.setLaserDelay();
            	}
            	if (ship4.isFire() && ship4.getBlasterDelay() == 0){
            		ship4.setWeapon1(new Blaster(ship4.getX(), ship4.getY()));
            		
            	}
            	if (!ship4.isFire() && ship4.isSpecialWeapon() && ship4.getLaserDelay() == 0){
            		ship4.setWeapon2Laser();
            	}
            	
            	
            }
            //end ship4
           

            background.backgroundMovement();

            if (ship1.isAlive() || ship2.isAlive() || ship3.isAlive()|| ship4.isAlive()){
            	switch (idToStart) {
        		case 1:
                    shield.moveShield(ship1);
        			break ;
        		case 2:
                    shield.moveShield(ship2);
        			break ;
        		case 3:
                    shield.moveShield(ship3);
        			break ;
        		case 4:
        			shield.moveShield(ship4);
        			break ;

        		}
            }
         

            

            moveEnemies();
            enemiesFire();

            repaint();
        }
    }

    /**
     * Method for controlling enemies' movement
     */
    public void moveEnemies(){
        Random rand = new Random();

        for (int i = 0; i < enemies.size(); ++i){
            SpaceCraft enemy = enemies.get(i);

            if (enemies.get(i).isAlive()){
                enemies.get(i).moveForward(ENEMY_SPEED);
                if (i % 2 == 0 && enemies.get(i).getY() > 0){
                    if (enemies.get(i).getX() > 0 && enemies.get(i).getX() < 400){
                        enemies.get(i).moveRight();
                    } 
                    if (enemies.get(i).getX() > 500 && enemies.get(i).getX() < screenWidth - 60){
                        enemies.get(i).moveLeft();
                    }
                }
            }

            if (stats.getPlayerOneScore() < 30 && (enemy.isAlive()==false || enemy.getY() > screenHeight)){
                int x_position = 50 + rand.nextInt(screenWidth - 100);
                int y_position = -rand.nextInt(ENEMIES_SPAWN_Y);
                enemy = new SpaceCraft(x_position, y_position);
                enemy.setImage("images/enemy2.png");
                enemies.set(i, enemy);
            }
            if(stats.getPlayerOneScore()>30 &&stats.getPlayerOneScore()<=40&& (enemy.isAlive() ==false|| enemy.getY() > screenHeight)) {
            	int x_position = 50 + rand.nextInt(screenWidth - 100);
                int y_position = -rand.nextInt(ENEMIES_SPAWN_Y);
                enemy = new SpaceCraft(x_position, y_position);
                enemy.setImage("images/enemy-old.png");
                enemies.set(i, enemy);
            }
            if(stats.getPlayerOneScore()>40 && (enemy.isAlive() ==false|| enemy.getY() > screenHeight)) {
            	int x_position = 50 + rand.nextInt(screenWidth - 100);
            	int y_position = -rand.nextInt(ENEMIES_SPAWN_Y);
            	enemy = new SpaceCraft(x_position, y_position);
            	enemy.setImage("images/boss.png");
            	enemies.set(i, enemy);
            }
        }
    }

    private static Random rand = new Random();
    /**
     * This method enables shooting for enemies
     */
    public void enemiesFire(){
        //enemies shooting
        int countTime = 2000;
        if (countTime - 10 > 1){
            int randIndex = rand.nextInt(enemies.size());
            randIndex     = Math.abs(randIndex);
            SpaceCraft enemy = enemies.get(randIndex);
            int fireDelay = enemy.getCurrentFireDelay();

            if (fireDelay == 0 && enemy.isAlive()){
                SpaceCraftWeapon fire = enemies.get(randIndex).shoot();
                fireShots.add(fire);
                enemy.setCurrentFireDelay(fire.getFireDelay());
            } else {
                enemy.setCurrentFireDelay(fireDelay - 1);
            }
            countTime = 0;
        }
        countTime++;
        //move shots
        for (int i = 0; i < fireShots.size(); ++i){
            SpaceCraftWeapon fireShot = fireShots.get(i);
            if (fireShot.isVisible()){
                fireShot.moveShot();
            } else {
                fireShots.remove(i);
            }
        }
    }

    /**
     * Method for moving Players' weapon types
     */
    public void movePlayerWeapons(){
        /* blaster shots */
    	switch (idToStart) {
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

		}
       
        /* if the blaster is visible - move */
        for (int i = 0; i < blasterShots.size(); ++i){
            Blaster temp = (Blaster)blasterShots.get(i);
            if (temp.isVisible() == true){
                temp.moveShot();
            } else {
                blasterShots.remove(i);
            }
        }

        // laser
        ArrayList<Laser> laserShots = ship1.getLaserShots();
        for (int i = 0; i < laserShots.size(); ++i){
            Laser temp = laserShots.get(i);
            if (temp.isVisible() == true){
                temp.moveShot();
            } else {
                laserShots.remove(i);
            }
        }

        // PLAYER TWO
        /* blaster shots */
//        ArrayList blasterShotsTwo = ship2.getBlasterShots();
//        /* if the blaster is visible - move */
//        for (int i = 0; i < blasterShotsTwo.size(); ++i){
//            Blaster temp = (Blaster)blasterShotsTwo.get(i);
//            if (temp.isVisible() == true){
//                temp.moveShot();
//            } else {
//                blasterShotsTwo.remove(i);
//            }
//        }
//
//        // laser
//        ArrayList<Laser> laserShotsTwo = ship2.getLaserShots();
//        for (int i = 0; i < laserShotsTwo.size(); ++i){
//            Laser temp = laserShotsTwo.get(i);
//            if (temp.isVisible() == true){
//                temp.moveShot();
//            } else {
//                laserShotsTwo.remove(i);
//            }
//        }
    }

    /**
     * Main method for drawing the whole game:
     * Game Menu
     * Background
     * Weapons
     * Enemies
     * Players ... etc.
     */
    @Override
    public void paint(Graphics g){
    	

        super.paint(g);
       
        Graphics2D graphics2D = (Graphics2D)g;
        
        if (!isGameStarted){
            drawGameMenu(graphics2D);
           

        } else {
        	menuSounds.stop();
            drawBackground(graphics2D);

            drawWeapons(graphics2D);

            drawEnemies(graphics2D);

            drawPlayers(graphics2D);

            /* draw shield */
            
            shield.paint(graphics2D);

            drawStats(graphics2D);

            if (isGameLost==true){
            	overGame.paint(graphics2D);
            }
        }
    }

    /**
     * Method for drawing the Game Menu
     */
    public void drawGameMenu(Graphics g){
        menu.drawGameMenu(g, backgroundMenu, BUTTON_PADDING_TOP,
                          isStartButtonHovered, isExitButtonHovered,
                          isSettingsButtonHovered, isBackButtonHovered);
       
        repaint();
    }

    /**
     * Method for calculating and drawing the scrolling background
     */
    public void drawBackground(Graphics2D g2){
    	background.paint(g2);
    }

    /**
     * Method for drawing weapons in the game
     */
    public void drawWeapons(Graphics2D g2){
        if (ship1.isAlive()==true || ship2.isAlive()==true || ship3.isAlive()==true || ship4.isAlive()==true){
            /* paint blaster beams */
            /* create arraylist to store blaster shots array */
        	shipWeapon.paint(g2);
        }
        //enemies weapon
        for (int i = 0; i < fireShots.size(); ++i){
            SpaceCraftWeapon fire = fireShots.get(i);
            if (fire.isVisible()){
                g2.drawImage(fire.getEnemyFireImg(), fire.getXPos(), fire.getYPos(), null);
            }
        }

        // PLAYER TWO
//        if (ship2.isAlive()){
//            ArrayList blasterShotsTwo = ship2.getBlasterShots();
//            for (int i = 0; i < blasterShotsTwo.size(); ++i){
//                Blaster temp = (Blaster)blasterShotsTwo.get(i);
//                temp.setImage("images/blasterTwo.png");
//                g2.drawImage(temp.getBlasterImg(), temp.getXPos(), temp.getYPos(), null);
//            }
//            // paint laser
//            ArrayList<Laser> laserShotsTwo = ship2.getLaserShots();
//            for (int i = 0; i < laserShotsTwo.size(); ++i){
//                Laser temp = laserShotsTwo.get(i);
//                temp.setImage("images/laserTwo.png");
//                g2.drawImage(temp.getLaserImg(), temp.getXPos(), temp.getYPos(), null);
//            }
//        }
    }

    /**
     * Method for drawing enemies in the game
     */
    public void drawEnemies(Graphics g){
        for (int i = 0; i < enemies.size(); ++i){
            if (enemies.get(i).isAlive()){
                g.drawImage(enemies.get(i).getImage(), enemies.get(i).getX(), enemies.get(i).getY(), null);
            }
        }
    }

    /**
     * Method for drawing players in the game
     */
    public void drawPlayers(Graphics2D g2){
    	switch (isSelectedID) {
		case 1:
	    	ship1.paint(g2);
			break ;
		case 2:
			ship2.paint(g2);
			break ;
		case 3:
			
			ship3.paint(g2);
			break ;
		case 4:
			ship4.paint(g2);
			
			break ;

		}
    
    }

    /**
     * Method for drawing game statistics
     */
    public void drawStats(Graphics2D g2){

    	stats.paint(g2);
    }

    /**
     * Method for saving the current game progress
     */
    public void saveConfig(){
        ObjectOutputStream out;
        /* save the data */
        cfg.player_one_x_pos  = ship1.getX();
        cfg.player_one_y_pos  = ship1.getY();
        cfg.player_one_score  = stats.getPlayerOneScore();
        cfg.player_one_shield = shield.isShieldActive();

        cfg.player_two_x_pos = ship2.getX();
        cfg.player_two_y_pos = ship2.getY();
        cfg.player_two_score = stats.getPlayerTwoScore();

        cfg.key_two_counter  = keyTwoCounter;

        cfg.is_player_one_alive = ship1.isAlive();
        cfg.is_player_two_alive = ship2.isAlive();

        cfg.background_position   = background.getBackgroundY();
        cfg.background_motion     = background.getBgMotion();
        cfg.background_motion_sec = background.getBgMotionSec();

        cfg.is_game_lost = false;

        try{
            out = new ObjectOutputStream(new FileOutputStream("Game.cfg"));
            out.writeObject(cfg);
            out.flush();
            out.close();
        }
        catch(IOException e){}
    }

    /**
     * Method for loading the last saved game progress
     */
    public void loadConfig(){
        ObjectInputStream in;
        try{
            in = new ObjectInputStream(new FileInputStream("Game.cfg"));
            try{
                cfg = (DataConfig)in.readObject();
                /* load the data */
                ship1.setX(cfg.player_one_x_pos);
                ship1.setY(cfg.player_one_y_pos);
                stats.setPlayerOneScore(cfg.player_one_score);
                shield.setShield(cfg.player_one_shield);

                ship2.setX(cfg.player_two_x_pos);
                ship2.setY(cfg.player_two_y_pos);
                stats.setPlayerTwoScore(cfg.player_two_score);

                keyTwoCounter = cfg.key_two_counter;

                ship1.setAlive(cfg.is_player_one_alive);
                ship2.setAlive(cfg.is_player_two_alive);

                background.setBackgroundY(cfg.background_position); 
                background.setBgMotion(cfg.background_motion)    ;
                background.setBgMotionSec(cfg.background_motion_sec);

                isGameLost = cfg.is_game_lost;
            }
            catch(ClassNotFoundException e){}
            catch(IOException e){}
            in.close();
        }
        catch(IOException e){}
    }
    
    public int getScreenWidth() {
    	return screenWidth;
    }
    public int getScreenHeight() {
    	return screenHeight;
    }
    public int getKeyTwoCounter() {
    	return keyTwoCounter;
    }

	public void resetGame() {
		idToStart  = isSelectedID;
			ship1.setAlive(true);
			ship2.setAlive(true);
			ship3.setAlive(true);
			ship4.setAlive(true);
		PlayerShip.moveAlivePlayer();
		isGameLost = false;
		isGameStarted = true;	
        }
}
