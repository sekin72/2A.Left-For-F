import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.border.Border;

public class CharacterSelection extends Menu implements ActionListener{

	ImageIcon maleImage;
	ImageIcon femaleImage;
	Timer timer;
	
    public CharacterSelection() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	timer = new Timer(50,this);
    	timer.start();
		setLayout(new BorderLayout());
		
		JButton maleButton = new JButton("Male");
		JButton femaleButton = new JButton("Female");
		
		maleButton.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  try
				  {
					  GameManager.Instance.player = new Player("Ali", ImageIO.read(new File(".\\Assets\\player.png")));
					  GameManager.Instance.player.isMale=true;
					  GameManager.Instance.changeUI("Game");
				  }catch(IOException ex)
				  {
					  
				  }
			  }
			});
			
		femaleButton.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  try
				  {
					  GameManager.Instance.player = new Player("Ayþe", ImageIO.read(new File(".\\Assets\\female.png")));
					  GameManager.Instance.player.isMale=false;
					  GameManager.Instance.changeUI("Game");
				  }catch(IOException ex)
				  {
					  
				  }
			  }
			});
		
			
		JPanel c= new JPanel();
		c.add(maleButton, BorderLayout.WEST);
		c.add(femaleButton, BorderLayout.EAST);
		add(c, BorderLayout.SOUTH);

    	maleImage = new ImageIcon(".\\Assets\\player.png");
    	femaleImage = new ImageIcon(".\\Assets\\female.png");
    	setFocusable(true);
    }

    public void paint(Graphics g)
    {
    	super.paint(g);
    	g.drawImage(maleImage.getImage(), 25,175, null);
		g.drawImage(femaleImage.getImage(), 670,175, null);
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//repaint();
	}
    
}