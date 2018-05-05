import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * @author Kaan
 *
 */

public class GameManager {

	private Audio music;
	public int currentLevel;
	public Player player=null;
	private Menu activeMenu = new MainMenu();
	public boolean gameOn = false;
	public LevelControl levelController=null;
	public static GameManager Instance;
	public static long tempTimer=0;

	public GameManager() {
		currentLevel = 1;
		Instance=this;
	}

	public GameManager(int level) {
		Instance=this;
		currentLevel = level;
	}

	public void createNewLevel() {
		GameFrame.startTime=0;
		GameManager.tempTimer=0;
		System.out.println("New level is "+currentLevel);
		if(player==null)
		{
			try {
			player = new Player("Ali", ImageIO.read(new File(".\\Assets\\player.png")));
		    } catch (IOException ex) {
		    } 
		}
		player.xPos=20;
		player.yPos=100;
		player.healthPoints=player.maximumHealth;
		gameOn = true;
		levelController = new LevelControl(currentLevel,player);
		Update();
	}
	
	public void createNewLevel(Player playerr, int dif) {
		GameFrame.startTime=0;
		GameManager.tempTimer=0;
		currentLevel=dif;
		System.out.println("New level is "+currentLevel);
		player=playerr;
		player.xPos=20;
		player.yPos=100;
		gameOn = true;
		levelController = new LevelControl(currentLevel,player);
		Update();
	}

	public void Update() {
		{
			if (gameOn) 
			{
				tempTimer=0;
				levelController.Update();
			} 
			else 
			{
				if(tempTimer==0)
					tempTimer=System.currentTimeMillis();
			}
		}
	}

	public void changeUI(String newMenu) {
		switch (newMenu) {
			case "Options":
				break;
			case "Tutorials":
                activeMenu.setVisible(false);
                activeMenu = new Tutorial();
                activeMenu.setSize(690,320);
                activeMenu.setVisible(true);
				break;
			case "Credits":
                activeMenu.setVisible(false);
                activeMenu = new Credits();
                activeMenu.setSize(800,500);
                activeMenu.setVisible(true);
				break;
			case "CharacterSelection":
                activeMenu.setVisible(false);
                activeMenu = new CharacterSelection();
                activeMenu.setSize(800,500);
                activeMenu.setVisible(true);
				break;
			case "ItemMenu":
				activeMenu.setVisible(false);
				activeMenu = new ItemMenu();
	            activeMenu.setSize(810,400);
	            activeMenu.setVisible(true);
				break;
			case "Pause":
            	activeMenu.setVisible(false);
				activeMenu = new PauseMenu();
                activeMenu.setSize(810,400);
                activeMenu.setVisible(true);
				break;
			case "MainMenu":
            	activeMenu.setVisible(false);
				activeMenu = new MainMenu();
                activeMenu.setSize(810,400);
                activeMenu.setVisible(true);
				break;
			case "Game":
				gameOn=true;
                activeMenu.setVisible(false);
                if(levelController != null)
                	levelController.gamePause=false;
                activeMenu = new GameFrame();
                activeMenu.setSize(1366,768);
                activeMenu.setVisible(true);
                break;
            case "Won":
                activeMenu.setVisible(false);
                activeMenu = new WinPage();
                activeMenu.setSize(750,400);
                activeMenu.setVisible(true);
                break;
            case "Lost":
	            activeMenu.setVisible(false);
	            activeMenu = new LostPage();
	            activeMenu.setSize(700,320);
	            activeMenu.setVisible(true);
	            break;
            case "Battle":
                activeMenu.setVisible(false);
                activeMenu = new BattleScreen();
                activeMenu.setSize(800,500);
                activeMenu.setVisible(true);
            default:
        		break;
		}
	}
}
