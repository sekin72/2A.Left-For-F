/**
 * 
 */

/**
 * @author Kaan
 *
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ItemMenu extends Menu
{
	JList list;
	DefaultListModel model;
	int counter = 15;
	ImageIcon bgIcon;
	
	public ItemMenu() 
	{
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
		setLayout(new BorderLayout());
		model = new DefaultListModel();
		list = new JList(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane pane = new JScrollPane(list);
		JButton backButton = new JButton("Back");
		JButton useButton = new JButton("Use");

		for (int i = 0; i < GameManager.Instance.levelController.player.itemList.size(); i++)
		{
			model.addElement(GameManager.Instance.levelController.player.itemList.get(i).name);
		}
		
		backButton.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  if(GameManager.Instance.levelController.enemy==null)
			  {
				  GameManager.Instance.gameOn=true;
				  GameManager.Instance.changeUI("Game");
			  }
			  else
				  GameManager.Instance.changeUI("Battle");
		  }
		});
		
		useButton.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  if(list.getSelectedIndex()!=-1)
				  {
						GameManager.Instance.levelController.player.useItem((String) list.getSelectedValue());
						model.remove(list.getSelectedIndex());
				  }
				  
			  }
			});
		
		
		add(pane, BorderLayout.NORTH);
		add(useButton, BorderLayout.CENTER);
		add(backButton, BorderLayout.SOUTH);
	}
     
}
