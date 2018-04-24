import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class Item  extends GameObject{
    int numberOfitems;
    int randomMultiplier;

    public void draw(Graphics2D g2d)
    {
        BufferedImage resizedImage = new BufferedImage(150,100,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(currentImage, 0, 0, 150, 100, null);
        g2.dispose();
        currentImage = resizedImage;   
    	g2d.drawImage(currentImage,GameManager.Instance.levelController.moveDisX+xPos,GameManager.Instance.levelController.moveDisY+yPos,null);
    }


}
