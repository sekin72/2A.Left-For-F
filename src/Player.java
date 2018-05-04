import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Player extends Character{
    ArrayList <Consumable> itemList= new ArrayList<Consumable>();
    int moveSpeed;
    ArrayList <Enchancement> inUseItems= new ArrayList<Enchancement>();
    ArrayList <Enchancement> enchancements = new ArrayList<Enchancement>();

    boolean isMale;
    
    enum directions{
        RIGHT,
        LEFT,
        UP,
        DOWN
    }

    BufferedImage originalImage = null;
    Player(String name, BufferedImage currentImage){
        this.name = name;
        maximumHealth = 100;
        healthPoints = maximumHealth;
        this.currentImage = currentImage;
        
    	try {
    		originalImage = ImageIO.read(new File(".\\Assets\\player.png"));
        	resize(originalImage, 100,100);
        } catch (IOException ex) {
        } 
    	
        power = 10;
        xPos=20;
        yPos=100;  
        moveSpeed=5;
    }

    public void move(int xVel, int yVel){
        xPos += xVel;
        yPos += yVel;
    }

    public void useItem(String name) {
		if (name == "coffee") {
			useCoffee();
		}
		if (name == "food") {
			useFood();
		}
		if (name == "energyDrink") {
			useEnergyDrink();
		}
		if (name == "skillEnhancement") {
			power+=10;
		}
		if (name == "statEnhancement") {
			moveSpeed+=5;
		}
	}
    
    public boolean useEnergyDrink(){
        for(int i = 0; i < itemList.size(); i++){
            if (itemList.get(i).name =="energyDrink"){
                healthPoints = healthPoints + 10;
                itemList.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean useCoffee(){
        for(int i = 0; i < itemList.size(); i++){
            if (itemList.get(i).name == "coffee"){
                healthPoints = healthPoints + 5;
                itemList.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean useFood(){
        for(int i = 0; i < itemList.size(); i++){
            if (itemList.get(i).name == "food"){
                    healthPoints = healthPoints + itemList.get(i).increaseAmount;
                    maximumHealth = maximumHealth + itemList.get(i).maximumHealthChangeAmount;
                    itemList.remove(i);
                    return true;
            }
        }
        return false;
    }

    public void addItem(Item x){
        if (x.getClass() == Consumable.class){
           itemList.add((Consumable)x);
        }
        else
            enchancements.add((Enchancement)x);

    }

    public void equipEnchancement(Enchancement x){
        for(int i = 0; i < enchancements.size(); i++){
            if(enchancements.get(i).name == x.name) {
                enchancements.remove(i);
                inUseItems.add(x);
            }
        }

    }

    public void draw(Graphics2D g2d)
    {
		//System.out.println(xPos + " "+ yPos);
    	g2d.drawImage(currentImage,20,100,null);
    	
    }
}