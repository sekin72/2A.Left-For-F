import java.awt.Image;
public class EnergyDrink extends Consumable{
	
    EnergyDrink(int xPos, int yPos, Image currentImage, int numberOfItems, int increaseAmount){
        this.xPos = xPos;
        this.yPos = yPos;
        this.increaseAmount = increaseAmount;
        this.numberOfitems = numberOfItems;
        this.currentImage = currentImage;

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
