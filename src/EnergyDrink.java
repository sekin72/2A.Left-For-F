import java.awt.Image;
import java.awt.image.BufferedImage;
public class EnergyDrink extends Consumable{
	
    EnergyDrink(int xPos, int yPos, BufferedImage currentImage, int numberOfItems, int increaseAmount){
        this.xPos = xPos;
        this.yPos = yPos;
        this.increaseAmount = increaseAmount;
        this.numberOfitems = numberOfItems;
        this.currentImage = currentImage;
        this.name = "energyDrink";
    }
    
    EnergyDrink()
    {
        this.name = "energyDrink";
    }

    public void update(boolean isTaken){
        if(isTaken){
            currentImage = null;
            xPos = 0;
            yPos = 0;
        }
    }

    public boolean isEnergyDrink(){
        return true;
    }


    public boolean isCoffee() {
        return false;
    }


    public boolean isFood() {
        return false;
    }
}
