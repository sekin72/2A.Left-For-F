import java.awt.Graphics2D;
import java.util.ArrayList;

public class Enemy extends Character {
	String name;
	boolean isEscapable;
	int escapePenalty;
	ArrayList <Attack> weakness = new ArrayList<Attack>();
	ArrayList <Attack> strongAgainst = new ArrayList<Attack>();
	ArrayList <Attack> attacks = new ArrayList<Attack>();
	
     public void addWeakness(Attack x){
          weakness.add(x);
     }

     public void addStrongAgainst(Attack x){
          strongAgainst.add(x);
     }
     public void addAttack(Attack x){
          attacks.add(x);
     }

     public void draw(Graphics2D g2d)
     {
     	g2d.drawImage(currentImage,GameManager.Instance.levelController.moveDisX+xPos,GameManager.Instance.levelController.moveDisY+yPos,null);
     }

}
