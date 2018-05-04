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
	private int currentLevel;
	private Player player;
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
		try {
		player = new Player("Ali", ImageIO.read(new File(".\\Assets\\player.png")));
	    } catch (IOException ex) {
	    } 
		gameOn = true;
		levelController = new LevelControl(currentLevel,player);
		Update();
	}

	public void Update() {
		//while (true) 
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
				break;
			case "ItemMenu":
				activeMenu.setVisible(false);
				activeMenu = new ItemMenu();
	            activeMenu.setSize(810,400);
	            activeMenu.setVisible(true);
				break;
			case "Pause":
				break;
			case "MainMenu":
            	activeMenu.setVisible(false);
				activeMenu = new MainMenu();
                activeMenu.setSize(810,400);
                activeMenu.setVisible(true);
				break;
			case "Game":
                GameManager.Instance.gameOn=true;
                activeMenu.setVisible(false);
                activeMenu = new GameFrame(player);
                activeMenu.setSize(919,744);
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
