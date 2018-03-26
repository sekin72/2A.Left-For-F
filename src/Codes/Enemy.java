import java.util.ArrayList;

public class Enemy extends Character {
	String name;
	boolean isEscapable;
	int escapePenalty;
	ArrayList <Attack> weakness = new ArrayList<Attack>();
	ArrayList <Attack> strongAgainst = new ArrayList<Attack>();
	ArrayList <Attack> attacks = new ArrayList<Attack>();
	private int x=0;
 	
 	public int getX()
 	{
 		return x;
 	}
 	
 	public void setX(int a)
 	{
 		x=a;
 	}
 	
     public void addWeakness(Attack x){
          weakness.add(x);
     }

     public void addStrongAgainst(Attack x){
          strongAgainst.add(x);
     }
     public void addAttack(Attack x){
          attacks.add(x);
     }



}
