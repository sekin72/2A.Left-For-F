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
	
	public int getPlayerX() {
		return player.xPos;
	}

	public int getPlayerY() {
		return player.yPos;
	}

	public LevelControl() {
		

		try {
    		player = new Player("Ali", ImageIO.read(new File(".\\Assets\\player.png")));
        } catch (IOException ex) {
        } 
		
		difficulty = 1;
		positionEnemies();
		positionItems();
		GameManager.Instance.changeUI("Game");
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
			System.out.println(player.xPos);
			// checkGameEnd();
			findEnemies();
			// Item temp = findItemCollision();
			// if(temp !=null)
			// dddddplayer.addItem(temp);
		}
	}

	public Boolean checkGameEnd() {
		if (player.healthPoints == 0 || player.xPos == 400)
			return true;
		return false;
	}

	private void positionEnemies() {
		
		try {
			enemies.add(new Teacher(true, rand.nextInt(400), 0, ImageIO.read(new File(".\\Assets\\prof.png")), "Teacher"));
        } catch (IOException ex) {
        } 

		enemies.get(0).resize(enemies.get(0).currentImage, 30, 50);
		
		for (int i = 0; i < difficulty; i++) {
			enemies.add(new Quiz(null, "quiz", 10));
			enemies.get(i).setX(rand.nextInt(400));
		}
		for (int i = 0; i < difficulty; i++) {
			enemies.add(new Midterm(rand.nextInt(400), 0, null, "Midterm", 0, 20));
		}
		for (int i = 0; i < difficulty; i++) {
			enemies.add(new Project(rand.nextInt(400), 0, null, "Project"));
		}
		
	}

	private void useItem(Item item) {
		if (item == null)
			return;

		if (item.getClass() == Coffee.class) {
			player.useCoffee();
		}
		if (item.getClass() == Food.class) {
			player.useFood("");
		}
		if (item.getClass() == EnergyDrink.class) {
			player.useEnergyDrink();
		}
		if (item.getClass() == SkillEnchancement.class) {
			player.equipEnchancement((Enchancement) item);
		}
		if (item.getClass() == StatEnchancement.class) {
			player.equipEnchancement((Enchancement) item);
		}
	}

	private void positionItems() {
		items.add(new StatEnchancement(1, "", rand.nextInt(400), 0, null));
		items.add(new Food(rand.nextInt(400), 0, null, 1, 1, 1, ""));
		
		try {
			items.add(new SkillEnchancement(1, "", rand.nextInt(400), 0, ImageIO.read(new File(".\\Assets\\cpp.png"))));
    		items.add(new EnergyDrink(rand.nextInt(400), 0, ImageIO.read(new File(".\\Assets\\energy_drink.png")), 1, 1));
    		items.add(new Coffee(rand.nextInt(400), 0, ImageIO.read(new File(".\\Assets\\coffee.png")), 1, 1));
        } catch (IOException ex) {
        } 
		items.get(2).resize(items.get(2).currentImage, 30, 50);
		items.get(3).resize(items.get(3).currentImage, 30, 50);
		items.get(4).resize(items.get(4).currentImage, 30, 50);
		
	}

	private Item findItemCollision() {
		for (int i = 0; i < items.size(); i++) {
			if (player.xPos == items.get(i).xPos)
				return items.get(i);
		}
		return null;
	}

	private Boolean findEnemies() {
		if (enemies.size() == 0) {
			return false;
		}
		for (int i = 0; i < difficulty; i++) {
			if (player.xPos == enemies.get(i).getX()) {
				GameManager.Instance.changeUI("Battle");
				System.out.println("hoca");
				return true;
			}
		}
		return false;
	}

	public void move(String dir) {
		if (GameManager.Instance.gameOn && !gamePause) {
			switch (dir) {
			case "Left":
				moveDisX += 5;
				break;
			case "Down":
				moveDisY -= 5;
				break;
			case "Right":
				moveDisX -= 5;
				break;
			case "Up":
				moveDisY += 5;
				break;
			default:
				break;
			}
		}
		/*if (player.xPos > 250) {
			GameManager.Instance.changeUI("Battle");
			player.xPos = 220;
			player.xVel = 0;
		}*/
	}

	public void playerUpdate() {
		player.move();
	}

	public void resetMoveChanges() {
		player.xVel = 0;
		player.yVel = 0;
	}

	public void Pause() {
		gamePause = true;
		GameManager.Instance.changeUI("Pause");
	}

}
