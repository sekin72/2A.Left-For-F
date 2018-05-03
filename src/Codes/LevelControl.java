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

	public boolean gamePause = false;
	public Player player;
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	ArrayList<Item> items = new ArrayList<Item>();
	private int difficulty;
	private Random rand = new Random();
	public int moveDisX=0,moveDisY=0;

	public LevelControl() 
	{
		try {
    		player = new Player("Ali", ImageIO.read(new File(".\\Assets\\player.png")));
        } catch (IOException ex) {
        } 
		
		difficulty = 1;
		positionEnemies();
		positionItems();
		GameManager.Instance.changeUI("Game");
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

	public LevelControl(int currentLevel, Player playah) {
		player = playah;
		difficulty = currentLevel;
		positionEnemies();
		positionItems();
		GameManager.Instance.changeUI("Game");
	}

	public void Update() {
		if (gamePause) {
			GameManager.Instance.changeUI("Pause");
		} else {
			checkGameEnd();
			findEnemies();
			findItemCollision();
		}
	}

	public Boolean checkGameEnd() 
	{
		if (player.healthPoints == 0 || player.xPos == 800)
			return true;
		return false;
	}

	private void positionEnemies() 
	{
		enemies.add(new Teacher(true, rand.nextInt(800), 0, null/*ImageIO.read(new File(".\\Assets\\prof.png"))*/, "Teacher"));
		
		for (int i = 0; i < difficulty; i++) {
			enemies.add(new Quiz(null, "quiz", 10));
			enemies.get(i).xPos = (rand.nextInt(800));
		}
		for (int i = 0; i < difficulty; i++) {
			enemies.add(new Midterm(rand.nextInt(800), 0, null, "Midterm", 0, 20));
		}
		for (int i = 0; i < difficulty; i++) {
			enemies.add(new Project(rand.nextInt(800), 0, null, "Project"));
		}
		
	}

	

	private void positionItems() 
	{
		try {
    		items.add(new EnergyDrink(rand.nextInt(800), rand.nextInt(640), ImageIO.read(new File(".\\Assets\\energy_drink.png")), 1, 1));
    		items.add(new Coffee(rand.nextInt(800), rand.nextInt(640), ImageIO.read(new File(".\\Assets\\coffee.png")), 1, 1));
    		items.add(new Food(rand.nextInt(800), rand.nextInt(640), ImageIO.read(new File(".\\Assets\\hamburger.png")), 1, 1, 1));
    		items.add(new Food(rand.nextInt(800), rand.nextInt(640), ImageIO.read(new File(".\\Assets\\fries.png")), 1, 1, 1));
    		items.add(new Food(rand.nextInt(800), rand.nextInt(640), ImageIO.read(new File(".\\Assets\\pizza.png")), 1, 1, 1));
    		items.add(new Food(rand.nextInt(800), rand.nextInt(640), ImageIO.read(new File(".\\Assets\\donut.png")), 1, 1, 1));
    		items.add(new Food(rand.nextInt(800), rand.nextInt(640), ImageIO.read(new File(".\\Assets\\hotdog.png")), 1, 1, 1));
    		items.add(new Food(rand.nextInt(800), rand.nextInt(640), ImageIO.read(new File(".\\Assets\\egg.png")), 1, 1, 1));
    		items.add(new StatEnchancement(1, "", rand.nextInt(800), rand.nextInt(640), ImageIO.read(new File(".\\Assets\\cpp.png"))));
    		items.add(new StatEnchancement(1, "", rand.nextInt(800), rand.nextInt(640), ImageIO.read(new File(".\\Assets\\java.png"))));
    		items.add(new StatEnchancement(1, "", rand.nextInt(800), rand.nextInt(640), ImageIO.read(new File(".\\Assets\\c#.png"))));
    		items.add(new StatEnchancement(1, "", rand.nextInt(800), rand.nextInt(640), ImageIO.read(new File(".\\Assets\\css.png"))));
    		items.add(new StatEnchancement(1, "", rand.nextInt(800), rand.nextInt(640), ImageIO.read(new File(".\\Assets\\php.png"))));
    		items.add(new StatEnchancement(1, "", rand.nextInt(800), rand.nextInt(640), ImageIO.read(new File(".\\Assets\\python.png"))));
			items.add(new SkillEnchancement(1, "", rand.nextInt(800), rand.nextInt(640), ImageIO.read(new File(".\\Assets\\hashTable.png"))));
			items.add(new SkillEnchancement(1, "", rand.nextInt(800), rand.nextInt(640), ImageIO.read(new File(".\\Assets\\stackOverflow.png"))));
			items.add(new SkillEnchancement(1, "", rand.nextInt(800), rand.nextInt(640), ImageIO.read(new File(".\\Assets\\github.png"))));
        } catch (IOException ex) {
        } 
		
		for (int i = 0; i < items.size(); i++) {
			items.get(i).resize(items.get(i).currentImage, 30, 50);
		}
	}

	private void findItemCollision() {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).xPos+15 >= player.xPos &&  items.get(i).xPos+15 <= player.xPos+100d &&  items.get(i).yPos+25 >= player.yPos &&  items.get(i).yPos+25 <= player.xPos+100)//+50 && player.yPos >= items.get(i).yPos)
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
		for (int i = 0; i < difficulty; i++) {
			if (enemies.get(i).xPos+15 >= player.xPos &&  enemies.get(i).xPos+15 <= player.xPos+100d &&  enemies.get(i).yPos+25 >= player.yPos &&  items.get(i).yPos+25 <= player.xPos+100)//+50 && player.yPos >= items.get(i).yPos)
			{
				GameManager.Instance.gameOn=false;
				GameManager.Instance.changeUI("Battle");
			}
		}
	}

	public void move(String dir) {
		if (GameManager.Instance.gameOn && !gamePause) {
			switch (dir) {
			case "Left":
				moveDisX += 5;
				playerUpdate(-5,0);
				break;
			case "Down":
				moveDisY -= 5;
				playerUpdate(0,5);
				break;
			case "Right":
				moveDisX -= 5;
				playerUpdate(5,0);
				break;
			case "Up":
				moveDisY += 5;
				playerUpdate(0,-5);
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
