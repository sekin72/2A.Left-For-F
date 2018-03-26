import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
    
    Player(String name, Image currentImage){
        this.name = name;
        maximumHealth = 100;
        healthPoints = maximumHealth;
        this.currentImage = currentImage;
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

        BufferedImage resizedImage = new BufferedImage(150,100,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(currentImage, 0, 0, 150, 100, null);
        g2.dispose();
        currentImage = resizedImage;   
    	g2d.drawImage(currentImage,xPos,yPos,null);
    }

}