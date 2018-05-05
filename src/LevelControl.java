/**
 * 
 */

/**
 * @author Kaan
 *
 */

import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LevelControl {

	public boolean gamePause = false, timeRanOut=false;
	public Player player;
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	ArrayList<Item> items = new ArrayList<Item>();
	private int difficulty;
	private Random rand = new Random();
	public int moveDisX=0,moveDisY=0, enemyLoc;
	public Enemy enemy=null;
	
	public LevelControl() 
	{
		difficulty = 1;
		positionEnemies();
		positionItems();
	}
	
	public LevelControl(LevelControl level) 
	{
		gamePause = true;
		player= level.player;
		enemies = level.enemies;
		items = level.items;
		difficulty = level.difficulty;
		moveDisX=level.moveDisX;
		moveDisY=level.moveDisY;
	}

	public LevelControl(int currentLevel,Player playerr) {
		enemies = new ArrayList<Enemy>();
		items = new ArrayList<Item>();
		player = playerr;
		difficulty = currentLevel;
		positionEnemies();
		positionItems();
		if(currentLevel==1)
			GameManager.Instance.changeUI("CharacterSelection");
		else
			GameManager.Instance.changeUI("Game");
			
	}

	public void Update() {
		if (gamePause) {
		} else {
			player=GameManager.Instance.player;
			checkGameEnd();
			findEnemies();
			findItemCollision();
			if(player.healthPoints>player.maximumHealth)
				player.healthPoints=player.maximumHealth;
		}
	}

	public Boolean checkGameEnd()
	{
		if (player.healthPoints <= 0 || timeRanOut)
		{
			GameManager.Instance.gameOn=false;
			GameFrame.startTime=0;
			GameManager.tempTimer=0;
			GameManager.Instance.changeUI("Lost");
			timeRanOut=false;
			return true;
		}
		return false;
	}

	private void positionEnemies() 
	{
		int count=0;
		for (int i = 0; i < difficulty+1; i++) {
			enemies.add(new Quiz(null, "Quiz", 10));
			enemies.get(count).xPos = (rand.nextInt(400)+300);
			enemies.get(count).yPos = (rand.nextInt(768));
			enemies.get(count).power =difficulty;
			count++;
		}
		for (int i = 0; i < difficulty; i++) {
			enemies.add(new Midterm(rand.nextInt(400)+500, rand.nextInt(768), null, "Midterm", 0, 20));
			enemies.get(count).power =2*difficulty;
			count++;
		}
		for (int i = 0; i < difficulty-1; i++) {
			enemies.add(new Project(rand.nextInt(400)+700, rand.nextInt(768), null, "Project"));
			enemies.get(count).power =4*difficulty;
			count++;
		}
		enemies.add(new Teacher(true, rand.nextInt(400)+900, rand.nextInt(768), null, "Proffessor"));
		enemies.get(enemies.size()-1).power =5*difficulty;
		
		for (int i = 0; i < enemies.size(); i++) 
		{
			enemies.get(i).maximumHealth=50*difficulty;
			enemies.get(i).healthPoints=50*difficulty;
		}
	}

	

	private void positionItems() 
	{
		try {
    		items.add(new EnergyDrink(rand.nextInt(1366), rand.nextInt(768), ImageIO.read(new File(".\\Assets\\energy_drink.png")), 1, 1));
    		items.add(new Coffee(rand.nextInt(1366), rand.nextInt(768), ImageIO.read(new File(".\\Assets\\coffee.png")), 1, 1));
    		items.add(new EnergyDrink(rand.nextInt(1366), rand.nextInt(640), ImageIO.read(new File(".\\Assets\\energy_drink.png")), 1, 1));
    		items.add(new Coffee(rand.nextInt(1366), rand.nextInt(768), ImageIO.read(new File(".\\Assets\\coffee.png")), 1, 1));
    		switch(difficulty)
    		{
	    		case 1:
	        		items.add(new StatEnchancement(1, "", rand.nextInt(1366), rand.nextInt(768), ImageIO.read(new File(".\\Assets\\python.png"))));
	        		items.add(new Food(rand.nextInt(1366), rand.nextInt(768), ImageIO.read(new File(".\\Assets\\hamburger.png")), 1, 1, 1));
	        		items.add(new Food(rand.nextInt(1366), rand.nextInt(768), ImageIO.read(new File(".\\Assets\\fries.png")), 1, 1, 1));
	    			break;
	    		case 2:
	        		items.add(new StatEnchancement(1, "", rand.nextInt(1366), rand.nextInt(768), ImageIO.read(new File(".\\Assets\\java.png"))));
	    			items.add(new SkillEnchancement(1, "", rand.nextInt(1366), rand.nextInt(768), ImageIO.read(new File(".\\Assets\\hashTable.png"))));
	        		items.add(new Food(rand.nextInt(1366), rand.nextInt(768), ImageIO.read(new File(".\\Assets\\hamburger.png")), 1, 1, 1));
	        		items.add(new Food(rand.nextInt(1366), rand.nextInt(768), ImageIO.read(new File(".\\Assets\\fries.png")), 1, 1, 1));
	    			break;
	    		case 3:
	        		items.add(new StatEnchancement(1, "", rand.nextInt(1366), rand.nextInt(768), ImageIO.read(new File(".\\Assets\\c#.png"))));
	    			items.add(new SkillEnchancement(1, "", rand.nextInt(1366), rand.nextInt(768), ImageIO.read(new File(".\\Assets\\stackOverflow.png"))));
	        		items.add(new Food(rand.nextInt(1366), rand.nextInt(768), ImageIO.read(new File(".\\Assets\\pizza.png")), 1, 1, 1));
	        		items.add(new Food(rand.nextInt(1366), rand.nextInt(768), ImageIO.read(new File(".\\Assets\\donut.png")), 1, 1, 1));
	    			break;
	    		case 4:
	        		items.add(new StatEnchancement(1, "", rand.nextInt(1366), rand.nextInt(768), ImageIO.read(new File(".\\Assets\\cpp.png"))));
	    			items.add(new SkillEnchancement(1, "", rand.nextInt(1366), rand.nextInt(768), ImageIO.read(new File(".\\Assets\\github.png"))));
	        		items.add(new Food(rand.nextInt(1366), rand.nextInt(768), ImageIO.read(new File(".\\Assets\\hotdog.png")), 1, 1, 1));
	        		items.add(new Food(rand.nextInt(1366), rand.nextInt(768), ImageIO.read(new File(".\\Assets\\egg.png")), 1, 1, 1));
	    			break;
    			default:
    				break;
    		}
        } catch (IOException ex) {
        } 
		
		for (int i = 0; i < items.size(); i++) {
			items.get(i).resize(items.get(i).currentImage, 30, 50);
		}
	}

	private void findItemCollision() {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).xPos+15 >= player.xPos &&  items.get(i).xPos+15 <= player.xPos+100 &&  items.get(i).yPos+25 >= player.yPos &&  items.get(i).yPos+25 <= player.yPos+100)//+50 && player.yPos >= items.get(i).yPos)
			{
				if(items.get(i).name == "food" || items.get(i).name == "coffee" || items.get(i).name == "energyDrink")
				{
					player.itemList.add((Consumable) items.get(i));
					items.remove(i);
				}
				else if(items.get(i).name == "skillEnhancement" || items.get(i).name == "statEnhancement")
				{
					player.enchancements.add( (Enchancement) items.get(i));
					items.remove(i);
				}
			}
		}
	}

	private void findEnemies() {
		if (enemies.size() == 0) {
			return;
		}
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).xPos+15 >= player.xPos &&  enemies.get(i).xPos+15 <= player.xPos+100)
			{
				enemy = enemies.get(i);
				enemyLoc=i;
				gamePause = true;
				GameManager.Instance.changeUI("Battle");
			}
		}
	}

	public void move(String dir) {
		if (GameManager.Instance.gameOn && !gamePause) {
			switch (dir) {
			case "Left":
				moveDisX += player.moveSpeed;
				playerUpdate(-player.moveSpeed,0);
				break;
			case "Down":
				moveDisY -= player.moveSpeed;
				playerUpdate(0,player.moveSpeed);
				break;
			case "Right":
				moveDisX -= player.moveSpeed;
				playerUpdate(player.moveSpeed,0);
				break;
			case "Up":
				moveDisY += player.moveSpeed;
				playerUpdate(0,-player.moveSpeed);
				break;
			default:
				break;
			}
		}
	}

	public void playerUpdate(int x, int y) {
		player.move(x,y);
	}

	public void resetMoveChanges() {
	}

	public void Pause() {
		gamePause = true;
		GameManager.Instance.changeUI("Pause");
	}
	
	public void ItemMenu() {
		gamePause = true;
		GameManager.Instance.changeUI("ItemMenu");
	}

}
