import java.awt.Image;
public class Teacher extends  Enemy {
    boolean lastTeacher;
    Teacher(boolean lastTeacher, int xPos, int yPos, Image currentImage, String name){
        this.name = name;
        this.lastTeacher = lastTeacher;
        this.xPos = xPos;
        this.yPos = yPos;
        this.currentImage = currentImage;
        isEscapable = false;

    }


}
