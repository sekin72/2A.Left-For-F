import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Timer;

public class GameFrame extends Menu implements ActionListener{

	public Player player;
	Timer timer;
	ImageIcon bgIcon;
	
    public GameFrame(Player playah) {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    	setFocusable(true);
    	player =playah;
    	addKeyListener(new InputManager());
    	timer = new Timer(50,this);
    	timer.start();
		bgIcon = new ImageIcon(".\\Assets\\map.png");
		JLabel health = new JLabel();
		health.setText(GameManager.Instance.levelController.player.healthPoints + "/" + GameManager.Instance.levelController.player.maximumHealth); 
		add(health);
    }

    public void paint(Graphics g)
    {
    	super.paint(g);

    	g.drawImage(bgIcon.getImage(), GameManager.Instance.levelController.moveDisX, GameManager.Instance.levelController.moveDisY, null);
    	Graphics2D g2d = (Graphics2D) g;
    	for(int i=0;i<GameManager.Instance.levelController.items.size();i++)
    	{
    		if(GameManager.Instance.levelController.items.get(i)!=null)
    		{
    			GameManager.Instance.levelController.items.get(i).draw(g2d);
    		}
    	}
    	GameManager.Instance.levelController.enemies.get(0).draw(g2d);
    	player.draw(g2d);
    	

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
    
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		GameManager.Instance.Update();
		player=	GameManager.Instance.levelController.player;
		repaint();
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