import java.awt.Image;
public class StatEnchancement extends Enchancement {
    String statToUpgraded;
    int statChangeAmount;
    StatEnchancement(int statChangeAmount, String statToUpgraded,int xPos, int yPos, Image currentImage){
        this.statChangeAmount = statChangeAmount;
        this.statToUpgraded = statToUpgraded;
        this.xPos = xPos;
        this.yPos = yPos;
        this.currentImage = currentImage;
    }
}
