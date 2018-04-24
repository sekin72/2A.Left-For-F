import java.awt.Image;
import java.awt.image.BufferedImage;
public class Food extends Consumable {
    Food(int xPos, int yPos, BufferedImage currentImage, int numberOfItems, int increaseAmount,int maximumHealthChangeAmount,String name){
        this.xPos = xPos;
        this.yPos = yPos;
        this.increaseAmount = increaseAmount;
        this.numberOfitems = numberOfItems;
        this.currentImage = currentImage;
        this.name = name;
        this.maximumHealthChangeAmount = maximumHealthChangeAmount;

    }

    public void update(boolean isTaken){
        if(isTaken){
            currentImage = null;
            xPos = 0;
            yPos = 0;
        }
    }

    @Override
    public boolean isEnergyDrink() {
        return false;
    }

    @Override
    public boolean isCoffee() {
        return false;
    }

    @Override
    public boolean isFood() {
        return true;
    }
}
