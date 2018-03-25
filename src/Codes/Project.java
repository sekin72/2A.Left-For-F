import java.awt.image.BufferedImage;

public class Project extends Enemy {

    Project(int xPos, int yPos, BufferedImage currentImage, String name){
        this.name = name;
        this.xPos = xPos;
        this.yPos = yPos;
        this.currentImage = currentImage;
        isEscapable = false;
    }
}
