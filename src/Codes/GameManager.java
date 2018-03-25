/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ömer
 */
import java.awt.Graphics;
public class GameManager {

	private Audio music;
	private int currentLevel;
	private Player player;
	private Menu activeMenu;
	public boolean gameOn = false;
	private LevelControl levelController;
	public static GameManager Instance;

	public GameManager() {
		currentLevel = 1;
		Instance=this;
		 activeMenu = new MainMenu();
		// music=defaultMusic();

	}

	public GameManager(int level) {
		Instance=this;
		currentLevel = level;
	}

	public void createNewLevel() {
		player = new Player("Ali", null);
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
                            
				//createNewLevel();
				//Menüler iþ yapacak burda
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
                        activeMenu.setVisible(false);
                        activeMenu = new GameFrame();
                        activeMenu.setSize(750,420);
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
                System.gc();
	}
}