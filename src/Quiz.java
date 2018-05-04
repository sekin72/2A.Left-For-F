import java.awt.Image;
import java.awt.image.BufferedImage;
public class Quiz extends Enemy {
    int randomMultiplier;
    Quiz(BufferedImage currentImage, String name,int escapePenalty){
        this.currentImage = currentImage;
        this.name = name;
        isEscapable = true;
        this.escapePenalty = escapePenalty;
    }



}
