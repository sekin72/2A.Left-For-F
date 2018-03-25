import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Character{
    ArrayList <Consumable> itemList= new ArrayList<Consumable>();
    int moveSpeed;
    ArrayList <Enchancement> inUseItems= new ArrayList<Enchancement>();
    ArrayList <Enchancement> enchancements = new ArrayList<Enchancement>();

    int maximumHealth;
    boolean isMale;
    int power;
    enum directions{
        RIGHT,
        LEFT,
        UP,
        DOWN
    }
    Player(String name, BufferedImage currentImage){
        this.name = name;
        maximumHealth = 100;
        healthPoints = maximumHealth;
        this.currentImage = currentImage;
        power = 10;

    }

    public void move(int x, int y){
        xPos = x;
        yPos = y;

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



}
