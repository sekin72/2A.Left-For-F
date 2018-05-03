import java.awt.Image;
import java.awt.image.BufferedImage;

public class Coffee extends Consumable {

    Coffee(int xPos, int yPos, BufferedImage currentImage, int numberOfItems, int increaseAmount){
        this.xPos = xPos;
        this.yPos = yPos;
        this.increaseAmount = increaseAmount;
        this.numberOfitems = numberOfItems;
        this.currentImage = currentImage;
        this.name = "coffee";
    }
    @Override
    public boolean isEnergyDrink() {
        return false;
    }

    @Override
    public boolean isCoffee() {
        return true;
    }

    @Override
    public boolean isFood() {
        return false;
    }
}