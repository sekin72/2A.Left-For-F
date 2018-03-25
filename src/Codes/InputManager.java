/**
 * 
 */
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * @author Kaan
 *
 */
public class InputManager extends KeyAdapter{

	@Override
    public void keyPressed(KeyEvent event) {
    	char ch = event.getKeyChar();
    	switch(ch)
		{
			case 'A':
			case 'a':
				LevelControl.Instance.move("Left");
				break;
			case 'S':
			case 's':
				LevelControl.Instance.move("Down");
				break;
			case 'D':
			case 'd':
				LevelControl.Instance.move("Right");
				break;
			case 'W':
			case 'w':
				LevelControl.Instance.move("Up");
				break;
			case 'P':
			case 'p':
				LevelControl.Instance.Pause();
				break;
			default:
				break;
		}
    	System.out.println(event.getKeyChar());
    }
	
}