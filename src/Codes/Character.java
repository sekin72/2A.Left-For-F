import java.util.ArrayList;

public class Character extends GameObject {
    int healthPoints;
    String name;
    ArrayList<Attack> attacks = new ArrayList<Attack>();


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

    int getX(){
        return xPos;
    }
    int getY(){
        return yPos;
    }

    void setX(int x){
        xPos = x;
    }

    void setY(int y){
        yPos = y;
    }
}
