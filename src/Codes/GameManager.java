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
	private Menu activeMenu;
	public boolean gameOn = false;
	public LevelControl levelController;
	public static GameManager Instance;

	public GameManager() {
		currentLevel = 1;
		Instance=this;
		// activeMenu = new MainMenu();
		// music=defaultMusic();

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
				//Menüler iþ yapacak burda
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
			activeMenu = new MainMenu();
			activeMenu.setVisible(true);
			break;
		case "Game":
			activeMenu = new GameFrame(player);
			activeMenu.setVisible(true);
			break;
		default:
			break;
		}
		System.gc();
	}
}
