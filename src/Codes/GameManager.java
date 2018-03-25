import java.awt.Graphics;

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

	public GameManager() {
		currentLevel = 1;
		Instance=this;
	}

	public GameManager(int level) {
		Instance=this;
		currentLevel = level;
	}

	public void createNewLevel() {
		player = new Player("Ali", new ImageIcon(".\\kahve.png").getImage());
		gameOn = true;
		levelController = new LevelControl();
	}

	public void Update() {
		while (true) {
			if (gameOn) 
			{
				levelController.Update();
			} 
			else 
			{
			}
		}
	}

	public void changeUI(String newMenu) {
		switch (newMenu) {
		case "Options":
			break;
		case "Tutorials":
			break;
		case "Credits":
			break;
		case "CharacterSelection":
			break;
		case "ItemMenu":
			break;
		case "Pause":
			break;
		case "MainMenu":
			activeMenu.setVisible(false);
			activeMenu = new MainMenu();
			activeMenu.setSize(810, 400);
			activeMenu.setVisible(true);
			break;
		case "Game":
			activeMenu.setVisible(false);
			activeMenu = new GameFrame(player);
			activeMenu.setSize(750, 420);
			activeMenu.setVisible(true);
			break;
		default:
			break;
		}
		System.gc();
	}
}
