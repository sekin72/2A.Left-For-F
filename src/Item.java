import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class Item  extends GameObject{
    int numberOfitems;
    int randomMultiplier;
    String name;

    public void draw(Graphics2D g2d)
    {
    	g2d.drawImage(currentImage,GameManager.Instance.levelController.moveDisX+xPos,GameManager.Instance.levelController.moveDisY+yPos,null);
    }


}
