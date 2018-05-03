import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class GameObject {

    int xPos;
    int yPos;
    BufferedImage currentImage;
    void update(long elapsedTime){

    }

    
    
    public void resize(BufferedImage originalImage, int w, int h)
    {
    	Image tmp = originalImage.getScaledInstance(w, h, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g = dimg.createGraphics();
	    g.drawImage(tmp, 0, 0, null);
	    g.dispose();
	    currentImage = dimg; 
    }
}
