import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
public class GameFrame extends Menu {


	public Player player;
    public GameFrame(Player playah) {
    	setFocusable(true);
    	player =playah;
    }

    public void paint(Graphics g)
    {
    	super.paint(g);
    	Graphics2D g2d = (Graphics2D) g;
    	player.draw(g2d);
    	
    }
    
    private BufferedImage resizeImage(ImageIcon img, int width, int height)
    {
    	BufferedImage resizedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img.getImage(), 0, 0, width, height, null);
        g2.dispose();
        return resizedImage;
    }

    private BufferedImage flipImageHor(BufferedImage img)
    {
    	// Flip the image horizontally
    	AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
    	tx.translate(-img.getWidth(null), 0);
    	AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    	img = op.filter(img, null);
    	return img;
    }

    private BufferedImage flipImageVer(BufferedImage img)
    {
    	// Flip the image vertically
    	AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
    	tx.translate(0, -img.getHeight(null));
    	AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    	img = op.filter(img, null);
    	return img;
    }
}


/*

private void update()
{
	while(true)
	{
		jLabel1.setLocation(LevelControl.Instance.getPlayerX(), LevelControl.Instance.getPlayerY());
	}
}

private BufferedImage resizeImage(ImageIcon img, int width, int height)
{
	BufferedImage resizedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
    Graphics2D g2 = resizedImage.createGraphics();
    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2.drawImage(img.getImage(), 0, 0, width, height, null);
    g2.dispose();
    return resizedImage;
}

private BufferedImage flipImageHor(BufferedImage img)
{
	// Flip the image horizontally
	AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
	tx.translate(-img.getWidth(null), 0);
	AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
	img = op.filter(img, null);
	return img;
}

private BufferedImage flipImageVer(BufferedImage img)
{
	// Flip the image vertically
	AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
	tx.translate(0, -img.getHeight(null));
	AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
	img = op.filter(img, null);
	return img;
}

*/