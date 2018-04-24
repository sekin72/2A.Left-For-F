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

    int maximumHealth;
    boolean isMale;
    int power;
    
    public int xVel=0,yVel=0;
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
        	resize(originalImage, 300,150);
        } catch (IOException ex) {
        } 
    	
        power = 10;
        xPos=140;
        yPos=100;     
    }

    public void move(){
        xPos += xVel;
        yPos += yVel;
    }

    public boolean useEnergyDrink(){
        for(int i = 0; i < itemList.size(); i++){
            if (itemList.get(i).isEnergyDrink()){
                healthPoints = healthPoints + itemList.get(i).increaseAmount;
                itemList.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean useCoffee(){
        for(int i = 0; i < itemList.size(); i++){
            if (itemList.get(i).isCoffee()){
                healthPoints = healthPoints + itemList.get(i).increaseAmount;
                itemList.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean useFood(String foodName){
        for(int i = 0; i < itemList.size(); i++){
            if (itemList.get(i).isFood()){
                if (itemList.get(i).name == foodName){
                    healthPoints = healthPoints + itemList.get(i).increaseAmount;
                    maximumHealth = maximumHealth + itemList.get(i).maximumHealthChangeAmount;
                    itemList.remove(i);
                    return true;
                }

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
    	g2d.drawImage(currentImage,xPos,yPos,null);
    }

}