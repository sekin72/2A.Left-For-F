import java.awt.Image;
public class SkillEnchancement extends Enchancement {
    String skillToUpgraded;
    int damageChange;
    SkillEnchancement(int damageChange, String skillToUpgraded,int xPos, int yPos, Image currentImage){
        this.damageChange = damageChange;
        this.skillToUpgraded = skillToUpgraded;
        this.xPos = xPos;
        this.yPos = yPos;
        this.currentImage = currentImage;
    }
}
