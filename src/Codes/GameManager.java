/**
 * 
 */

/**
 * @author Kaan
 *
 */


public class GameManager {

	/**
	 * 
	 */
	
	private Audio music;
	private int currentLevel;
	private Player player;
	private Menu activeMenu;
	public boolean gameOn = false;
	private LevelControl levelController;
	public static GameManager Instance;
	
	public GameManager() {
		// TODO Auto-generated constructor stub
		currentLevel=1;
		player=new Player("Ali",null);
//		activeMenu = new MainMenu();
		//music=defaultMusic();
		
	}
	
	public GameManager(int level, Player playah) {
		// TODO Auto-generated constructor stub
		currentLevel=level;
		player=playah;
	}
	
	private void createNewLevel()
	{
		gameOn=true;
		levelController = new LevelControl(currentLevel, player);
		activeMenu = levelController.getUI();
	}
	
	public void Update()
	{
		while(true)
		{
			if(gameOn)
			{
				levelController.Update();
			}
			else
			{
			}
		}
	}
	
	public void changeUI(String newMenu)
	{
		switch(newMenu)
		{
			case "Main":
				break;
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
				break;
			default:
				break;
		}
	}
}
