import java.awt.Image;
import java.awt.image.BufferedImage;
public class StatEnchancement extends Enchancement {
    String statToUpgraded;
    int statChangeAmount;
    StatEnchancement(int statChangeAmount, String statToUpgraded,int xPos, int yPos, BufferedImage currentImage){
        this.statChangeAmount = statChangeAmount;
        this.statToUpgraded = statToUpgraded;
        this.xPos = xPos;
        this.yPos = yPos;
        this.currentImage = currentImage;
        this.name="statEnhancement";
    }
    
    StatEnchancement()
    {
        this.name="statEnhancement";
    }
}
