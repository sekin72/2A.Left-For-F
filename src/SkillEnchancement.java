import java.awt.Image;
import java.awt.image.BufferedImage;
public class SkillEnchancement extends Enchancement {
    String skillToUpgraded;
    int damageChange;
    SkillEnchancement(int damageChange, String skillToUpgraded,int xPos, int yPos, BufferedImage currentImage){
        this.damageChange = damageChange;
        this.skillToUpgraded = skillToUpgraded;
        this.xPos = xPos;
        this.yPos = yPos;
        this.currentImage = currentImage;
        this.name="skillEnhancement";
    }
}
