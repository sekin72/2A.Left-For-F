import java.awt.Image;
public class Project extends Enemy {

    Project(int xPos, int yPos, Image currentImage, String name){
        this.name = name;
        this.xPos = xPos;
        this.yPos = yPos;
        this.currentImage = currentImage;
        isEscapable = false;
    }
}
