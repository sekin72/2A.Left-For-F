/**
 * 
 */

/**
 * @author Kaan
 *
 */

import java.util.Random;
	
public class LevelControl {

	/**
	 * 
	 */
	

	private boolean gamePause = false;
	private Menu menu,pausemenu;
	public static LevelControl Instance;
	private Player player;
	private Enemy enemies[];
	private int difficulty;
	private Random rand = new Random();
	private InputManager input;

	public LevelControl() {
		// TODO Auto-generated constructor stub
		player=new Player("Ali",null);
		difficulty=1;
		input=new InputManager();
	}

	/**
	 * @param currentLevel
	 */
	public LevelControl(int currentLevel, Player playah) {
		// TODO Auto-generated constructor stub
		player=playah;
		difficulty=currentLevel;
	}
	
	public void Update()
	{
		if(gamePause)
		{
			GameManager.Instance.changeUI("Pause");
		}
		else
		{
			input.Update();
		}
	}
	
	public Menu getUI()
	{
		return menu;
	}
	
	public Boolean checkGameEnd()
	{
		if(player.healthPoints == 0 || player.xPos==400)
			return true;
		return false;
	}
	
	private void positionEnemies()
	{
		for(int i=0;i<difficulty;i++)
		{
			enemies[i] = new Enemy();
			enemies[i].setX(rand.nextInt(400));
		}
	}
	
	private Boolean findEnemies()
	{
		for(int i=0;i<difficulty;i++)
		{
			if(player.xPos==enemies[i].getX())
				return true;
		}
		return false;
	}
	
	public void move(String dir)
	{
		if(GameManager.Instance.gameOn && !gamePause)
		{
			switch(dir)
			{
				case "A":
					player.move(player.xPos-5, player.yPos);
					break;
				case "S":
					player.move(player.xPos, player.yPos-5);
					break;
				case "D":
					player.move(player.xPos+5, player.yPos);
					break;
				case "W":
					player.move(player.xPos, player.yPos+5);
					break;
				default:
					break;
			}
		}
	}
	
	public void Pause()
	{
		gamePause=true;
		menu=pausemenu;
	}

}
