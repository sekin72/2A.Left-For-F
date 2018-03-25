/**
 * 
 */

/**
 * @author Kaan
 *
 */

import java.util.Random;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LevelControl {

	private boolean gamePause = false;
	private Menu menu,pausemenu;
	public static LevelControl Instance;
	private Player player;
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    ArrayList<Item> items = new ArrayList<Item>();
	private int difficulty;
	private Random rand = new Random();
	private InputManager input;

	public LevelControl() {
		player=new Player("Ali",null);
		difficulty=1;
		input=new InputManager();
		positionEnemies();
		positionItems();
		GameManager.Instance.changeUI("Game");
	}

	public LevelControl(int currentLevel, Player playah) {
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
			checkGameEnd();
			input.Update();
			findEnemies();
			useItem(findItemCollision());
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
			enemies.add(new Quiz(null,"quiz",10));
			enemies.get(i).setX(rand.nextInt(400));
		}
		for(int i=0;i<difficulty;i++)
		{
			enemies.add(new Midterm(rand.nextInt(400), 0, null, "Midterm" ,0,20));
		}
		for(int i=0;i<difficulty;i++)
		{
			enemies.add(new Project(rand.nextInt(400), 0,  null, "Project"));
		}
		enemies.add(new Teacher(true, rand.nextInt(400), 0, null, "Teacher"));
	}
	
	private void useItem(Item item)
	{
		if(item == null)
			return;
		if(item.getClass() == Food.class)
		{
		}
		if(item.getClass() == EnergyDrink.class)
		{
			
		}
		if(item.getClass() == SkillEnchancement.class)
		{
			
		}
		if(item.getClass() == StatEnchancement.class)
		{
			
		}
	}
	private void positionItems()
	{
	    items.add(new StatEnchancement(1, "",rand.nextInt(400), 0, null));
	    items.add(new SkillEnchancement(1, "",rand.nextInt(400), 0, null));
	    items.add(new Food(rand.nextInt(400), 0, null, 1, 1, 1, ""));
    	items.add(new EnergyDrink(rand.nextInt(400), 0, null, 1, 1));
	}
	
	private Item findItemCollision()
	{
		for(int i=0;i<items.size();i++)
		{
			if(player.xPos==items.get(i).xPos)
				return items.get(i);
		}
		return null;
	}
	
	private Boolean findEnemies()
	{
		for(int i=0;i<difficulty;i++)
		{
			if(player.xPos==enemies.get(i).getX())
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