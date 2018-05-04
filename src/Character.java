import java.awt.Graphics2D;
import java.util.ArrayList;

public class Character extends GameObject {
    int healthPoints;
    int maximumHealth;
    String name;
    ArrayList<Attack> attacks = new ArrayList<Attack>();

    int power;

    boolean isDead(){
        if(healthPoints == 0)
            return true;
        else
            return false;
    }
    int attack(String attackMove){
        for(int i = 0; i<attacks.size(); i++){
            if (attackMove.equalsIgnoreCase(attacks.get(i).name)){
                return attacks.get(i).damage;
            }
        }
        return 0;
    }
    void getAttacked(int damage){
        this.healthPoints = this.healthPoints - damage;
    }


    
}
