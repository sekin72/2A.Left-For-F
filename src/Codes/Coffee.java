import java.awt.Image;

public class Coffee extends Consumable {

    Coffee(int xPos, int yPos, Image currentImage, int numberOfItems, int increaseAmount){
        this.xPos = xPos;
        this.yPos = yPos;
        this.increaseAmount = increaseAmount;
        this.numberOfitems = numberOfItems;
        this.currentImage = currentImage;

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