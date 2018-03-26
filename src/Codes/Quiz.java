import java.awt.Image;
public class Quiz extends Enemy {
    int randomMultiplier;
    Quiz(Image currentImage, String name,int escapePenalty){
        this.currentImage = currentImage;
        this.name = name;
        isEscapable = true;
        this.escapePenalty = escapePenalty;

    }



}
