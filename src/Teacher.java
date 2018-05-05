import java.awt.Image;
import java.awt.image.BufferedImage;
public class Teacher extends  Enemy {
    boolean lastTeacher;
    Teacher(boolean lastTeacher, int xPos, int yPos, BufferedImage currentImage, String name){
        this.name = name;
        this.lastTeacher = lastTeacher;
        this.xPos = xPos;
        this.yPos = yPos;
        this.currentImage = currentImage;
        isEscapable = false;
        this.name =name;

    }


}
