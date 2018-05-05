import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PauseMenu extends Menu implements ActionListener{

	Timer timer;
	ImageIcon bg;
	
    public PauseMenu() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	timer = new Timer(50,this);
    	timer.start();

		JButton optionsButton = new JButton("Options");
		JButton itemsButton = new JButton("Items");
		JButton gameButton = new JButton("Continue Game");
		JButton mainButton = new JButton("Return to Main");
		

    	bg = new ImageIcon(".\\Assets\\l4fback.png");
		
		optionsButton.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {

				  GameManager.Instance.changeUI("Options");
			  }
			});
			
		itemsButton.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  GameManager.Instance.levelController.ItemMenu();
			  }
			});
		
		gameButton.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {

				  GameManager.Instance.changeUI("Game");
			  }
			});
			
		mainButton.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  GameManager.Instance.gameOn=false;
					GameFrame.startTime=0;
					GameManager.tempTimer=0;
				  GameManager.Instance.changeUI("MainMenu");
			  }
			});
    
		

		getContentPane().add(optionsButton);
		getContentPane().add(itemsButton);
		getContentPane().add(gameButton);
		getContentPane().add(mainButton);
		getContentPane().add(new JLabel(bg));
		optionsButton.setBounds(310, 100, 180, 27);
		itemsButton.setBounds(310, 150, 180, 27);
		gameButton.setBounds(310, 200, 180, 27);
		mainButton.setBounds(310, 250, 180, 27);
		

    	setFocusable(true);
    	addKeyListener(new InputManager());
    }

    public void paint(Graphics g)
    {
    	super.paint(g);
    	
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}
    
}