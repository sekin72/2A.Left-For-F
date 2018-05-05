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

public class BattleScreen extends Menu implements ActionListener{

	public Player player;
	public Enemy enemy;
	Timer timer;

	BufferedImage playerImage;
	BufferedImage enemyImage;
	
	String healthBarEnemy, healthBarPlayer;
	Font font;

    public BattleScreen() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
    	timer = new Timer(50,this);
    	timer.start();
		
		JButton attackkButton = new JButton("Attack");
		JButton itemsButton = new JButton("Items");
		JButton runButton = new JButton("Run");
		
		attackkButton.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  enemy.healthPoints-=player.power;
				  player.healthPoints-=enemy.power;
			  }
			});
			
		itemsButton.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  GameManager.Instance.changeUI("ItemMenu");
			  }
			});
		
		runButton.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  if(enemy.isEscapable)
				  {
					  player.healthPoints-=enemy.escapePenalty;
					  GameManager.Instance.levelController.enemies.remove(GameManager.Instance.levelController.enemyLoc);
					  GameManager.Instance.levelController.enemy=null;
					  GameManager.Instance.gameOn=true;
					  GameManager.Instance.changeUI("Game");
				  }
			  }
			});
			
		JPanel c= new JPanel();
		c.add(attackkButton, BorderLayout.WEST);
		c.add(itemsButton, BorderLayout.SOUTH);
		c.add(runButton, BorderLayout.EAST);
		add(c, BorderLayout.SOUTH);
		
    	player=GameManager.Instance.levelController.player;
    	enemy=GameManager.Instance.levelController.enemy;
    	
    	
    	if(player.isMale)
	    	try {
	    		playerImage = ImageIO.read(new File(".\\Assets\\player.png"));
			    } catch (IOException ex) {
			    } 
    	else
	    	try {
	    		playerImage = ImageIO.read(new File(".\\Assets\\female.png"));
			    } catch (IOException ex) {
			    } 
    		
    	switch(enemy.name)
    	{
	    	case "Proffessor":
	        	try {
	        		enemyImage = ImageIO.read(new File(".\\Assets\\oak.png"));
	    		    } catch (IOException ex) {
	    		    } 
	        	resize(enemyImage,30,50);
	            break;
	    	case "Quiz":
	        	try {
	        		enemyImage = ImageIO.read(new File(".\\Assets\\quiz.jpg"));
	    		    } catch (IOException ex) {
	    		    } 
	        	resize(enemyImage,30,50);
	            break;
	    	case "Midterm":
	        	try {
	        		enemyImage = ImageIO.read(new File(".\\Assets\\mid.png"));
	    		    } catch (IOException ex) {
	    		    } 
	        	resize(enemyImage,30,50);
	            break;
	    	case "Project":
	        	try {
	        		enemyImage = ImageIO.read(new File(".\\Assets\\proj.png"));
	    		    } catch (IOException ex) {
	    		    } 
	        	resize(enemyImage,30,50);
	            break;
	    	default:
	    		break;		
    	}
    	healthBarPlayer = player.healthPoints + "/" + player.maximumHealth;
    	healthBarEnemy = enemy.healthPoints + "/ 100";
    	font= new Font("Calibri", Font.PLAIN,36);
    	setFocusable(true);
    	addKeyListener(new InputManager());
    }

    public void checkBattleEnd()
    {
    	if(enemy.healthPoints==0)
    	{
    		if(enemy.name=="Proffessor")
    		{				
    			GameManager.Instance.levelController.enemies.remove(GameManager.Instance.levelController.enemyLoc);
    			GameManager.Instance.gameOn=false;
    			GameFrame.startTime=0;
    			GameManager.tempTimer=0;
    			GameManager.Instance.changeUI("Won");
    		}
    		else
    		{
				GameManager.Instance.levelController.enemies.remove(GameManager.Instance.levelController.enemyLoc);
				GameManager.Instance.levelController.enemy=null;
				GameManager.Instance.changeUI("Game");
    		}
    	}
    	else if(player.healthPoints<=0)
    	{
			GameManager.Instance.levelController.enemies.remove(GameManager.Instance.levelController.enemyLoc);
			GameManager.Instance.gameOn=false;
			GameFrame.startTime=0;
			GameManager.tempTimer=0;
			GameManager.Instance.changeUI("Lost");
    	}
    }
    public void paint(Graphics g)
    {
    	checkBattleEnd();
    	healthBarPlayer = player.healthPoints + "/" + player.maximumHealth;
    	healthBarEnemy = enemy.healthPoints + "/" + enemy.maximumHealth;
    	super.paint(g);
    	
    	
    	
    	
    	Graphics2D g2d = (Graphics2D) g;
    	g2d.drawImage(playerImage, 25,300,null);
    	g2d.drawImage(enemyImage,670,50,null);
    	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	g2d.setColor(Color.red);
    	g2d.setFont(font);
    	g2d.drawString(healthBarPlayer, 25, 330);
    	g2d.drawString(healthBarEnemy, 650, 220);
    	g2d.drawString("Player", 25, 280);
    	g2d.drawString(enemy.name, 650, 170);
    }
    
    public void resize(BufferedImage originalImage, int w, int h)
    {
    	Image tmp = originalImage.getScaledInstance(w, h, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g = dimg.createGraphics();
	    g.drawImage(tmp, 0, 0, null);
	    g.dispose();
	    originalImage = dimg; 
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
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		GameManager.Instance.Update();
		player=	GameManager.Instance.levelController.player;
		repaint();
	}
}
	

/*
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;




public class BattleScreen extends Menu {

    ImageIcon temp;
    public BattleScreen() {
    	switch(GameManager.Instance.levelController.enemyName)
    	{
	    	case "prof":
	            temp = new ImageIcon(".\\Assets\\prof.png");
	            break;
	    	case "quiz":
	            temp = new ImageIcon(".\\Assets\\quiz.png");
	            break;
	    	case "mid":
	            temp = new ImageIcon(".\\Assets\\mid.png");
	            break;
	    	case "proj":
	            temp = new ImageIcon(".\\Assets\\proj.png");
	            break;
	    	default:
	    		break;		
    	}
        Image temp2= resizeImage(temp,170,220);
        temp = new ImageIcon(temp2);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Hash Table");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("AVL Tree");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("GUI");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("UML Diagrams");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Escape");

        jLabel2.setIcon(new javax.swing.ImageIcon(".\\Assets\\\\player.pngd")); // NOI18N

        jTextField1.setText("Ali Hp : 100 / 100");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel3.setIcon(temp);

        jTextField2.setText("Professor X Hp : 100 / 100");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jButton3))
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(57, 57, 57)
                                .addComponent(jButton5)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(jButton3))
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(115, 115, 115))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed
     private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        jTextField2.setText("Professor X Hp : 70 / 100");
        jTextField1.setText("Ali Hp : 95 / 100");
    } 
     private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        jTextField2.setText("Professor X Hp : 50 / 100");
        jTextField1.setText("Ali Hp : 90 / 100");
    } 
     private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        jTextField2.setText("Professor X Hp : 20 / 100");
        jTextField1.setText("Ali Hp : 85 / 100");
    } private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        jTextField2.setText("Professor X Hp : 0 / 100");
        jTextField1.setText("Ali Hp : 85 / 100");
        //TimeUnit.SECONDS.sleep(5);
       GameManager.Instance.changeUI("Lost");
    } 
     private Image resizeImage(ImageIcon img, int width, int height)
    {
    	BufferedImage resizedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img.getImage(), 0, 0, width, height, null);
        g2.dispose();
        return resizedImage;
    }
     
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BattleScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BattleScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BattleScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BattleScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BattleScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
*/