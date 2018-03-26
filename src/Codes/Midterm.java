import java.awt.Image;
public class Midterm extends Enemy {
    int minimumRequirements;

    Midterm(int xPos, int yPos, Image currentImage, String name,int minimumRequirements,int escapePenalty){
        this.minimumRequirements = minimumRequirements;
        this.escapePenalty = escapePenalty;

        this.xPos = xPos;
        this.yPos = yPos;
        this.currentImage = currentImage;
        this.name = name;

    }

    void update() {
        if(healthPoints <= minimumRequirements){
            isEscapable = true;
        }
        else
            isEscapable = false;

    }
}
